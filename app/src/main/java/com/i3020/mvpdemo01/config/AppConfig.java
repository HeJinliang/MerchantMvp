package com.i3020.mvpdemo01.config;

import android.text.TextUtils;

import com.i3020.mvpdemo01.utils.LogUtils;


/**
 * describe: APP配置信息
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/9/1
 */

public class AppConfig {

    public static boolean isDebug = false;//是否是测试版本
    public static String debugType = "";//（前端手动切换环境） 0 开发服 1 测试服A 2 测试服B 3 线上测试服 4 线上服  为空则由用户自己切换服务器
    public static String isDebugData = "0";//（后台控制）后台控制切换测试服、正式服，  2 为测试服 B    3 为线上测试服
    public static boolean isOpenLog = true;//是否开启打印LOG日志
    public static String versionName = "V1.5.5";//版本名称-关于界面显示 (线上版本要把日期去掉)
    public static String versionName2 = "V1.5.5.0815r";//传递给后台使用版本名称
    public static String SCAN_DJ_URL = "";//洞见扫描二维码的url

//    public static int updateFaile = 0;//是否因为权限问题强更失败  5 是

    //tcp 通信 start

//    public final static String SOCKET_SERVER = "60.191.46.50";//TCP通讯地址
    public final static int SOCKET_PORT = 4470;//TCP端口号
    //TCP 渠道
    public final static String channel = "0oxoho7qo4pxxk6yzwdx5zcp1mrbqenz";
    //TCP key
    public final static String key = "uoqluwkiiq3b5b6bvhs2e910qj84d8ev1tzsuyxa7iwdt1m132kwq4qihpew6r26";
    //TCP 公钥
    public final static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClBR49kgFcyCJmHuf1+9UIu4/7O0AqyH3qq9IiIElrmZ43T+DnU31vMHhKgDBo139ApE0nycLR3TuHnMqBUgp5nCfLiiLhnD6xkUBez2qju9A7RlWC6V3pLCWlGVWFUHhPkvc16hfTLpmz0+Dcq9k6y3ZVRroEiWKmGnRCubZquQIDAQAB";
    //心跳包发送间隔时间
    public final static int SOCKET_HEART_SECOND =3;
    //广播调频
    public final static String BC = "BC";
    // 默认timeout 时间 60s
    public final static int SOCKET_TIMOUT = 60 * 1000;

    public final static int SOCKET_READ_TIMOUT = 15 * 1000;
    //如果没有连接无服务器。读线程的sleep时间
    public final static int SOCKET_SLEEP_SECOND = 3;
    //tcp 通信 end

    /**
     * 手机imsi默认值
     */
    public final static String IMSI = "123456789987654321";


    /**
     * 获取 数据库服务器 ip
     * @return
     */
    public static String getBaseIp(){

        if (isDebug){
            if ("0".equals(debugType)){
                return IP_DEV;//开发服
            } else if ("1".equals(debugType)){
                return IP_TEST_A;//测试服A
            } else if ("2".equals(debugType)){
                return IP_TEST_B;//测试服B
            } else if("3".equals(debugType)){
                return IP_ONLINE_TEST;//线上测试服
            }else {
                return IP_ONLINE;//线上服
            }
        } else {
            if ("2".equals(isDebugData)){
                return IP_TEST_B;//测试B ip
            } else if ("3".equals(isDebugData)){
                return IP_ONLINE_TEST;//线上测试ip
            } else {
                return IP_ONLINE;//线上ip
            }
        }
    }

    /**
     * 获取推送服务器IP
     * @return
     */
    public static String getPushIp(){
        if (isDebug){
            if ("0".equals(debugType)){
                return IP_PUSH_DEV;//开发服
            } else if ("1".equals(debugType)){
                return IP_PUSH_TEST_A;//测试服A
            } else if ("2".equals(debugType)){
                return IP_PUSH_TEST_B;//测试服B
            } else if("3".equals(debugType)){
                return IP_PUSH_ONLINE_TEST;//线上测试服
            }else {
                return IP_PUSH_ONLINE;//推动服务器线上服
            }
        } else {
            if ("2".equals(isDebugData)){
                return IP_PUSH_TEST_B;//测试B ip
            } else if ("3".equals(isDebugData)){
                return IP_PUSH_ONLINE_TEST;//线上测试ip
            } else {
                return IP_PUSH_ONLINE;//推动服务器线上ip
            }
        }

    }



    /**
     * 获取websocket通信地址(没端口号)
     * @return
     */
    public static String getSocket(){
        return "ws://"+getBaseIp()+":";
//        if (isDebug){
//            if ("0".equals(debugType)){
//                return URL_SOCKET_IP_TEST;//开发测试服
//            } else if ("1".equals(debugType)){
//                return URL_SOCKET_IP_TEST_2;//测试测试服
//            } else if ("2".equals(debugType)){
//                return URL_SOCKET_IP_ONLINE_TEST;//线上测试服
//            } else {
//                return URL_SOCKET_IP_ONLINE;//线上服
//            }
//        } else {
//            if ("2".equals(isDebugData)){
//                return URL_SOCKET_IP_TEST;//测试ip
//            } else if ("3".equals(isDebugData)){
//                return URL_SOCKET_IP_ONLINE_TEST;//线上测试ip
//            } else {
//                return URL_SOCKET_IP_ONLINE;//正式ip
//            }
//        }
    }

//    /**
//     * 开发服 socket通信IP
//     */
////    public static final String URL_SOCKET_IP_TEST = "ws://60.191.46.50:";
//    public static final String URL_SOCKET_IP_TEST = "ws://dev.hahaipi.com:";
//    /**
//     * 测试服 socket通信IP
//     */
//    public static final String URL_SOCKET_IP_TEST_2 = "ws://47.97.23.104:";
//    /**
//     * 线上测试服 socket通信IP
//     */
//    public static final String URL_SOCKET_IP_ONLINE_TEST = "ws://118.178.182.196:";
//    /**
//     * 线上服 socket通信IP
//     */
//    public static final String URL_SOCKET_IP_ONLINE = "ws://114.55.6.175:";


    /**
     * 获取BaseUrl
     * @return
     */
    public static String getBaseUrl (){

        return "http://"+getBaseIp()+":8228/index.php/";

//        if (isDebug){
//            if ("0".equals(debugType)){
//                return URL_BASE_TEST;//开发测试服
//            } else if ("1".equals(debugType)){
//                return URL_BASE_TEST_2;//测试测试服
//            } else if ("2".equals(debugType)){
//                return URL_BASE_ONLINE_TEST;//线上测试服
//            } else {
//                return URL_BASE_ONLINE;//线上服
//            }
//        } else {
//            if ("2".equals(isDebugData)){
//                return URL_BASE_TEST;//测试URL
//            } else if ("3".equals(isDebugData)){
//                return URL_BASE_ONLINE_TEST;//线上测试URL
//            } else {
//                return URL_BASE_ONLINE;//正式URLmd
//            }
//        }
    }

    /**
     * 获取登录注册忘记密码 BaseUrl
     * @return
     */
    public static String getBaseLoginUrl (){

        return "http://"+getBaseIp()+":8114/index.php/";

//        if (isDebug){
//            if ("0".equals(debugType)){
//                return URL_LOGIN_TEST;//开发测试服
//            } else if ("1".equals(debugType)){
//                return URL_LOGIN_TEST_2;//测试测试服
//            } else if ("2".equals(debugType)){
//                return URL_LOGIN_ONLINE_TEST;//线上测试服
//            } else {
//                return URL_LOGIN_ONLINE;//线上服
//            }
//
//        } else {
//            if ("2".equals(isDebugData)){
//                return URL_LOGIN_TEST;
//            } else if ("3".equals(isDebugData)){
//                return URL_LOGIN_ONLINE_TEST;//线上测试URL
//            } else {
//                return URL_LOGIN_ONLINE;
//            }
//        }
    }

    /**
     * 扫描二维码的url
     * @return
     */
    public static String getScanUrl (){

        LogUtils.e("url:"+SCAN_DJ_URL);

        if (TextUtils.isEmpty(SCAN_DJ_URL)){
            return URL_DJ_SCANN_ONLINE;
        } else {
            return SCAN_DJ_URL;
        }
//        if (isDebug){
//            if ("0".equals(debugType)){
//                return URL_SCAN_DEV;//开发服
//            } else if ("1".equals(debugType)){
//                return URL_SCAN_TEST_A;//测试服 A
//            } else if("2".equals(debugType)){
//                return URL_SCAN_TEST_B;//测试服 B
//            } else if ("3".equals(debugType)){
//                return URL_SCAN_ONLINE_TEST;//线上测试服
//            } else {
//                return URL_SCANN_ONLINE;//线上服
//            }
//        } else {
//            //二维码扫描的url由后台控制
//            if ("2".equals(isDebugData)){
//                return URL_SCAN_TEST_B;
//            } else if ("3".equals(isDebugData)){
//                return URL_SCAN_ONLINE_TEST;//线上测试URL
//            } else {
//                return URL_SCANN_ONLINE;
//            }
//        }
    }



//--------------------------------------------------------------------------------------------------
//  推送服务器 IP  start
//--------------------------------------------------------------------------------------------------
    /**
     * 推送服务器 开发服IP地址
     */
    public static final String IP_PUSH_DEV = "dev.hahaipi.com";

    /**
     * 推送服务器 测试服A IP地址
     */
    public static final String IP_PUSH_TEST_A = "testpre.hahaipi.com";

    /**
     * 推送服务器 测试服B IP地址
     */
    public static final String IP_PUSH_TEST_B = "testpro.hahaipi.com";

    /**
     * 推送服务器 线上测试服IP地址
     */
    public static final String IP_PUSH_ONLINE_TEST = "118.178.182.196";

    /**
     * 推送服务器 线上服IP地址
     */
    public static final String IP_PUSH_ONLINE = "push.api.i3020.com";

//--------------------------------------------------------------------------------------------------
//  推送服务器 IP  end
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
//  数据服务器 IP  start
//--------------------------------------------------------------------------------------------------
    /**
     * 开发服IP地址
     */
    public static final String IP_DEV = "dev.hahaipi.com";

    /**
     * 测试服A IP地址
     */
    public static final String IP_TEST_A = "testpre.hahaipi.com";

    /**
     * 测试服B IP地址
     */
    public static final String IP_TEST_B = "testpro.hahaipi.com";

    /**
     * 线上测试服IP地址
     */
    public static final String IP_ONLINE_TEST = "118.178.182.196";

    /**
     * 线上服IP地址
     */
    public static final String IP_ONLINE = "api.hahaipi.com";

//--------------------------------------------------------------------------------------------------
//  数据服务器 IP  end
//--------------------------------------------------------------------------------------------------


//--------------------------------------------------------------------------------------------------
//  扫码匹配URL  start
//--------------------------------------------------------------------------------------------------

    /**
     * ScanURL 洞见 线上
     */
    public static final String URL_DJ_SCANN_ONLINE = "http://play.hahaipi.com/index.php/pay?did=";

//    /**
//     * ScanURL  开发
//     */
//    public static final String URL_SCAN_DEV = "http://dev.hahaipi.com/pay/index.php/pay?did=";
//
//    /**
//     * ScanURL  测试服
//     */
//    public static final String URL_SCAN_TEST_A = "http://testpre.hahaipi.com/pay/index.php/pay?did=";
//
//    /**
//     * ScanURL  测试服B
//     */
//    public static final String URL_SCAN_TEST_B = "http://testpro.hahaipi.com/pay/index.php/pay?did=";
//
//    /**
//     * ScanURl  线上测试
//     */
//    public static final String URL_SCAN_ONLINE_TEST = "http://test.hahaipi.com/pay/index.php/pay?did=";
//    /**
//     * ScanURL  线上
//     */
//    public static final String URL_SCANN_ONLINE = "http://pay.hahaipi.com/index.php/pay?did=";

//--------------------------------------------------------------------------------------------------
//  扫码匹配URL  end
//--------------------------------------------------------------------------------------------------


    /**
     * 登录相关URL 线上
     */
    public static final String URL_LOGIN_ONLINE = "http://api.hahaipi.com:8114/index.php/";






}
