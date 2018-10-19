package com.i3020.mvpdemo01.config;

/**
 * describe: 公共变量
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/7/3.
 */

public class Variable {
    public static String mm = "";//手机型号
    public static String ai = "1003";//应用ID
    public static String vc = "";//版本号
    public static String vn = "";//版本名称--上传接口使用
    /**
     * gf，bd，360，wdj，andr，yyb，gp，mi，ka；分别表示：官方，百度助手，360手机助手，豌豆荚，安卓市场，腾讯应用宝，GooglePlay，小米商城, 酷安市场。
     */
    public static String sv = "ka";//渠道
    public static String imei = "";//手机IMEI
    public static String imsi = "";//手机IMSI
    public static String dt = "0";//设备类型   手机 0
    public static String os = "1";//操作系统  1-Android
    public static String osv = "";//操作系统版本
    public static String w = "";//屏幕宽度
    public static String h = "";//屏幕高度
    public static String ct = "";//当前日期
    public static String nt = "";//网络类型
    public static String uid = "";//用户ID
    public static String cpn = "1";
    public static String Token = "";//登陆用 token

    public static String storeId = "";//门店ID
    public static String storeName = "";//门店名称

    public static boolean LOGIN_BOOLEAN = false;//是否登陆

    public static float screenScale = (float) 1.1;//当前手机屏幕宽度和1080的比值
    public static int status_bar_height;//状态栏的高度
    public static boolean isChangeEnvironment = false;//是否切换了 服务器环境

    public static String sessionID = "";//推送 会话ID
    public static String pushToken = "";//服务器端用rsa私钥加密的token，连接方可用渠道注册时获取的公钥进行解密

    /**
     * 代码生成公钥失败，则使用该公钥、私钥
     */
    public static String MyPublicKey =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDmUSQ8UVt3HDwdvqUdS8RLcwIY" + "\r" +
                    "DtxyG3Ov7d0Qvmxy506o2YajQZYcviwjwhJLaURCOHsiny1CB/P+edPN9DfqWumQ" + "\r" +
                    "lrCa4F1mZzh8DjdV9dFembYgT61pchhHUH4nZL363PJIlghQCWodj7C2Ha8sXDtl" + "\r" +
                    "ZUdWySYpOqs/H+P3SQIDAQAB";

    public static String MyPrivateKey
            = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOZRJDxRW3ccPB2+" +// "\r" +
            "pR1LxEtzAhgO3HIbc6/t3RC+bHLnTqjZhqNBlhy+LCPCEktpREI4eyKfLUIH8/55" + //"\r" +
            "0830N+pa6ZCWsJrgXWZnOHwON1X10V6ZtiBPrWlyGEdQfidkvfrc8kiWCFAJah2P" + //"\r" +
            "sLYdryxcO2VlR1bJJik6qz8f4/dJAgMBAAECgYEAnwlMojHznK3sfQWV8kLNSuQh" + //"\r" +
            "gAdTBaAbJFJ1ogg7zqQWVtXN+JFjXHmc9Rwfkd8geTGTJXcQw1XGj+QD82U97dBQ" +// "\r" +
            "+V+WxIuIi+R/j6i78l/5qQ6CySB8qDQ8ITEmY+WR3NEAXKmtwLEWz9l0UDLOAiHd" + //"\r" +
            "kRbDOVsFSG7Aqi87IAECQQD6u4bvh28uaNNFxGB8Owq0bIWMulxwQc9Eu8NVuKcF" + //"\r" +
            "PBQWWQT1KXqVcpivIbXr9EHnr1KWeIuRngq9pOhtt+YBAkEA6yfRGlMK8IyRsv+1" + //"\r" +
            "j/OE99pwWctmck9ki09tt8XYpPzQTSvK6J728uC9I8jz3fjXybs5dzPNxcFe4Cx4" + //"\r" +
            "+k1hSQJAKvHdrb8buu43aZa2E/Ek5zxDL5Rn6s5KyvICBfenm8zrf9xlbfxZw6kq" + //"\r" +
            "H6z9dNmURDOSSLWC6kEQg+Tv7oL2AQJBAI2/zaMxpamFAprE21wvrdp9+TWXj2Se" + //"\r" +
            "iPwU4A1kHIivwJyUJlb8491aLYkxrBcE1obK7gP13Tc0rg0xoac4F8kCQBxQVnbW" + //"\r" +
            "zbMwuMG+ef3ZylfOZIjTKUii2RtOeg5QadaBIQgGJmZB85zWNw9M+OK2AjMzSf0M" + //"\r" +
            "zq2sAGAAxPUS/iQ=";

    public static String uMengDeviceToken = "";//友盟推送 设备唯一标识
    public static String uMengPushTag = "";//友盟推送打开指定界面  1、嗨皮签到  2、轮播图 3、门店页面 4、话题页面 5、优惠券页面 6、通知页面
    public static String uMengPushContent = "";//友盟推送打开指定界面传递的数据  轮播图 ：代表轮播图的下标  话题页面 ：代表话题内容


    public static int badHeartbeatCount = 0;//未回复的心跳帧次数
}
