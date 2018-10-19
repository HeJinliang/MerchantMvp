package com.i3020.mvpdemo01.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.i3020.mvpdemo01.R;
import com.i3020.mvpdemo01.inter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * describe: 选择框 适配器
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/10/17.
 */

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> list;
    private int tag; //标记  3 单选  4  多选
    private List<Integer> selectList;

    /**
     *
     * @param mContext
     * @param list      数据列表
     * @param tag       标记  3 单选  4  多选
     */
    public CheckAdapter(Context mContext, List<String> list, int tag){
        this.mContext = mContext;
        this.list = list;
        this.tag = tag;
        selectList = new ArrayList<>();
        if (tag == 3){
            selectList.add(0);
        }
    }

    /**
     * @param mContext
     * @param list      数据列表
     * @param integerList    多选框选中的数据
     * @param tag    4 多选
     */
    public CheckAdapter(Context mContext, List<String> list, List<Integer> integerList, int tag){
        this.mContext = mContext;
        this.list = list;
        selectList = new ArrayList<>();
        selectList.clear();
        selectList.addAll(integerList);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_check_dialog, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if (tag == 3){
            holder.cb_bg.setBackgroundResource(R.drawable.selector_single_check);
        } else {
            holder.cb_bg.setBackgroundResource(R.drawable.selector_double_check);
        }

        if (position < list.size()){
            String name = list.get(position);
            holder.tv_content.setText(name);


            if (selectList.contains(position)){
                holder.cb_bg.setChecked(true);
            } else {
                holder.cb_bg.setChecked(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    /**
     * 将制定item设置为选中 或 未选中
     * @param index
     * @return
     */
    public List<Integer> setSelect(int index){

        if (tag == 3){
            if (!selectList.contains(index)){
                selectList.clear();
                selectList.add(index);
                notifyDataSetChanged();
            }
        } else {
            if (selectList.contains(index)){
                selectList.remove(Integer.valueOf(index));
            } else {
                selectList.add(Integer.valueOf(index));
            }
            notifyDataSetChanged();
        }

        return selectList;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.cb_bg)
        CheckBox cb_bg;
        @Bind(R.id.tv_content)
        TextView tv_content;
        @Bind(R.id.iRoom)
        View iRoom;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            cb_bg.setEnabled(false);

            iRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
