package com.i3020.mvpdemo01.config;

/**
 * describe: SP的配置文件
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/9/1.
 */

public class SPConfig {


    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  sp文件名  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * APP配置信息
     */
    public static final String SP_NAME_CONFIG = "config";



    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  sp key名  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 上次弹非强制更新的时间
     */
    public static final String SP_KEY_CHECK_DATE = "checkDate";

    /**
     * 登陆状态
     */
    public static final String SP_KEY_LOGIN_STATUS = "loginStatus";

    /**
     * 账号
     */
    public static final String SP_KEY_UESR_NAME = "userName";


    /**
     * 用户ID
     */
    public static final String SP_KEY_UESR_ID = "userId";

    /**
     * 用户token
     */
    public static final String SP_KEY_UESR_TOKEN = "userToken";

    /**
     * 友盟推送 前端设备的唯一标识
     */
    public static final String SP_KEY_UMEMG_TOKEN = "uMengToken";

    /**
     * 门店id
     */
    public static final String SP_KEY_STORE_ID = "storeId";

    /**
     * 门店名称
     */
    public static final String SP_KEY_STORE_NAME = "storeName";

    /**
     * 通知提示音
     */
    public static final String SP_KEY_NOTICE_VOICE_SWITCH = "noticeVoiceSwitch";

    /**
     * 钱包安全密码错误时间戳
     */
    public static final String SP_KEY_SAFE_PSW_ERROR = "safePswError";

    /**
     * 服务器环境切换
     */
    public static final String SP_KEY_ENVIRONMENT = "environment";

    /**
     * 洞见二维码匹配字段
     */
    public static final String SP_KEY_SCAN_DJ_URL = "scan_dj_url";


	/**
	 * 添加账户的保存的信息
	 */
	public static final String SP_KEY_ADD_ACCOUNT_JSON = "add_account_json";
}
