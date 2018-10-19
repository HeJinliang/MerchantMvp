package com.i3020.mvpdemo01.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * describe: json转换类
 * Company:  杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2017/3/28.
 */

public class GsonUtils {
    private static GsonUtils gsonUtils = null;
    private static Gson gson = null;
    static {
		gson = new Gson();
	}

    private GsonUtils() {
    }

    public static GsonUtils getInstance() {
        if (gsonUtils == null) {
            synchronized (GsonUtils.class) {
                if (gsonUtils == null) {
                    gsonUtils = new GsonUtils();
                }
            }
        }
        return gsonUtils;
    }

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    public String GsonString(Object object) {
        String gsonString = null;
        try{
            if (gson != null) {
                gsonString = gson.toJson(object);
            } else {
                gson = new Gson();
                gsonString = gson.toJson(object);
            }
        } catch (Exception e){
            e.printStackTrace();
            LogUtils.e(e.toString());
        }
        return gsonString;
    }

    /**
     * 转成bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public <T> T GsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        try{
            if (gson != null) {
                t = gson.fromJson(gsonString, cls);
            } else {
                gson = new Gson();
                t = gson.fromJson(gsonString, cls);
            }
        } catch (Exception e){
            e.printStackTrace();
            LogUtils.e(e.toString());
        }

        return t;
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错  (弃用)
     * @param gsonString
     * @param cls
     * @return
     */
    @Deprecated
    public <T> List<T> GsonToList(String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list
     * 解决泛型问题
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public <T> ArrayList<T> jsonToList(String json, Class<T> cls) {
        ArrayList<T> list = new ArrayList<T>();
        try{
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            if (gson != null){
                for(final JsonElement elem : array){
                    list.add(gson.fromJson(elem, cls));
                }
            } else {
                gson = new Gson();
                for(final JsonElement elem : array){
                    list.add(gson.fromJson(elem, cls));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            LogUtils.e(e.toString());
        }
        return list;
    }



    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        } else {
            gson = new Gson();
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * List转JsonString
     * @author MirsFang
     * @time 2017/6/26 上午9:06
     **/
    public String ListToJson(List list){
        String jsonList="";
        try {
            if (gson != null){
                jsonList = gson.toJson(list);
            } else {
                gson = new Gson();
                jsonList = gson.toJson(list);
            }
        } catch (Exception e){
            e.printStackTrace();
            LogUtils.e(e.toString());
        }
        return jsonList;
    }

}
