package com.i3020.mvpdemo01.listener;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * decribe:
 * create by HeJinliang on 2018/8/16
 */
public interface DataRequestListener<T> {

	void onRequestSuccess(String urlCut, Response response, T content);

	void onRequestFaile(String urlCut, Request request, IOException e);

	void onRequestCommon(String urlCut, boolean isSuccess);

}
