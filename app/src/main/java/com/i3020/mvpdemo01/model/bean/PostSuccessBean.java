package com.i3020.mvpdemo01.model.bean;


import com.i3020.mvpdemo01.listener.DataRequestListener;

import okhttp3.Response;

/**
 * describe: 联网成功的javaBean
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/9/21.
 */

public class PostSuccessBean<T> {

    /**
     * 本次联网的请求字段
     */
    private String strUrlCut;
    /**
     * 本次联网的响应体
     */
    private Response response;

	private DataRequestListener<T> listener;


    public PostSuccessBean(){}

    public PostSuccessBean(String strUrlCut, Response response, DataRequestListener<T> listener){
        this.strUrlCut = strUrlCut;
        this.response = response;
        this.listener = listener;
    }

	public DataRequestListener<T> getListener() {
		return listener;
	}

	public void setListener(DataRequestListener<T> listener) {
		this.listener = listener;
	}

	public void setStrUrlCut(String strUrlCut){
        this.strUrlCut = strUrlCut;
    }

    public String getStrUrlCut(){
        return strUrlCut;
    }

    public void setResponse(Response response){
        this.response = response;
    }

    public Response getResponse(){
        return response;
    }

}
