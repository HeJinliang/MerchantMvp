package com.i3020.mvpdemo01.viewpresenter.guide;

import com.i3020.mvpdemo01.R;
import com.i3020.mvpdemo01.base.BaseActivity;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/23.
 */
public class GuideActivity extends BaseActivity<GuideContract.IView, GuideContract.AbstractPresenter> implements GuideContract.IView {

	@Override
	protected int getLayoutResId() {

		return R.layout.activity_guide;
	}

	@Override
	protected void initView() {

	}

	@Override
	public void initListener() {

	}

	@Override
	protected void initData() {

		forbidSwipeBack();//屏蔽左右滑动


		mPresenter.loadData();

	}

	@Override
	protected GuideContract.AbstractPresenter createPresenter() {
		return new GuidePresenter(getApplicationContext());
	}


}
