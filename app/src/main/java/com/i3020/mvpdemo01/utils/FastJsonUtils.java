package com.i3020.mvpdemo01.utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/24.
 */
public class FastJsonUtils {

	private static FastJsonUtils fastJsonUtils = null;

	private FastJsonUtils() {
	}

	public static FastJsonUtils getInstance() {
		if (fastJsonUtils == null) {
			synchronized (FastJsonUtils.class) {
				if (fastJsonUtils == null) {
					fastJsonUtils = new FastJsonUtils();
				}
			}
		}
		return fastJsonUtils;
	}


	/**
	 * 转成json
	 *
	 * @param object
	 * @return
	 */
	public String BeanToJson(Object object) {
		String gsonString = null;
		try{
			gsonString = JSON.toJSONString(object);
		} catch (Exception e){
			e.printStackTrace();
			LogUtils.e(e.toString());
		}
		return gsonString;
	}



	/**
	 * 转成bean
	 *
	 * @param jsonString
	 * @param cls
	 * @return
	 */
	public <T> T JsonToBean(String jsonString, Class<T> cls) {
		T t = null;
		try{
			t = JSON.parseObject(jsonString, cls);
		} catch (Exception e){
			e.printStackTrace();
			LogUtils.e(e.toString());
		}

		return t;
	}



	/**
	 * 转成list
	 * 解决泛型问题
	 * @param json
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public <T> ArrayList<T> JsonToList(String json, Class<T> cls) {
		ArrayList<T> list = new ArrayList<T>();
		try{
			List<T> ts = JSON.parseArray(json, cls);
			for(T obj : ts){
				list.add(obj);
			}
		} catch (Exception e){
			e.printStackTrace();
			LogUtils.e(e.toString());
		}
		return list;
	}


	/**
	 * List转JsonString
	 * @author MirsFang
	 * @time 2017/6/26 上午9:06
	 **/
	public String ListToJson(List list){
		String jsonList="";
		try {
			jsonList = JSON.toJSONString(list);
		} catch (Exception e){
			e.printStackTrace();
			LogUtils.e(e.toString());
		}
		return jsonList;
	}




}
