package com.i3020.mvpdemo01.viewpresenter.login;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.i3020.mvpdemo01.R;
import com.i3020.mvpdemo01.base.BaseActivity;
import com.i3020.mvpdemo01.ui.widget.WooxEditText;
import com.i3020.mvpdemo01.utils.HPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/25.
 */
public class LoginActivity extends BaseActivity<LoginContract.IView, LoginContract.AbstractPresenter> implements LoginContract.IView{


	@Bind(R.id.et_user_input)
	WooxEditText et_user_input;
	@Bind(R.id.et_psw_input)
	WooxEditText et_psw_input;
	@Bind(R.id.iv_show_psw)
	ImageView iv_show_psw;
	@Bind(R.id.btn_login)
	Button btn_login;
	@Bind(R.id.tv_register)
	TextView tv_register;
	@Bind(R.id.tv_forget_psw)
	TextView tv_forget_psw;

//	private boolean isShowPsw = false;//是否显示密码，默认不显示

	@Override
	protected int getLayoutResId() {
		return R.layout.activity_login;
	}

	@Override
	protected void initView() {
		ButterKnife.bind(this);
	}

	@Override
	public void initListener() {

	}

	@Override
	protected void initData() {
	}


	@Override
	protected LoginContract.AbstractPresenter createPresenter() {
		return new LoginPresenter(getApplicationContext());
	}




	@OnClick({R.id.iv_show_psw, R.id.btn_login, R.id.tv_register, R.id.tv_forget_psw})
	public void onViewClicked(View view) {
		if (!HPUtils.isFastClick()) {
			switch (view.getId()) {
				case R.id.iv_show_psw:

					setShowPassword();

					break;
				case R.id.btn_login:

					mPresenter.toLogin(getInputPhone(), getInputPassword());

					break;
				case R.id.tv_register:

					mPresenter.toRegister();

					Glide.with(mContext).load("jowjef").preload();
					break;
				case R.id.tv_forget_psw:

					mPresenter.toForgetPassword();

					HPUtils.showToast(mContext, "onViewClicked");


					break;
			}
		}
	}

	@Override
	public String getInputPhone() {
		return et_user_input.getText().toString().trim();
	}

	@Override
	public String getInputPassword() {
		return et_psw_input.getText().toString().trim();
	}

	@Override
	public void setShowPassword() {
		if (et_psw_input.getInputType() == EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
			iv_show_psw.setImageResource(R.mipmap.login_yanjing2);

			et_psw_input.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD | EditorInfo.TYPE_CLASS_TEXT);
			et_psw_input.setSelection(et_psw_input.length());
		} else {
			iv_show_psw.setImageResource(R.mipmap.login_yanjng);

			et_psw_input.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
			et_psw_input.setSelection(et_psw_input.length());
		}
	}


}
