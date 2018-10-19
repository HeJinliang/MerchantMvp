package com.i3020.mvpdemo01.viewpresenter.guide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.i3020.mvpdemo01.config.Constant;
import com.i3020.mvpdemo01.config.Variable;
import com.i3020.mvpdemo01.model.bean.StandardSimpleBean;
import com.i3020.mvpdemo01.model.impl.OkHttpModelImpl;
import com.i3020.mvpdemo01.model.impl.SPUtils;
import com.i3020.mvpdemo01.utils.FastJsonUtils;
import com.i3020.mvpdemo01.utils.GsonUtils;
import com.i3020.mvpdemo01.utils.LogUtils;
import com.i3020.mvpdemo01.viewpresenter.guide.GuideContract.AbstractPresenter;
import com.i3020.mvpdemo01.viewpresenter.login.LoginActivity;
import com.i3020.mvpdemo01.viewpresenter.main.MainActivity;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/23.
 */
public class GuidePresenter extends AbstractPresenter {

	private String pushMsg;

	public GuidePresenter(Context mContext) {
		super(mContext);
	}

	@Override
	public void attachView(GuideContract.IView mView) {
		super.attachView(mView);
	}

	@Override
	protected void init() {
		if (getView() != null){
			Bundle bundle = getView().getBundle();
			if (bundle != null){
				pushMsg = bundle.getString("pushMsg");
			}

		}
	}

	@Override
	public void detachView() {
		super.detachView();

		mHandler.removeCallbacksAndMessages(null);

	}

	/**
	 * 校验登录 加载数据
	 */
	@Override
	public void loadData() {
		LogUtils.e("11111111111111");

		String uid = SPUtils.getUid(mContext);
		if (!TextUtils.isEmpty(uid)){
			//本地记录是登陆状态，校验登陆
			//非测试环境，而且没有请求过切换服务器的接口
			OkHttpModelImpl.getInstance().loadLoginDataByPost(Constant.URL_LOGIN_CHECK_LOGIN, this);

			LogUtils.e("22222222222222");

		} else {
			//本地记录是非登陆状态，直接跳转登陆界面
			mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 1000);

			LogUtils.e("3333333333333");
		}
	}


//--------------------------------------------------------------------------------------------------
	//联网请求数据 start
//--------------------------------------------------------------------------------------------------

	@Override
	public void onRequestSuccess(String urlCut, Response response, String content) {
		super.onRequestSuccess(urlCut, response, content);

		switch (urlCut){
			case Constant.URL_LOGIN_CHECK_LOGIN:
				//校验登录
				StandardSimpleBean standardSimpleBean = FastJsonUtils.getInstance().JsonToBean(content, StandardSimpleBean.class);
				if (standardSimpleBean != null){
					String return_code = standardSimpleBean.getReturn_code();
					if ("0".equals(return_code)){

						//通过校验， 进行刷新token

						Map map = new HashMap();
						String deviceToken;//将友盟推送的deviceToken传递给后台
						if (TextUtils.isEmpty(Variable.uMengDeviceToken)){
							deviceToken = SPUtils.getDeviceToken(mContext);
						} else {
							deviceToken = Variable.uMengDeviceToken;
						}
						if (!TextUtils.isEmpty(deviceToken)){
							map.put("device_token", deviceToken);
						}

						OkHttpModelImpl.getInstance().loadLoginDataByPost(Constant.URL_LOGIN_UPDATE_TOKEN, map,this);


					} else {
						if (TextUtils.isEmpty(pushMsg)){
							mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 1000);
						} else {
							mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 200);
						}

					}
				} else {
					if (TextUtils.isEmpty(pushMsg)){
						mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 1000);
					} else {
						mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 200);
					}
				}

				break;
			case Constant.URL_LOGIN_UPDATE_TOKEN:
				//刷新token
				StandardSimpleBean standardSimpleBean1 = GsonUtils.getInstance().GsonToBean(content, StandardSimpleBean.class);
				if (standardSimpleBean1 != null){
					String return_code3 = standardSimpleBean1.getReturn_code();
					if ("0".equals(return_code3)){
						//刷新token成功之后，注册推送
//						LogUtils.i("push", "发送EventBus信息");
//						EventBus.getDefault().post(new MainEvent(EventBusConfig.EVENT_REGISTER_PUSH));

						//进入首页
						if (TextUtils.isEmpty(pushMsg)){
							mHandler.sendEmptyMessageDelayed(TO_MAINACTIVITY, 1000);
						} else {
							mHandler.sendEmptyMessageDelayed(TO_MAINACTIVITY, 200);
						}

					} else {
						if (TextUtils.isEmpty(pushMsg)){
							mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 1000);
						} else {
							mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 200);
						}
					}
				} else {
					if (TextUtils.isEmpty(pushMsg)){
						mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 1000);
					} else {
						mHandler.sendEmptyMessageDelayed(TO_LOGINACTIVITY, 200);
					}
				}


				break;
		}

	}

//--------------------------------------------------------------------------------------------------
	//联网请求数据 end
//--------------------------------------------------------------------------------------------------









//--------------------------------------------------------------------------------------------------
//内部类 start
//--------------------------------------------------------------------------------------------------


	/**
	 * 跳转到首页
	 */
	private final int TO_MAINACTIVITY = 2;
	/**
	 * 跳转到登录界面
	 */
	private final int TO_LOGINACTIVITY = 3;

	Handler mHandler = new Handler(msg -> {
		switch (msg.what){
			case TO_MAINACTIVITY:
				//跳转主页面

				if (getView() != null){
					Intent intent = new Intent(mContext, MainActivity.class);
					intent.putExtra("tag", 0);
					intent.putExtra("pushMsg", pushMsg);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

					getView().startActivity(intent, -1);

					getView().finishActivity();
				}

				break;
			case TO_LOGINACTIVITY:
				//跳转登录界面
				SPUtils.setUid(mContext, "");//清空用户id
				SPUtils.setToken(mContext, "");//清空token
				Variable.LOGIN_BOOLEAN = false;
				Variable.Token = "";

				if (getView() != null){
					Intent loginIntent = new Intent(mContext, LoginActivity.class);
					loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
					getView().startActivity(loginIntent, -1);
					getView().finishActivity();
				}


				break;
		}
		return true;
	});


//--------------------------------------------------------------------------------------------------
//内部类 end
//--------------------------------------------------------------------------------------------------



}
