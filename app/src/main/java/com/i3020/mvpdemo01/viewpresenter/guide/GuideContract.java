package com.i3020.mvpdemo01.viewpresenter.guide;

import android.content.Context;

import com.i3020.mvpdemo01.base.BasePresenter;
import com.i3020.mvpdemo01.base.BaseView;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/23.
 */
public interface GuideContract {

	interface IView extends BaseView{

	}

	abstract class AbstractPresenter extends BasePresenter<IView>{
		AbstractPresenter(Context mContext) {
			super(mContext);
		}

	}

}
