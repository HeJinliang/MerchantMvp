package com.i3020.mvpdemo01.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.i3020.mvpdemo01.config.AppConfig;
import com.i3020.mvpdemo01.config.SPConfig;
import com.i3020.mvpdemo01.config.Variable;
import com.i3020.mvpdemo01.utils.LogUtils;

/**
 * describe: 存贮、获取SP文件的工具类
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/7/3.
 */

public class SPUtils {

    /**
     * 存贮上次弹出"非强制更新"弹窗的时间
     * @param mContext
     * @param updateNumOld  弹出"非强制更新"弹窗的时间
     */
    public static void setUpdateNoForceLastTime(Context mContext, int updateNumOld){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putInt(SPConfig.SP_KEY_CHECK_DATE+ Variable.vn, updateNumOld)
                .apply();
    }

    /**
     * 获取上次弹窗"非强制更新"弹窗的时间
     * @param mContext
     * @param defaultNum  默认时间
     * @return
     */
    public static int getUpdateNoForceLastTime(Context mContext, int defaultNum){
        int anInt = defaultNum;
        try{
            //在v2.1.2.0223r版本崩溃过，抛出异常
            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
            anInt = sharedPreferences.getInt(SPConfig.SP_KEY_CHECK_DATE+ Variable.vn, defaultNum);
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e(e.toString());
        }
        return anInt;
    }

    /**
     * 存储当前day是否要显示"非强制更新"弹窗    ———— 一天只显示一次
     * @param mContext
     * @param currentDay   当前日期
     * @param isShowCurrent
     */
    public static void setIsShowUpdateNoForceCurrent(Context mContext, int currentDay, boolean isShowCurrent){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(String.valueOf(currentDay), isShowCurrent)
                .apply();
    }

    /**
     * 获取当前day是否要显示"非强制更新"弹窗  ———— 一天只显示一次
     * @param mContext
     * @param currentDay
     * @return
     */
    public static boolean getIsShowUpdateNoForceCurrent(Context mContext, int currentDay){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        boolean aBoolean = sharedPreferences.getBoolean(String.valueOf(currentDay), true);
        return aBoolean;
    }

//    /**
//     * 存储登陆状态
//     * @param mContext
//     * @param isLogined
//     * @return
//     */
//    public static void setIsLogined(Context mContext, boolean isLogined){
//        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
//                .edit()
//                .putBoolean(SPConfig.SP_KEY_LOGIN_STATUS, isLogined)
//                .apply();
//    }
//
//    /**
//     * 获取登陆状态
//     * @param mContext
//     * @return
//     */
//    public static boolean getIsLogined(Context mContext){
//        boolean isLogined = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
//                .getBoolean(SPConfig.SP_KEY_LOGIN_STATUS, false);
//        return isLogined;
//    }

    /**
     * 存储登陆账号
     * @param mContext
     * @param userName
     */
    public static void setLoginUser(Context mContext, String userName){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putString(SPConfig.SP_KEY_UESR_NAME, userName)
                .apply();
    }

    /**
     * 获取登陆账号
     * @param mContext
     * @return
     */
    public static String getLoginUser(Context mContext){
        String userName = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .getString(SPConfig.SP_KEY_UESR_NAME, "");
        return userName;
    }

    /**
     * 存储用户ID
     * @param mContext
     * @param userId
     */
    public static void setUid(Context mContext, String userId){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putString(SPConfig.SP_KEY_UESR_ID, userId)
                .apply();
    }

    /**
     * 获取用户ID
     * @param mContext
     * @return
     */
    public static String getUid(Context mContext){
        String userId = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .getString(SPConfig.SP_KEY_UESR_ID, "");
        return userId;
    }



    /**
     * 设置 token值
     * @param mContext
     * @param token
     */
    public static void setToken(Context mContext, String token){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putString(SPConfig.SP_KEY_UESR_TOKEN, token)
                .apply();
    }

    /**
     * 获取 token值
     * @param mContext
     * @return
     */
    public static String getToken(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(SPConfig.SP_KEY_UESR_TOKEN, "");
        return token;
    }

    /**
     * 设置门店ID
     * @param mContext
     * @param storeId
     */
    public static void setStoreId(Context mContext, String storeId){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putString(SPConfig.SP_KEY_STORE_ID + Variable.uid, storeId)
                .apply();
    }

    /**
     * 获取 门店ID
     * @param mContext
     * @return
     */
    public static String getStoreId(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        String store_id = sharedPreferences.getString(SPConfig.SP_KEY_STORE_ID + Variable.uid, "");
        return store_id;
    }

    /**
     * 设置门店名称
     * @param mContext
     * @param storeName
     */
    public static void setStoreName(Context mContext, String storeName){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putString(SPConfig.SP_KEY_STORE_NAME + Variable.uid, storeName)
                .apply();
    }

    /**
     * 获取 门店名称
     * @param mContext
     * @return
     */
    public static String getStoreName(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(SPConfig.SP_KEY_STORE_NAME + Variable.uid, "");
        return token;
    }

    /**
     * 设置 友盟推送deviceToken值
     * @param mContext
     * @param token
     */
    public static void setDeviceToken(Context mContext, String token){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putString(SPConfig.SP_KEY_UMEMG_TOKEN, token)
                .apply();
    }

    /**
     * 获取 友盟推送deviceToken值
     * @param mContext
     * @return
     */
    public static String getDeviceToken(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(SPConfig.SP_KEY_UMEMG_TOKEN, "");
        return token;
    }

    /**
     * 设置 通知提示音是否开启
     * @param mContext
     * @param isOpen
     */
    public static void setNoticeVoice(Context mContext, boolean isOpen){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(SPConfig.SP_KEY_NOTICE_VOICE_SWITCH, isOpen)
                .apply();
    }

    /**
     * 获取 是否开启通知提示音
     * @param mContext
     * @return
     */
    public static boolean getNoticeVoice(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        boolean isOpen = sharedPreferences.getBoolean(SPConfig.SP_KEY_NOTICE_VOICE_SWITCH, true);
        return isOpen;
    }

    /**
     * 设置 安全密码错误的时间戳
     * @param mContext
     * @param content
     */
    public static void setSafePswErrorInfo(Context mContext, String content) {
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putString(SPConfig.SP_KEY_SAFE_PSW_ERROR, content)
                .apply();
    }

    /**
     * 设置 安全密码错误的时间戳
     * @param mContext
     * @return
     */
    public static String getSafePswErrorInfo(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        String info = sharedPreferences.getString(SPConfig.SP_KEY_SAFE_PSW_ERROR, "");
        return info;
    }


    /**
     * 保存 选择的 服务器 环境
     * @param mContext
     * @param debugType 0 研发 1 测试  2 线上测试
     */
    public static void setSaveEnvironment(Context mContext, int debugType){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putInt(SPConfig.SP_KEY_ENVIRONMENT+ AppConfig.versionName2, debugType)
                .apply();
    }

    /**
     * 获取 选择的 服务器 环境
     * @param mContext
     * @return
     */
    public static int getSaveEnvironment(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        int debugType = sharedPreferences.getInt(SPConfig.SP_KEY_ENVIRONMENT+ AppConfig.versionName2, 1);
        return debugType;
    }

    /**
     * 洞见二维码内容
     * @param mContext
     */
    public static void setScanDjUrl(Context mContext, String SCAN_DJ_URL){
        mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
                .edit()
                .putString(SPConfig.SP_KEY_SCAN_DJ_URL, SCAN_DJ_URL)
                .apply();
    }

    /**
     * 洞见二维码内容
     * @return
     */
    public static String getScanDjUrl(Context mContext){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
        String SCAN_DJ_URL = sharedPreferences.getString(SPConfig.SP_KEY_SCAN_DJ_URL, "");
        return SCAN_DJ_URL;
    }

	/**
	 * 保存 添加账户 信息
	 * @param mContext
	 */
	public static void setAddAccountInfo(Context mContext, String json){
		mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE)
				.edit()
				.putString(SPConfig.SP_KEY_ADD_ACCOUNT_JSON, json)
				.apply();
	}

	/**
	 * 获取 添加账户 信息
	 * @return
	 */
	public static String getAddAccountInfo(Context mContext){
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(SPConfig.SP_NAME_CONFIG, Context.MODE_PRIVATE);
		String json = sharedPreferences.getString(SPConfig.SP_KEY_ADD_ACCOUNT_JSON, "");
		return json;
	}


}
