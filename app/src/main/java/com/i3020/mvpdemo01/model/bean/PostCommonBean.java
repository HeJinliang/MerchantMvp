package com.i3020.mvpdemo01.model.bean;

import com.i3020.mvpdemo01.listener.DataRequestListener;

/**
 * describe: 联网结果的javaBean
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/9/21.
 */

public class PostCommonBean {

    /**
     * 本次联网的请求字段
     */
    private String strUrlCut;
    /**
     * 联网的结果
     */
    private boolean result;

	private DataRequestListener<String> listener;

	public PostCommonBean(String strUrlCut, boolean result, DataRequestListener<String> listener){
        this.strUrlCut = strUrlCut;
        this.result = result;
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

    public void setResult(boolean result){
        this.result = result;
    }

    public boolean getResult(){
        return result;
    }
}
