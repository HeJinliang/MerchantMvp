package com.i3020.mvpdemo01.utils;

import android.util.Log;

import com.i3020.mvpdemo01.config.AppConfig;


public class LogUtils {

    public static void d(String msg){
        if (AppConfig.isOpenLog){
            if (msg.length() > 3500){
                //数据过多的话 Android Monitor 一次显示不完全，分批显示
                Log.d("<<<<<<haipi>>>>>>", msg.substring(0, 3500));
                d(msg.substring(3500));
            } else {
                Log.d("<<<<<<haipi>>>>>>", msg);
            }
        }
    }

    public static void d(String tag, String msg){
        if (AppConfig.isOpenLog){
            if (msg.length() > 3500){
                //数据过多的话 Android Monitor 一次显示不完全，分批显示
                Log.d(tag, msg.substring(0, 3500));
                d(msg.substring(3500));
            } else {
                Log.d(tag, msg);
            }
        }
    }

    public static void i(String msg){
        if (AppConfig.isOpenLog){
            if (msg.length() > 3500){
                //数据过多的话 Android Monitor 一次显示不完全，分批显示
                Log.i("<<<<<<haipi>>>>>>", msg.substring(0, 3500));
                i(msg.substring(3500));
            } else {
                Log.i("<<<<<<haipi>>>>>>", msg);
            }
        }
    }

    public static void i(String tag, String msg){
        if (AppConfig.isOpenLog){
            if (msg.length() > 3500){
                //数据过多的话 Android Monitor 一次显示不完全，分批显示
                Log.i(tag, msg.substring(0, 3500));
                i(tag, msg.substring(3500));
            } else {
                Log.i(tag, msg);
            }
        }
    }

    public static void w(String msg){
        if (AppConfig.isOpenLog){
            if (msg.length() > 3500){
                //数据过多的话 Android Monitor 一次显示不完全，分批显示
                Log.w("<<<<<<haipi>>>>>>", msg.substring(0, 3500));
                w(msg.substring(3500));
            } else {
                Log.w("<<<<<<haipi>>>>>>", msg);
            }
        }
    }

    public static void w(String tag, String msg){
        if (AppConfig.isOpenLog){
            if (msg.length() > 3500){
                //数据过多的话 Android Monitor 一次显示不完全，分批显示
                Log.w(tag, msg.substring(0, 3500));
                w(tag, msg.substring(3500));
            } else {
                Log.w(tag, msg);
            }
        }
    }

    public static void e(String msg) {
        if (AppConfig.isOpenLog){
            if (msg.length() > 3500){
                //数据过多的话 Android Monitor 一次显示不完全，分批显示
                Log.e("<<<<<<haipi>>>>>>", msg.substring(0, 3500));
                e(msg.substring(3500));
            } else {
                Log.e("<<<<<<haipi>>>>>>", msg);
            }
        }
    }

    public static void e(String tag, String msg){
        if (AppConfig.isOpenLog){
            if (msg.length() > 3500){
                //数据过多的话 Android Monitor 一次显示不完全，分批显示
                Log.e(tag, msg.substring(0, 3500));
                e(tag, msg.substring(3500));
            } else {
                Log.e(tag, msg);
            }
        }
    }
}
