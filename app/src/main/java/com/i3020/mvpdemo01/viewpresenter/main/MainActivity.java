package com.i3020.mvpdemo01.viewpresenter.main;

import com.i3020.mvpdemo01.R;
import com.i3020.mvpdemo01.base.BaseActivity;

public class MainActivity extends BaseActivity<MainContract.IView, MainContract.AbstractPresenter> implements MainContract.IView {

	@Override
	protected int getLayoutResId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {

	}

	@Override
	public void initListener() {

	}

	@Override
	protected void initData() {

	}


	@Override
	protected MainPresenter createPresenter() {
		return new MainPresenter(getApplicationContext());
	}


}
