package com.i3020.mvpdemo01.viewpresenter.main;

import android.content.Context;

import com.i3020.mvpdemo01.base.BasePresenter;
import com.i3020.mvpdemo01.base.BaseView;

/**
 * decribe:
 * create by HeJinliang on 2018/8/16
 */
public interface MainContract {

	interface IView extends BaseView{
	}


	abstract class AbstractPresenter extends BasePresenter<IView>{
		AbstractPresenter(Context mContext) {
			super(mContext);
		}

	}

}
