package com.i3020.mvpdemo01.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i3020.mvpdemo01.R;
import com.i3020.mvpdemo01.config.Variable;
import com.i3020.mvpdemo01.ui.widget.LoadingView;
import com.i3020.mvpdemo01.ui.widget.NoNetFrameLayout;
import com.i3020.mvpdemo01.utils.HPUtils;
import com.i3020.mvpdemo01.utils.PhoneInfoUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * decribe:
 * create by HeJinliang on 2018/8/16
 */
public abstract class BaseFragment<V extends BaseView, T extends BasePresenter<V>> extends Fragment {

	protected  T mPresenter;

	protected Context mContext;

	private NoNetFrameLayout noNetFrameLayout;

	protected LoadingView mLoadingView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mContext = getActivity();

		mPresenter = createPresenter();
		if (mPresenter != null){
			mPresenter.attachView((V) this);
		}
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View mRootView = inflater.inflate(setLayoutId(), container, false);

		dealCommon(mRootView);//处理相同逻辑

		initView();//初始化控件

		initListener();//设置监听


		initData();//初始化数据

		return mRootView;
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();

		//关闭键盘

		if (mPresenter != null){
			mPresenter.detachView();
		}

	}


	/**
	 * 设置资源ID
	 *
	 * @return layoutID
	 */
	protected abstract int setLayoutId();

	/**
	 * 处理相同逻辑
	 */
	private void dealCommon(View view){

		//断网布局
		if (view instanceof NoNetFrameLayout){
			noNetFrameLayout = (NoNetFrameLayout) view;
			noNetFrameLayout.setRefreshListener(v -> {
				if (!HPUtils.isFastClick()) {
					mPresenter.loadData();//重新加载数据
				}
			});
		}

		//v19以上，可以通过加view，解决沉浸式状态栏和内容布局重叠的问题
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			try{
				View view_status_bar = view.findViewById(R.id.view_status_bar);

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
			} catch (Exception e){
				e.printStackTrace();
			}
		} else {
			try{
				View view_status_bar = view.findViewById(R.id.view_status_bar);
				if (view_status_bar != null){
					view_status_bar.setVisibility(View.GONE);
				}

			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化控件
	 */
	protected abstract void initView();

	/**
	 * 设置监听(给控件添加监听)
	 */
	protected abstract void initListener();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 创建presenter
	 * @return
	 */
	protected abstract T createPresenter();

	/**
	 * 注册EventBus
	 */
	public void register() {
		EventBus.getDefault().register(this);
	}


	/**
	 * 注销EventBus
	 */
	public void unRegister() {
		EventBus.getDefault().unregister(this);
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
		((Activity) mContext).finish();
	}

	public Bundle getBundle(){
		return getArguments();
	}


}
