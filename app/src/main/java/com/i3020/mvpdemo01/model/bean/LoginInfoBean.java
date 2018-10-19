package com.i3020.mvpdemo01.model.bean;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/9/21.
 */

public class LoginInfoBean {
    /**
     * data : {"token":"YGUv9ok1iwcJk7BYjdBOjLINoSlEJObwFvhNZ3vDlaCkT5YItGSMPs3rzqGDIcwmZSexna8zqmudJqEB+ryQqfqcFfikneIGXf0djZVaaBFL0DIu39P7pIGJvVh8mf3CHufy/VWPajBGe8dWAzalvfrPDDEWA0djSYBn3UGRXJY=","uid":"93"}
     * msg : 登录成功
     * return_code : 0
     */

    private DataBean data;
    private String msg;
    private String return_code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public static class DataBean {
        /**
         * token : YGUv9ok1iwcJk7BYjdBOjLINoSlEJObwFvhNZ3vDlaCkT5YItGSMPs3rzqGDIcwmZSexna8zqmudJqEB+ryQqfqcFfikneIGXf0djZVaaBFL0DIu39P7pIGJvVh8mf3CHufy/VWPajBGe8dWAzalvfrPDDEWA0djSYBn3UGRXJY=
         * uid : 93
         */

        private String token;
        private String uid;
        private String store_id;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }


//    /**
//     * board : 1
//     * change_gift : 1
//     * daily : 1
//     * data : {"uid":"118"}
//     * gift_img : 1
//     * msg : 登录成功
//     * return_code : 0
//     */
//
//    private String board;
//    private String change_gift;
//    private String daily;
//    private DataBean data;
//    private String gift_img;
//    private String msg;
//    private String return_code;
//
//    public String getBoard() {
//        return board;
//    }
//
//    public void setBoard(String board) {
//        this.board = board;
//    }
//
//    public String getChange_gift() {
//        return change_gift;
//    }
//
//    public void setChange_gift(String change_gift) {
//        this.change_gift = change_gift;
//    }
//
//    public String getDaily() {
//        return daily;
//    }
//
//    public void setDaily(String daily) {
//        this.daily = daily;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public String getGift_img() {
//        return gift_img;
//    }
//
//    public void setGift_img(String gift_img) {
//        this.gift_img = gift_img;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getReturn_code() {
//        return return_code;
//    }
//
//    public void setReturn_code(String return_code) {
//        this.return_code = return_code;
//    }
//
//    public static class DataBean {
//        /**
//         * uid : 118
//         */
//
//        private String uid;
//
//        public String getUid() {
//            return uid;
//        }
//
//        public void setUid(String uid) {
//            this.uid = uid;
//        }
//    }
}
