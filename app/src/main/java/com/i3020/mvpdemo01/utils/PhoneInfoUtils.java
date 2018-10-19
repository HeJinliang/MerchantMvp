/*    
 * Company:  杭州洞见科技有限公司（www.i3020.com）
 * 
 * How much you pay, how much you will reap!
 * Nobody can casually succeed without working hard！
 *
 */
package com.i3020.mvpdemo01.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.i3020.mvpdemo01.config.AppConfig;

import java.text.SimpleDateFormat;

/**
 * Describe:  手机信息工具类
 * <p>
 * Author:  Mr.Woox Hunter
 * Time:    2016-03-16  17:01
 */
public class PhoneInfoUtils {


    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth(Context mContext) {
        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        return widthPixels;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight(Context mContext) {
        int heightPixels = mContext.getResources().getDisplayMetrics().heightPixels;
        return heightPixels;
    }

    /**
     * 获取设备的唯一标识 (唯一用户的标志 如果能够获取到手机sim卡的imsi就用这个做返回 否则使用唯一的设备ID：IMEI作为返回。)
     * @return
     */
    public static String getIMEI(Context mContext) {
    	TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        String result = AppConfig.IMSI;

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return result;
        }
        result = telephonyManager.getSubscriberId();//simsi
        if (TextUtils.isEmpty(result))
            //获取imei
            result = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(result))
            //获取手机号
            result = telephonyManager.getLine1Number();
        if (TextUtils.isEmpty(result))
            //SIM卡的序列号
            result = telephonyManager.getSimSerialNumber();
        if (TextUtils.isEmpty(result)) {
            result = "123456789987654321";
        }

        return result;
    }

    /**
     * 获取SIM卡的唯一标识
     * @return
     */
    public static String getIMSI(Context mContext) {

    	TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        String result = AppConfig.IMSI;

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return result;
        }
        result = telephonyManager.getSubscriberId();

        return result;
    }

    /**
     * 获取操作系统的版本
     * @return
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取网络状态 （wifi 为 20）
     * @return
     */
    public static String getNetWork(Context mContext){
        String netWorkStr = "";
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                netWorkStr = "20";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                int networkType = telephonyManager.getNetworkType();
                netWorkStr = Integer.toString(networkType);
            }
        }
        return netWorkStr;
    }

    /**
     * 获取嗨皮的版本名称
     * @return
     */
    public static String getVersionName(){
        return AppConfig.versionName2;
    }

    /**
     * 获取嗨皮的版本号
     * @return
     */
    public static String getVersionCode(Context mContext){
        String vc = "";
        PackageManager packageManager = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            vc = String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            LogUtils.e(e.toString());
        }
        return vc;
    }

    /**
     * 获取手机类型
     * @return
     */
    public static String getPhoneType(Context mContext){
    	TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        return String.valueOf(telephonyManager.getPhoneType());
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getPhoneModel(){
        return Build.MODEL;
    }

    /**
     * 获取系统日期
     * @return
     */
    public static String getSystemDate(){
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(timeMillis);
        return format;
    }

    /**
     * 利用反射获取状态栏高度 (px)
     * @return
     */
    public static int getStatusBarHeight(Context mContext) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
