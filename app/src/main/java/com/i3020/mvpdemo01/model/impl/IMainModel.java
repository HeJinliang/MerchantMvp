package com.i3020.mvpdemo01.model.impl;

import com.i3020.mvpdemo01.listener.DataRequestListener;

import java.util.Map;

/**
 * decribe:
 * create by HeJinliang on 2018/8/16
 */
public interface IMainModel {

	/**
	 * 登录接口 无额外参数 get请求
	 * @param url
	 * @param listener
	 */
	void loadLoginDataByGet(String url, DataRequestListener<String> listener);

	/**
	 * 登录接口 有额外参数 get请求
	 * @param url
	 * @param map
	 * @param listener
	 */
	void loadLoginDataByGet(String url,  Map<String, String> map, DataRequestListener<String> listener);

	/**
	 * 业务接口 无额外参数 get请求
	 * @param url
	 * @param listener
	 */
	void loadHaipiDataByGet(String url, DataRequestListener<String> listener);

	/**
	 * 业务接口 有额外参数 get请求
	 * @param url
	 * @param map
	 * @param listener
	 */
	void loadHaipiDataByGet(String url,Map<String, String> map, DataRequestListener<String> listener);

	/**
	 * 普通接口 无额外参数 get请求
	 * @param url
	 * @param listener
	 */
	void loadDataByGet(String url, DataRequestListener<String> listener);

	/**
	 * 普通接口 有额外参数 get请求
	 * @param url
	 * @param map
	 * @param listener
	 */
	void loadDataByGet(String url, Map<String, String> map, DataRequestListener<String> listener);



	/**
	 * 登录接口 无额外参数 post请求
	 * @param url
	 * @param listener
	 */
	void loadLoginDataByPost(String url, DataRequestListener<String> listener);

	/**
	 * 登录接口 有额外参数 post请求
	 * @param url
	 * @param map
	 * @param listener
	 */
	void loadLoginDataByPost(String url,  Map<String, String> map, DataRequestListener<String> listener);

	/**
	 * 业务接口 无额外参数 post请求
	 * @param url
	 * @param listener
	 */
	void loadHaipiDataByPost(String url, DataRequestListener<String> listener);

	/**
	 * 业务接口 有额外参数 postt请求
	 * @param url
	 * @param map
	 * @param listener
	 */
	void loadHaipiDataByPost(String url,Map<String, String> map, DataRequestListener<String> listener);

	/**
	 * 普通接口 无额外参数 post请求
	 * @param url
	 * @param listener
	 */
	void loadDataByPost(String url, DataRequestListener<String> listener);

	/**
	 * 普通接口 有额外参数 post请求
	 * @param url
	 * @param map
	 * @param listener
	 */
	void loadDataByPost(String url, Map<String, String> map, DataRequestListener<String> listener);





}
