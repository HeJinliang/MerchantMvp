package com.i3020.mvpdemo01.ui.widget;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.i3020.mvpdemo01.R;

/**
 * describe: 等待提示
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/10/11
 */
public class LoadingView {
    private Activity activity;
    private LayoutInflater mInfalter;
    private AlertDialog mDialog;

    public LoadingView(Activity activity) {
        this.activity = activity;
        this.mInfalter = LayoutInflater.from(activity);

        View view = View.inflate(activity, R.layout.view_loading, null);

        mDialog = new AlertDialog.Builder(activity, R.style.defaultDialog)
                .setView(view)
                .create();

        ImageView iv_content = (ImageView) view.findViewById(R.id.iv_content);
        Glide.with(activity.getApplicationContext()).load(R.drawable.loading).into(iv_content);
//        ((AnimationDrawable)iv_content.getDrawable()).start();

        mDialog.setCanceledOnTouchOutside(false);
    }

    public void show() {
        if (mDialog != null && !activity.isFinishing()){
            mDialog.show();
        }
    }

    public void dismiss() {
        if(mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
}
