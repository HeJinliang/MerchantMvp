package com.i3020.mvpdemo01.model.bean;

/**
 * describe: 用于 对 后台数据操作 后台返回的数据
 * Company:  杭州洞见科技有限公司（www.i3020.com）
 * <p>
 * Created by HeJinliang on 2017/4/13.
 */

public class StandardSimpleBean {

    /**
     * return_code : -400
     * msg : 操作失败
     */

    private String return_code;
    private String msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
