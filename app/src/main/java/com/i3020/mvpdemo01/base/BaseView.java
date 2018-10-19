package com.i3020.mvpdemo01.base;

import android.content.Intent;
import android.os.Bundle;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/16.
 */
public interface BaseView {

	/**
	 * 显示等待框
	 */
	void showLoading(boolean isShow);

	/**
	 * 展示断网布局
	 * @param isNerError
	 */
	void set404Visiable(boolean isNerError);

	/**
	 * 设置断网布局margin
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	void set404ViewMargin(int top, int bottom, int left, int right);

	/**
	 * 开启一个新的Acitivty
	 * @param intent
	 * @param requestCode
	 */
	void startActivity(Intent intent, int requestCode);

	/**
	 * 关闭当前activity
	 */
	void finishActivity();

	/**
	 * 获取bundle数据
	 */
	Bundle getBundle();

}
