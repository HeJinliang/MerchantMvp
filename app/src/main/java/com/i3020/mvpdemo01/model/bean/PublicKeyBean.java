package com.i3020.mvpdemo01.model.bean;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/7/4.
 */

public class PublicKeyBean {

    /**
     * data : {"public_key":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnskuACNHjQTq92XtJ0dsQldLuwb1HuXIrbT4eOXjhT/2XaxofZLPMg0O281gt1vDUAIwdzbbq3JSTxqbO97cMREf2e1Q8GZarIKF/zWnNekc+9L3lqZbA5eOwsfNIYMqq5XiQKzQ6/j5k/zpXu039KzzL6RXEZb0mb+OP/mXzKQIDAQAB"}
     * return_code : 0
     * msg : 公钥获取成功
     */

    private DataBean data;
    private String return_code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    public static class DataBean {
        /**
         * public_key : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnskuACNHjQTq92XtJ0dsQldLuwb1HuXIrbT4eOXjhT/2XaxofZLPMg0O281gt1vDUAIwdzbbq3JSTxqbO97cMREf2e1Q8GZarIKF/zWnNekc+9L3lqZbA5eOwsfNIYMqq5XiQKzQ6/j5k/zpXu039KzzL6RXEZb0mb+OP/mXzKQIDAQAB
         */

        private String public_key;

        public String getPublic_key() {
            return public_key;
        }

        public void setPublic_key(String public_key) {
            this.public_key = public_key;
        }
    }
}
