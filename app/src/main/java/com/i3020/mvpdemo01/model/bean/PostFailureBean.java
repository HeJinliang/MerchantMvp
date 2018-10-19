package com.i3020.mvpdemo01.model.bean;


import com.i3020.mvpdemo01.listener.DataRequestListener;

import java.io.IOException;

import okhttp3.Request;

/**
 * describe: 联网成功的javaBean
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/9/21.
 */

public class PostFailureBean {

    /**
     * 本次联网的请求字段
     */
    private String strUrlCut;
    /**
     * 本次联网的请求体
     */
    private Request request;

	/**
	 * 异常信息
	 */
	private IOException e;

	private DataRequestListener<String> listener;

    public PostFailureBean(){}

    public PostFailureBean(String strUrlCut, Request request, IOException e, DataRequestListener<String> listener){
        this.strUrlCut = strUrlCut;
        this.request = request;
        this.e = e;
        this.listener = listener;
    }

	public DataRequestListener<String> getListener() {
		return listener;
	}

	public void setListener(DataRequestListener<String> listener) {
		this.listener = listener;
	}

	public void setStrUrlCut(String strUrlCut){
        this.strUrlCut = strUrlCut;
    }

    public String getStrUrlCut(){
        return strUrlCut;
    }

    public void setRequest(Request request){
        this.request = request;
    }

    public Request getRequest(){
        return request;
    }

    public void setE(IOException e){
        this.e = e;
    }

    public IOException getE(){
        return e;
    }

}
