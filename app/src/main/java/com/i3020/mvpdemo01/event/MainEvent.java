package com.i3020.mvpdemo01.event;

/**
 * describe: EventBus传递的数据
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/9/23.
 */
public class MainEvent {
    private String tag;
    public Object obj;
    public int what;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public MainEvent(int what) {
        this.what = what;
    }

    public MainEvent(int what, String tag) {
        this.tag = tag;
        this.what = what;
    }

    public MainEvent(int what, Object obj){
        this.what = what;
        this.obj = obj;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
