package com.i3020.mvpdemo01.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.i3020.mvpdemo01.R;
import com.i3020.mvpdemo01.inter.OnItemClickListener;
import com.i3020.mvpdemo01.ui.adapter.CheckAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * describe: 标准 AlertDialog弹窗 （UI 设计）
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/10/16.
 */

public class StandardDialog implements OnItemClickListener {

    private static StandardDialog standardDialog;
    private Dialog mDialog;
    private Activity mActivity;
    private Context mContext;
    private int type;//弹窗类型  1 通知弹窗  2  输入弹窗  3 单选  4  多选  5 自定义view

    private final TextView tv_title;
    private final TextView tv_message;
    private final TextView tv_neutral;
    private final TextView tv_negative;
    private final TextView tv_positive;
    private final EditText et_input;
    private final View view_line;
    private final RelativeLayout rl_content;
    private final RelativeLayout rl_function;
    private final RecyclerView rcv_content;
    private CheckAdapter checkAdapter;

    private List<Integer> selectList;

    /**
     *
     * @param mActivity
     * @param type
     */
    private StandardDialog(Activity mActivity, int type){

        this.mActivity = mActivity;
        this.type = type;

        mDialog = new Dialog(mActivity, R.style.CustomDialogStyle);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_custom, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        view_line = view.findViewById(R.id.view_line);
        rl_content = (RelativeLayout) view.findViewById(R.id.rl_content);
        rl_function = (RelativeLayout) view.findViewById(R.id.rl_function);
        tv_message = (TextView) view.findViewById(R.id.tv_message);
        et_input = (EditText) view.findViewById(R.id.et_input);
        rcv_content = (RecyclerView) view.findViewById(R.id.rcv_content);
        tv_neutral = (TextView) view.findViewById(R.id.tv_neutral);
        tv_negative = (TextView) view.findViewById(R.id.tv_negative);
        tv_positive = (TextView) view.findViewById(R.id.tv_positive);


        tv_title.setVisibility(View.GONE);
        view_line.setVisibility(View.GONE);
        rl_function.setVisibility(View.GONE);
        tv_message.setVisibility(View.GONE);
        et_input.setVisibility(View.GONE);
        rcv_content.setVisibility(View.GONE);

        mDialog.setContentView(view);

        switch (type){
            case 1:
                tv_message.setVisibility(View.VISIBLE);
                rl_content.setVisibility(View.GONE);

                break;
            case 2:
                et_input.setVisibility(View.VISIBLE);
                break;

            case 3:
            case 4:
                rcv_content.setVisibility(View.VISIBLE);
                break;
            case 5:

                break;
        }
    }

    /**
     *
     * @param mContext
     * @param type
     */
    private StandardDialog(Context mContext, int type){

        this.mContext = mContext;
        this.type = type;

        mDialog = new Dialog(mContext, R.style.CustomDialogStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_custom, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        view_line = view.findViewById(R.id.view_line);
        rl_content = (RelativeLayout) view.findViewById(R.id.rl_content);
        rl_function = (RelativeLayout) view.findViewById(R.id.rl_function);
        tv_message = (TextView) view.findViewById(R.id.tv_message);
        et_input = (EditText) view.findViewById(R.id.et_input);
        rcv_content = (RecyclerView) view.findViewById(R.id.rcv_content);
        tv_neutral = (TextView) view.findViewById(R.id.tv_neutral);
        tv_negative = (TextView) view.findViewById(R.id.tv_negative);
        tv_positive = (TextView) view.findViewById(R.id.tv_positive);


        tv_title.setVisibility(View.GONE);
        view_line.setVisibility(View.GONE);
        rl_function.setVisibility(View.GONE);
        tv_message.setVisibility(View.GONE);
        et_input.setVisibility(View.GONE);
        rcv_content.setVisibility(View.GONE);

        mDialog.setContentView(view);

        switch (type){
            case 1:
                tv_message.setVisibility(View.VISIBLE);
                rl_content.setVisibility(View.GONE);

                break;
            case 2:
                et_input.setVisibility(View.VISIBLE);
                break;

            case 3:
            case 4:
                rcv_content.setVisibility(View.VISIBLE);
                break;
            case 5:

                break;
        }
    }


    public static StandardDialog create(Activity mActivity, int type){
        standardDialog = new StandardDialog(mActivity, type);

        return standardDialog;
    }

    public static StandardDialog create(Context mcontext, int type){
        standardDialog = new StandardDialog(mcontext, type);

        return standardDialog;
    }

    /**
     * 设置dialog的标题
     * @param title
     * @return
     */
    public StandardDialog setTitle(String title){
        if (tv_title != null){
            tv_title.setText(title);
            tv_title.setVisibility(View.VISIBLE);
            view_line.setVisibility(View.VISIBLE);
        }
        return standardDialog;
    }


    public StandardDialog setMessage(String content){
        if (tv_message != null){
            tv_message.setText(content);
            tv_message.setVisibility(View.VISIBLE);
        }
        rl_content.setVisibility(View.VISIBLE);

        return standardDialog;
    }

    public StandardDialog setNeutralButton(String content, final View.OnClickListener listener){

        if (rl_function != null){
            rl_function.setVisibility(View.VISIBLE);
        }

        if (tv_neutral != null){
            tv_neutral.setText(content);

            tv_neutral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();

                    if (listener != null){
                        listener.onClick(v);
                    }
                }
            });
        }

        return standardDialog;
    }

    public StandardDialog setNegativeButton(String content, final View.OnClickListener listener){

        if (rl_function != null){
            rl_function.setVisibility(View.VISIBLE);
        }

        if (tv_negative != null){
            tv_negative.setText(content);

            tv_negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();

                    if (listener != null){
                        listener.onClick(v);
                    }
                }
            });
        }

        return standardDialog;
    }

    public StandardDialog setPositiveButton(String content, final View.OnClickListener listener){

        if (rl_function != null){
            rl_function.setVisibility(View.VISIBLE);
        }

        if (tv_positive != null){
            tv_positive.setText(content);

            tv_positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (type){
                        case 1:
                            dismiss();
                            break;
                        case 2:
                            break;
                        case 3:
                            dismiss();
                            break;
                        case 4:
                            dismiss();
                            break;
                    }
                    if (listener != null){
                        listener.onClick(v);
                    }
                }
            });
        }

        return standardDialog;
    }


    public StandardDialog setPositiveMoreCheckButtom(String content, final OnSelectMoreCheckSureListener moreCheckSureListener){
        if (rl_function != null){
            rl_function.setVisibility(View.VISIBLE);
        }

        if (tv_positive != null){
            tv_positive.setText(content);

            tv_positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dismiss();

                    if (moreCheckSureListener != null){
                        moreCheckSureListener.onSureListener(v, selectList);
                    }
                }
            });
        }

        return standardDialog;
    }

    public StandardDialog setCancelable(boolean flag){
        mDialog.setCancelable(flag);
        return standardDialog;
    }

    public StandardDialog setCanceledOnTouchOutside(boolean flag){
        mDialog.setCanceledOnTouchOutside(flag);
        return standardDialog;
    }

    public StandardDialog setOnDismissListener(DialogInterface.OnDismissListener listener){
        mDialog.setOnDismissListener(listener);
        return standardDialog;
    }

//--------------------------------------------------------------------------------------------------
//    自定义dialog相关设置
//--------------------------------------------------------------------------------------------------

    /**
     * 设置自定义view布局
     * @param view
     * @return
     */
    public StandardDialog setContentView(View view){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);


		tv_message.setVisibility(View.GONE);
		et_input.setVisibility(View.GONE);
		rcv_content.setVisibility(View.GONE);

        rl_content.addView(view);
        return standardDialog;
    }



//--------------------------------------------------------------------------------------------------
//    输入框dialog相关设置
//--------------------------------------------------------------------------------------------------
    /**
     * 设置默认显示内容
     * @param hint
     * @return
     */
    public StandardDialog setHint(String hint){
        if (et_input != null){
            et_input.setHint(hint);
        }
        return standardDialog;
    }

    /**
     * 设置输入框输入类型
     * @param type  eg: InputType.TYPE_CLASS_NUMBER
     * @return
     */
    public StandardDialog setInputType(int type){
        if (et_input != null){
            et_input.setInputType(type);
        }
        return standardDialog;
    }

    /**
     * 设置输入框最大输入行数
     * @param maxlines
     * @return
     */
    public StandardDialog setInputMaxLines(int maxlines){
        if (et_input != null){
            et_input.setMaxLines(maxlines);
        }

        return standardDialog;
    }

	/**
	 * 设置输入框最大输入字数
	 * @param maxLength
	 * @return
	 */
	public StandardDialog setInputMaxLength(int maxLength){
		if (et_input != null){
			et_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
		}
		return standardDialog;
	}

    /**
     * 获取输入框的内容
     * @return
     */
    public String getEditContent(){
        String inputContent;
        if (et_input != null){
            inputContent = et_input.getText().toString().trim();
        } else {
            inputContent = "";
        }
        return inputContent;
    }



//--------------------------------------------------------------------------------------------------
//    单选框dialog相关设置
//--------------------------------------------------------------------------------------------------

    /**
     * 设置单选框
     * @param list
     * @param selectIndex 选中的下标
     * @param listener  点击监听
     * @return
     */
    public StandardDialog setSingleCheckData(List<String> list, int selectIndex, OnSingleSelctListener listener){

        this.singleListener = listener;
        selectList = new ArrayList<>();
        selectList.add(Integer.valueOf(selectIndex));

        LinearLayoutManager layoutManager;

        if (mActivity != null){
            checkAdapter = new CheckAdapter(mActivity, list, 3);
            layoutManager = new LinearLayoutManager(mActivity);
        } else {
            checkAdapter = new CheckAdapter(mContext, list, 3);
            layoutManager = new LinearLayoutManager(mContext);
        }
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_content.setLayoutManager(layoutManager);
        rcv_content.setAdapter(checkAdapter);
        checkAdapter.setSelect(selectIndex);
        checkAdapter.setOnItemClickListener(this);

        return standardDialog;
    }



//--------------------------------------------------------------------------------------------------
//    复选框dialog相关设置
//--------------------------------------------------------------------------------------------------

    /**
     * 复选框
     * @param list         内容条目
     * @param integerList  选中的下标集合
     * @param listener  点击监听
     * @return
     */
    public StandardDialog setMoreCheckData(List<String> list, List<Integer> integerList, OnSelectListener listener){

        this.listener = listener;
        selectList = integerList;

        LinearLayoutManager layoutManager;
        if (mActivity != null){
            checkAdapter = new CheckAdapter(mActivity, list, selectList, 4);
            layoutManager = new LinearLayoutManager(mActivity);
        } else {
            checkAdapter = new CheckAdapter(mContext, list, selectList, 4);
            layoutManager = new LinearLayoutManager(mContext);
        }

        rcv_content.setLayoutManager(layoutManager);
        rcv_content.setAdapter(checkAdapter);
        checkAdapter.setOnItemClickListener(this);


        return standardDialog;
    }

    /**
     * 显示弹窗
     */
    public StandardDialog show() {
        if (mActivity != null){
            if (!mActivity.isFinishing() && mDialog != null){
                mDialog.show();
            }
        } else {
            if (mDialog != null){
                mDialog.show();
            }
        }

        return standardDialog;
    }

    /**
     * 隐藏弹窗
     */
    public StandardDialog dismiss() {
        if(mDialog!=null && mDialog.isShowing()){
            mDialog.dismiss();
        }

        return standardDialog;
    }

    public Dialog getmDialog(){
        return mDialog;
    }

    @Override
    public void onItemClick(int position) {
        if (checkAdapter != null){
            selectList = checkAdapter.setSelect(position);

            if (listener != null){
                listener.onSelect(selectList);
            }

            if (singleListener != null){
                singleListener.onSelect(selectList.get(0));
            }
        }
    }

    /**
     * 单选/多选 条目选中的监听 返回所有选中的集合
     */
    private OnSelectListener listener;

    public interface OnSelectListener {
        void onSelect(List<Integer> list);
    }

    /**
     * 单选 条目选中的监听  返回选中的集合中的第一个
     */
    private OnSingleSelctListener singleListener;

    public interface OnSingleSelctListener{
        void onSelect(int index);
    }

    /**
     * 多选 确认按键的监听  返回view和选中的集合
     */
    private OnSelectMoreCheckSureListener moreCheckSureListener;

    public interface OnSelectMoreCheckSureListener{
        void onSureListener(View view, List<Integer> list);
    }
}
