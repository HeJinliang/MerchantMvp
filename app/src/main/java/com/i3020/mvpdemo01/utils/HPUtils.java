package com.i3020.mvpdemo01.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

/**
 * describe: 嗨皮工具类
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/7/3.
 */

public class HPUtils {

    private static Toast toast;

    /**
     * 瞬时吐司
     * @param mContext
     * @param msg
     */
    public static void showToast(Context mContext, String msg){

        if (toast == null){
            toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 判断某个Activity是否在前台显示
     * @param context
     * @param className
     * @return
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName
     *            是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(400);
        if (myList.size() <= 0) {
            return false;
        }

        LogUtils.i("服务数量："+myList.size());
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }


	private static final int MIN_DELAY_TIME= 500;  // 两次点击间隔不能少于1000ms
	private static long lastClickTime;

	/**
	 * 防止重复点击 （0.5S）
	 * @return
	 */
	public static boolean isFastClick() {
		return isFastClick(MIN_DELAY_TIME);
	}

	/**
	 * 防止重复点击
	 * @param interval
	 * @return
	 */
	public static boolean isFastClick(long interval){
		boolean flag = true;
		long currentClickTime = System.currentTimeMillis();
		if ((currentClickTime - lastClickTime) >= interval) {
			flag = false;
		}
		lastClickTime = currentClickTime;
		return flag;
	}

}
