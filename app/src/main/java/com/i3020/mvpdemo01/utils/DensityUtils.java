package com.i3020.mvpdemo01.utils;

import android.content.Context;
import android.util.TypedValue;

import com.i3020.mvpdemo01.config.Variable;

/**
 * describe: 常用单位转换工具类
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/7/18.
 */
public class DensityUtils {
    private DensityUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     *
     * @param context
     * @param
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpVal * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

	/**
	 * 1080P上的像素转当前手机的像素
	 * @param pxVal
	 * @return
	 */
	public static float px2px(float pxVal){
		return Variable.screenScale*pxVal;
	}

}
