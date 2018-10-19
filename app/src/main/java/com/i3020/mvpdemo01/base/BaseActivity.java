package com.i3020.mvpdemo01.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.i3020.mvpdemo01.R;
import com.i3020.mvpdemo01.config.AppConfig;
import com.i3020.mvpdemo01.config.Constant;
import com.i3020.mvpdemo01.config.Variable;
import com.i3020.mvpdemo01.listener.DataRequestListener;
import com.i3020.mvpdemo01.model.bean.ChangeDataSourceBean;
import com.i3020.mvpdemo01.model.impl.OkHttpModelImpl;
import com.i3020.mvpdemo01.model.impl.SPUtils;
import com.i3020.mvpdemo01.ui.widget.LoadingView;
import com.i3020.mvpdemo01.ui.widget.NoNetFrameLayout;
import com.i3020.mvpdemo01.ui.widget.swipeBackLayout.SwipeBackLayout;
import com.i3020.mvpdemo01.ui.widget.swipeBackLayout.app.SwipeBackActivity;
import com.i3020.mvpdemo01.utils.FastJsonUtils;
import com.i3020.mvpdemo01.utils.HPUtils;
import com.i3020.mvpdemo01.utils.LogUtils;
import com.i3020.mvpdemo01.utils.PhoneInfoUtils;
import com.i3020.mvpdemo01.utils.StandardDialog;
import com.umeng.message.PushAgent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * decribe:
 * create by HeJinliang on 2018/8/16
 */
public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends SwipeBackActivity {

	protected T mPresenter;
	protected Context mContext;
	private NoNetFrameLayout noNetFrameLayout;
	protected LoadingView mLoadingView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(getLayoutResId());

		mPresenter = createPresenter();
		if (mPresenter != null) {
			mPresenter.attachView((V) this);
		}

		//1.处理相同逻辑
		dealCommon();
		//2.查找控件
		initView();
		//3.设置监听
		initListener();

		checkPhonePermission();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mPresenter != null){
			mPresenter.detachView();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if (requestCode == MY_PERMISSION_REQUEST_CODE) {
			boolean isAllGranted = true;

			// 判断是否所有的权限都已经授予了
			for (int grant : grantResults) {
				if (grant != PackageManager.PERMISSION_GRANTED) {
					isAllGranted = false;
					break;
				}
			}

			if (isAllGranted) {
				// 如果所有的权限都授予了, 则执行代码
				getPhoneStateNextStep();

			} else {
				// 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
				showPhonePermissionDialog();
			}
		}
	}

	/**
	 * 提醒用户需要电话权限设置
	 */
	private void showPhonePermissionDialog() {
		StandardDialog.create((Activity) mContext, 1)
				.setTitle("警告")
				.setMessage("读取手机信息需要电话权限否则APP无法正常工作，请到 “应用信息 -> 权限” 中授予！")
				.setPositiveButton("去授权", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
						intent.addCategory(Intent.CATEGORY_DEFAULT);
						intent.setData(Uri.parse("package:" + getPackageName()));
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
						intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
						startActivity(intent);

						finish();
					}
				})
				.setNegativeButton("取消", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				})
				.setCancelable(false)
				.show();
	}


	/**
	 * 获取当前activity的布局id
	 * 父类确定不了布局,子类确定
	 *
	 * @return
	 */
	protected abstract int getLayoutResId();

	/**
	 * 处理相同逻辑
	 * 返回按钮
	 * 1.非空判断(子Activity可能没有返回按钮)
	 * 2.类型变为View(子Activity的返回按钮可能不是button)
	 * 3. 限制子类的返回按钮的id back
	 * 返回按钮为button
	 */
	private void dealCommon() {

		mContext = this;

		//断网布局
		View childAt = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
		if (childAt instanceof NoNetFrameLayout){
			noNetFrameLayout = (NoNetFrameLayout) childAt;
			noNetFrameLayout.setRefreshListener(v -> {
				if (!HPUtils.isFastClick()) {
					mPresenter.loadData();//重新加载数据
				}
			});
		}


		PushAgent.getInstance(mContext).onAppStart(); // 友盟推送

		getSomeInfo();//获取网络状态、用户ID、系统日期等信息

		getUserInfo();//用户是否处于登录状态, 登录， 获取与用户有关的信息


		//v19以上，可以通过加view，解决沉浸式状态栏和内容布局重叠的问题
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			try{
				View view_status_bar = findViewById(R.id.view_status_bar);

				int statusBarHeight = Variable.status_bar_height;
				if (statusBarHeight == 0){
					statusBarHeight = PhoneInfoUtils.getStatusBarHeight(mContext);
				}
				if (statusBarHeight != 0){
					ViewGroup.LayoutParams layoutParams = view_status_bar.getLayoutParams();
					layoutParams.height = statusBarHeight;
					view_status_bar.setLayoutParams(layoutParams);
					view_status_bar.setVisibility(View.VISIBLE);
				}
			} catch (Exception ignored){
			}
		} else {
			try{
				View view_status_bar = findViewById(R.id.view_status_bar);
				if (view_status_bar != null){
					view_status_bar.setVisibility(View.GONE);
				}

			} catch (Exception ignored){
			}
		}
	}




	/**
	 * 查找控件(findviewById)
	 */
	protected abstract void initView();

	/**
	 * 设置监听(给控件添加监听)
	 */
	public abstract void initListener();


	/**
	 * 设置数据 (初始化数据:给控件设置显示内容)
	 */
	protected abstract void initData();


	/**
	 * 创建presenter
	 * @return
	 */
	protected abstract T createPresenter();


	/**
	 * 请求读取手机状态的请求码
	 */
	private final int MY_PERMISSION_REQUEST_CODE = 1001;

	/**
	 * 检测读取手机信息的权限
	 */
	private void checkPhonePermission(){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
			if (PermissionChecker.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(
						this,
						new String[] {
								Manifest.permission.READ_PHONE_STATE
						},
						MY_PERMISSION_REQUEST_CODE
				);

				return;
			} else {
				getPhoneStateNextStep();
			}
		} else {
			getPhoneStateNextStep();
		}
	}

	/**
	 * 获取读取手机状态权限后下一步操作
	 */
	private void getPhoneStateNextStep(){

		//获取终端的唯一标识
		if (TextUtils.isEmpty(Variable.imei) || AppConfig.IMSI.equals(Variable.imei)){
			Variable.imei = PhoneInfoUtils.getIMEI(mContext);
		}

		//获取SIM卡的唯一标识
		if (TextUtils.isEmpty(Variable.imsi) || AppConfig.IMSI.equals(Variable.imsi)){
			Variable.imsi = PhoneInfoUtils.getIMSI(mContext);
		}

		if (!AppConfig.isDebug && !Variable.isChangeEnvironment){

			//非测试环境，而且没有请求过切换服务器的接口
			OkHttpModelImpl.getInstance().loadLoginDataByPost(Constant.URL_LOGIN_CHANGE_ENVIR, new DataRequestListener<String>() {
				@Override
				public void onRequestSuccess(String urlCut, Response response, String content) {
					if (Constant.URL_LOGIN_CHANGE_ENVIR.equals(urlCut)){
						LogUtils.i("切换测试/线上————环境", content);

						ChangeDataSourceBean changeDataSourceBean = FastJsonUtils.getInstance().JsonToBean(content, ChangeDataSourceBean.class);

						if (changeDataSourceBean != null){
							String return_code = changeDataSourceBean.getReturn_code();
							if ("0".equals(return_code)){
								String data = changeDataSourceBean.getData();
								if (!TextUtils.isEmpty(data)){
									try{
										int i = Integer.parseInt(data);
										//后台控制切换正式/测试
										if (i%39916801 == 111){
											LogUtils.e("测试服数据");
											AppConfig.isDebugData = "2";
										} else if(i%39916801 == 113){
											LogUtils.e("线上测试服数据");
											AppConfig.isDebugData = "3";
										} else {
											LogUtils.e("正式服数据");
										}

									}catch (Exception e){
										LogUtils.e(e.toString());
										e.printStackTrace();
									}
								}
							}
						}
					}

					//4.设置数据
					initData();
				}

				@Override
				public void onRequestFaile(String urlCut, Request request, IOException e) {
					//4.设置数据
					initData();
				}

				@Override
				public void onRequestCommon(String urlCut, boolean isSuccess) {

				}
			});

		} else {
			//4.设置数据
			initData();
		}
	}




	/**
	 * 获取网络状态、
	 */
	private void getSomeInfo() {
		//网络状态
		Variable.nt = PhoneInfoUtils.getNetWork(mContext);//获取手机的网络状态
		Variable.ct = PhoneInfoUtils.getSystemDate();//获取系统日期

		//手机型号
		if (TextUtils.isEmpty(Variable.mm)){
			Variable.mm = PhoneInfoUtils.getPhoneModel();
		}

		//嗨皮版本名称
		if (TextUtils.isEmpty(Variable.vn)){
			Variable.vn = PhoneInfoUtils.getVersionName();
		}

		//嗨皮版本号
		if (TextUtils.isEmpty(Variable.vc)){
			Variable.vc = PhoneInfoUtils.getVersionCode(mContext);
		}

		//获取手机类型
		if (TextUtils.isEmpty(Variable.dt)){
			Variable.dt = PhoneInfoUtils.getPhoneType(mContext);
		}

		//获取操作系统的版本
		if (TextUtils.isEmpty(Variable.osv)){
			Variable.osv = PhoneInfoUtils.getOSVersion();
		}

		//屏幕宽高，以及屏幕宽度与1080的比值
		if (TextUtils.isEmpty(Variable.w) || TextUtils.isEmpty(Variable.h) || Variable.screenScale==1.1){
			int screentWidth = PhoneInfoUtils.getScreenWidth(mContext);
			Variable.w = String.valueOf(screentWidth);//获取屏幕宽度
			Variable.h = String.valueOf(PhoneInfoUtils.getScreenHeight(mContext));//获取屏幕高度
			if (screentWidth != 0){
				Variable.screenScale = (float) (screentWidth*1.0/1080);
			}
		}

		//获取状态栏的高度
		if (Variable.status_bar_height == 0){
			Variable.status_bar_height = PhoneInfoUtils.getStatusBarHeight(mContext);
		}

		//获取二维码匹配字段
		if (TextUtils.isEmpty(AppConfig.SCAN_DJ_URL)){
			AppConfig.SCAN_DJ_URL = SPUtils.getScanDjUrl(mContext);
		}
	}


	/**
	 * 根据本地文件SP中登陆相关的信息写入内存
	 */
	private void getUserInfo() {
		//内存中的用户id被清空之后，从本地读取
		if (Variable.LOGIN_BOOLEAN){
			if (TextUtils.isEmpty(Variable.uid) || TextUtils.isEmpty(Variable.Token)){
				String uid = SPUtils.getUid(mContext);
				String token = SPUtils.getToken(mContext);
				if (!TextUtils.isEmpty(uid) && !TextUtils.isEmpty(token)){
					Variable.uid = uid;
					Variable.Token = token;
					Variable.LOGIN_BOOLEAN = true;
				} else {
					Variable.LOGIN_BOOLEAN = false;
				}
			} else {
				Variable.LOGIN_BOOLEAN = true;
			}

			if (TextUtils.isEmpty(Variable.storeId)){
				Variable.storeId = SPUtils.getStoreId(mContext);
			}

			if (TextUtils.isEmpty(Variable.storeName)){
				Variable.storeName = SPUtils.getStoreName(mContext);
			}
		}

	}




	/**
	 * 设置透明状态栏
	 * @param isLightTheme  6.x系统设置状态栏字体颜色  ture 亮底黑字  false 暗底白字
	 */
	protected void setStatusBar(boolean isLightTheme){
		setStatusBar(false, isLightTheme);
	}

	/**
	 * 设置透明状态栏与导航栏
	 * @param navi true不设置导航栏|false设置导航栏
	 * @param isLightTheme 6.x系统设置状态栏字体颜色  ture 亮底黑字  false 暗底白字
	 */
	protected void setStatusBar(boolean navi, boolean isLightTheme) {
		//api>21,全透明状态栏和导航栏;api>19,半透明状态栏和导航栏
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
			Window window = getWindow();

			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
			window.setStatusBarColor(Color.TRANSPARENT);//设置状态栏的背景颜色

			if (isLightTheme){
				window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//状态栏颜色设置为黑色

				if (navi) {
					window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//状态栏不会被隐藏但activity布局会扩展到状态栏所在位置
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//导航栏不会被隐藏但activity布局会扩展到导航栏所在位置
							| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//状态栏字体颜色设置为黑色
					window.setNavigationBarColor(getResources().getColor(R.color.color_statusbar));//设置导航栏的背景颜色
				} else {
					window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//状态栏字体颜色设置为黑色
				}
			} else {
				if (navi) {
					window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//状态栏不会被隐藏但activity布局会扩展到状态栏所在位置
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//导航栏不会被隐藏但activity布局会扩展到导航栏所在位置
							| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
					window.setNavigationBarColor(getResources().getColor(R.color.color_statusbar));//设置导航栏的背景颜色
				} else {
					window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
				}
			}

		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
			window.setStatusBarColor(getResources().getColor(R.color.color_statusbar));//设置状态栏的背景颜色
			if (navi) {
				window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//状态栏不会被隐藏但activity布局会扩展到状态栏所在位置
						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//导航栏不会被隐藏但activity布局会扩展到导航栏所在位置
						| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
				window.setNavigationBarColor(getResources().getColor(R.color.color_statusbar));//设置导航栏的背景颜色
			} else {
				//注意两个Flag必须要结合在一起使用,应用的主体内容占用系统状态栏的空间
				window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

			}
		}
	}


	/**
	 * 注册EventBus
	 */
	public void registeEventBus() {
		EventBus.getDefault().register(this);
	}


	/**
	 * 注销EventBus
	 */
	public void unRegisteEventBus() {
		EventBus.getDefault().unregister(this);
	}



	/**
	 * 屏蔽右滑退出效果
	 */
	public void forbidSwipeBack() {
		SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
		swipeBackLayout.setEnableGesture(false);
	}


	/**
	 * 显示加载进度框
	 * @param isShow
	 */
	public void showLoading(boolean isShow) {
		if (isShow){
			if (mContext instanceof Activity){
				if (mLoadingView == null){
					mLoadingView = new LoadingView((Activity) mContext);
				}
				mLoadingView.show();
			}
		} else {
			if (mLoadingView != null){
				mLoadingView.dismiss();
			}
		}

	}

	/**
	 * 显示断网提示
	 * @param isNerError
	 */
	public void set404Visiable(boolean isNerError){
		if (noNetFrameLayout != null){
			noNetFrameLayout.set404Visiable(isNerError);
		}
	}

	/**
	 * 设置断网布局margin
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	public void set404ViewMargin(int top, int bottom, int left, int right){
		if (noNetFrameLayout != null){
			noNetFrameLayout.set404ViewMargin(top, bottom, left, right);
		}
	}

	public void startActivity(Intent intent, int requestCode) {
		if (requestCode != -1){
			startActivityForResult(intent, requestCode);
		} else {
			startActivity(intent);
		}

	}

	public void finishActivity() {
		finish();
	}


	public Bundle getBundle(){
		return getIntent().getExtras();
	}

}
