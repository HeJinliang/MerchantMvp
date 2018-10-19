package com.i3020.mvpdemo01.base;

import android.content.Context;

import com.i3020.mvpdemo01.listener.DataRequestListener;
import com.i3020.mvpdemo01.utils.LogUtils;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import okhttp3.Request;
import okhttp3.Response;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/16.
 */
public abstract class BasePresenter<T extends BaseView> implements DataRequestListener<String> {

	/**
	 * view接口的弱引用
	 */
	private Reference<T> mViewRef;

	protected Context mContext;

	public BasePresenter(Context mContext){
		this.mContext = mContext;
	}


	/**
	 * 绑定视图
	 * @param mView view视图
	 */
	public void attachView(T mView){
		mViewRef = new WeakReference<>(mView);

		init();
	}

	/**
	 * 解绑视图
	 */
	public void detachView(){
		if (mViewRef != null){
			mViewRef.clear();
			mViewRef = null;
		}
	}



	/**
	 * 获取绑定的视图的实例
	 * @return
	 */
	protected T getView(){
		if (isViewAttached()){
			return mViewRef.get();
		} else {
			return null;
		}
	}



	/**
	 * presenter是否持有view的引用
	 * @return
	 */
	private boolean isViewAttached(){
		return mViewRef != null && mViewRef.get() != null;
	}

	/**
	 * 初始化一些数据
	 */
	protected abstract void init();

	/**
	 * 加载数据
	 */
	public void loadData(){

	}


	/**
	 * 联网 成功
	 * @param urlCut
	 * @param response
	 * @param content
	 */
	@Override
	public void onRequestSuccess(String urlCut, Response response, String content) {
		getView().set404Visiable(false);
		LogUtils.i("接口"+urlCut, content);
	}

	/**
	 * 联网 失败
	 * @param urlCut
	 * @param request
	 * @param e
	 */
	@Override
	public void onRequestFaile(String urlCut, Request request, IOException e) {
		getView().set404Visiable(true);
	}

	/**
	 * 联网 反馈
	 * @param urlCut
	 * @param isSuccess
	 */
	@Override
	public void onRequestCommon(String urlCut, boolean isSuccess) {
		if (getView() != null){
			getView().showLoading(false);
		}
	}

}
