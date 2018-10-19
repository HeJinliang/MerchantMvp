package com.i3020.mvpdemo01.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.i3020.mvpdemo01.R;

/**
 * decribe: 具有断网布局的FrameLayout
 * create by HeJinliang on 2018/8/24
 */
public class NoNetFrameLayout extends FrameLayout {


	private Context mContext;
	private View view404;

	public NoNetFrameLayout(@NonNull Context context) {
		super(context);
		this.mContext = context;
	}

	public NoNetFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init();
	}

	private void init() {
		view404 = LayoutInflater.from(mContext).inflate(R.layout.view_net_error, null, false);
		Button btnRefresh = view404.findViewById(R.id.btn_refresh);
		btnRefresh.setOnClickListener(v -> {
			if (onClickListener != null){
				onClickListener.onClick(v);
			}
		});

		addView(view404);

		view404.setVisibility(View.GONE);
	}

	/**
	 * 设置404显示状态
	 *
	 * @param visiable true 显示
	 */
	public void set404Visiable(boolean visiable) {
		if (visiable) {
			view404.setVisibility(View.VISIBLE);
			bringChildToFront(view404);
		} else {
			view404.setVisibility(View.GONE);
		}
	}


	private OnClickListener onClickListener;

	/**
	 * 设置断网重连事件
	 * @param onClickListener
	 */
	public void setRefreshListener(OnClickListener onClickListener){
		this.onClickListener = onClickListener;
	}

	/**
	 * 设置断网布局的边距 （px）
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	public void set404ViewMargin(int top, int bottom, int left, int right){
		FrameLayout.LayoutParams layoutParams = (LayoutParams) view404.getLayoutParams();
		layoutParams.setMargins(top, bottom, left, right);
		view404.setLayoutParams(layoutParams);
	}


}
