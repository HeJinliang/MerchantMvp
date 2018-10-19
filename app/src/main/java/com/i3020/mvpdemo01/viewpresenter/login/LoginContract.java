package com.i3020.mvpdemo01.viewpresenter.login;

import android.content.Context;

import com.i3020.mvpdemo01.base.BasePresenter;
import com.i3020.mvpdemo01.base.BaseView;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/25.
 */
public interface LoginContract{

	interface IView extends BaseView {

		String getInputPhone();

		String getInputPassword();

		void setShowPassword();

	}

	abstract class AbstractPresenter extends BasePresenter<LoginContract.IView> {
		AbstractPresenter(Context mContext) {
			super(mContext);
		}

		abstract void toLogin(String account, String password);

		abstract void toRegister();

		abstract void toForgetPassword();
	}

}
