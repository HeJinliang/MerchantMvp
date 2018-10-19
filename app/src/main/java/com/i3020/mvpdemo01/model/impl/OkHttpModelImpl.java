package com.i3020.mvpdemo01.model.impl;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.i3020.mvpdemo01.config.AppConfig;
import com.i3020.mvpdemo01.config.Variable;
import com.i3020.mvpdemo01.listener.DataRequestListener;
import com.i3020.mvpdemo01.model.bean.PostCommonBean;
import com.i3020.mvpdemo01.model.bean.PostFailureBean;
import com.i3020.mvpdemo01.model.bean.PostSuccessBean;
import com.i3020.mvpdemo01.utils.EncryptUtils;
import com.i3020.mvpdemo01.utils.LogUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * decribe:
 * create by HeJinliang on 2018/8/16
 */
public class OkHttpModelImpl implements IMainModel {

	private static OkHttpModelImpl mainModel = null;

	private static OkHttpClient okHttpClient = null;

	private OkHttpModelImpl(){
		OkHttpClient.Builder builder = new OkHttpClient.Builder();

		//cookie策略
		builder.cookieJar(new CookieJar() {
			private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

			@Override
			public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
				cookieStore.put(url, cookies);
			}

			@Override
			public List<Cookie> loadForRequest(HttpUrl url) {
				List<Cookie> cookies = cookieStore.get(url);
				return cookies != null ? cookies : new ArrayList<Cookie>();
			}
		});

		//连接超时时间
		builder.connectTimeout(15000, TimeUnit.MILLISECONDS);

		okHttpClient = builder.build();
	}

	public static OkHttpModelImpl getInstance() {
		if (mainModel == null) {
			synchronized (OkHttpModelImpl.class) {
				if (mainModel == null) {
					mainModel = new OkHttpModelImpl();
				}
			}
		}
		return mainModel;
	}


	@Override
	public void loadLoginDataByGet(String url, DataRequestListener<String> listener) {
		loadDataByGet(AppConfig.getBaseLoginUrl()+url, listener);

	}

	@Override
	public void loadLoginDataByGet(String url, Map<String, String> map, DataRequestListener<String> listener) {
		loadDataByGet(AppConfig.getBaseLoginUrl()+url, map, listener);
	}

	@Override
	public void loadHaipiDataByGet(String url, DataRequestListener<String> listener) {
		loadDataByGet(AppConfig.getBaseUrl()+url, listener);
	}

	@Override
	public void loadHaipiDataByGet(String url, Map<String, String> map, DataRequestListener<String> listener) {
		loadDataByGet(AppConfig.getBaseUrl()+url, map, listener);
	}

	@Override
	public void loadDataByGet(String url, DataRequestListener<String> listener) {
		loadDataByGet(url, new HashMap<String, String>(), listener);
	}

	@Override
	public void loadDataByGet(String url, Map<String, String> map, DataRequestListener<String> listener) {

		if (url.contains(AppConfig.getBaseLoginUrl()) || url.contains(AppConfig.getBaseUrl())){

			String time = String.valueOf(System.currentTimeMillis()/1000);
			String token;
			String uTag;
			if (Variable.LOGIN_BOOLEAN){
				token = Variable.Token;

				String uid = map.get("uid");
				if (TextUtils.isEmpty(uid)){
					uid = Variable.uid;
					map.put("uid", uid);
				}

				uTag = uid;

			} else {
				token = time.substring(0, 4);

				String uid = map.get("uid");
				if (TextUtils.isEmpty(uid)){
					uid = Variable.uid;
				} else {
					map.remove("uid");
				}
				uTag = uid;
				map.put("cpn", uid);
			}
			String md5 = EncryptUtils.getPostSign(time, token, uTag);

			map.put("sign", md5);
			map.put("timeline", time);
			map.put("ai", Variable.ai!=null?Variable.ai:"");//应用ID
			map.put("vc", Variable.vc!=null?Variable.vc:"");//APP版本号
			map.put("dt", Variable.dt!=null?Variable.dt:"");//设备类型
			map.put("os", Variable.os!=null?Variable.os:"");//操作系统
			map.put("vn", Variable.vn!=null?Variable.vn:"");//APP版本名称
			map.put("osv", Variable.osv!=null?Variable.osv:"");//操作系统版本
			map.put("imei", Variable.imei!=null?Variable.imei:"");//手机的唯一标识
			map.put("imsi", Variable.imsi!=null?Variable.imsi:"");//SIM卡的唯一标识
			map.put("sv", Variable.sv!=null?Variable.sv:"");//渠道
			map.put("w", Variable.w!=null?Variable.w:"");//屏幕宽
			map.put("h", Variable.h!=null?Variable.h:"");//屏幕高
			map.put("nt", Variable.nt!=null?Variable.nt:"");//网络类型
			map.put("mm", Variable.mm!=null?Variable.mm:"");//手机型号
			map.put("ct", Variable.ct!=null?Variable.ct:"");//当前日期 yyyyMMdd
		}


		String parameterLogInfo = "";
		if (map!=null && map.size()>0){
			url = url + "?";

			for(Map.Entry<String, String> entry : map.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)){
					url = url + key + "=" + value + "&";

					parameterLogInfo += key+"："+value+"         ";

				}
			}
		}
		String finalUrl = url.substring(0, url.length()-1);//最终请求的url

		LogUtils.i("model", "------------------------------------------------------------");
		LogUtils.i("model", "url："+finalUrl);
		LogUtils.i("model", "参数："+parameterLogInfo);
		LogUtils.i("model", "------------------------------------------------------------");

		Request request = new Request.Builder()
				.url(finalUrl)
				.get()
				.build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

				String strUrlCut;//本项目里的为 URL接口字段， 非本项目的为请求的url本身

				if (finalUrl.contains(AppConfig.getBaseLoginUrl())){

					strUrlCut = finalUrl.replace(AppConfig.getBaseLoginUrl(), "").split("/?")[0];

				} else if(finalUrl.contains(AppConfig.getBaseUrl())){

					strUrlCut = finalUrl.replace(AppConfig.getBaseUrl(), "").split("/?")[0];

				} else {

					strUrlCut = finalUrl;

				}

				PostCommonBean postCommonBean = new PostCommonBean(strUrlCut, false, listener);
				Message msg1 = new Message();
				msg1.obj = postCommonBean;
				msg1.what = WHAT_POST_COMMON;
				postHandler.sendMessage(msg1);

				PostFailureBean postFailureBean = new PostFailureBean(strUrlCut, request, e, listener);
				Message msg2 = new Message();
				msg2.obj = postFailureBean;
				msg2.what = WHAT_POST_FAILURE;
				postHandler.sendMessage(msg2);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {

				String strUrlCut;

				if (finalUrl.contains(AppConfig.getBaseLoginUrl())){

					strUrlCut = finalUrl.replace(AppConfig.getBaseLoginUrl(), "").split("/?")[0];

				} else if(finalUrl.contains(AppConfig.getBaseUrl())){

					strUrlCut = finalUrl.replace(AppConfig.getBaseUrl(), "").split("/?")[0];

				} else {

					strUrlCut = finalUrl;

				}

				PostCommonBean postCommonBean = new PostCommonBean(strUrlCut, true, listener);
				Message msg1 = new Message();
				msg1.obj = postCommonBean;
				msg1.what = WHAT_POST_COMMON;
				postHandler.sendMessage(msg1);

				PostSuccessBean postSuccessBean = new PostSuccessBean<String>(strUrlCut, response, listener);
				Message msg2 = new Message();
				msg2.obj = postSuccessBean;
				msg2.what = WHAT_POST_SUCCESS;
				postHandler.sendMessage(msg2);
			}
		});

	}

	@Override
	public void loadLoginDataByPost(String url, DataRequestListener<String> listener) {
		loadDataByPost(AppConfig.getBaseLoginUrl()+url, listener);
	}

	@Override
	public void loadLoginDataByPost(String url, Map<String, String> map, DataRequestListener<String> listener) {
		loadDataByPost(AppConfig.getBaseLoginUrl()+url, map, listener);
	}

	@Override
	public void loadHaipiDataByPost(String url, DataRequestListener<String> listener) {
		loadDataByPost(AppConfig.getBaseUrl()+url, listener);
	}

	@Override
	public void loadHaipiDataByPost(String url, Map<String, String> map, DataRequestListener<String> listener) {
		loadDataByPost(AppConfig.getBaseUrl()+url, map, listener);
	}

	@Override
	public void loadDataByPost(String url, DataRequestListener<String> listener) {
		loadDataByPost(url, new HashMap<String, String>(), listener);
	}

	@Override
	public void loadDataByPost(String url, Map<String, String> map, DataRequestListener<String> listener) {
		if (url.contains(AppConfig.getBaseLoginUrl()) || url.contains(AppConfig.getBaseUrl())){

			String time = String.valueOf(System.currentTimeMillis()/1000);
			String token;
			String uTag;
			if (Variable.LOGIN_BOOLEAN){
				token = Variable.Token;

				String uid = map.get("uid");
				if (TextUtils.isEmpty(uid)){
					uid = Variable.uid;
					map.put("uid", uid);
				}

				uTag = uid;

			} else {
				token = time.substring(0, 4);

				String uid = map.get("uid");
				if (TextUtils.isEmpty(uid)){
					uid = Variable.cpn;
				} else {
					map.remove("uid");
				}
				uTag = uid;
				map.put("cpn", uid);
			}
			String md5 = EncryptUtils.getPostSign(time, token, uTag);

			map.put("sign", md5);
			map.put("timeline", time);
			map.put("ai", Variable.ai!=null?Variable.ai:"");//应用ID
			map.put("vc", Variable.vc!=null?Variable.vc:"");//APP版本号
			map.put("dt", Variable.dt!=null?Variable.dt:"");//设备类型
			map.put("os", Variable.os!=null?Variable.os:"");//操作系统
			map.put("vn", Variable.vn!=null?Variable.vn:"");//APP版本名称
			map.put("osv", Variable.osv!=null?Variable.osv:"");//操作系统版本
			map.put("imei", Variable.imei!=null?Variable.imei:"");//手机的唯一标识
			map.put("imsi", Variable.imsi!=null?Variable.imsi:"");//SIM卡的唯一标识
			map.put("sv", Variable.sv!=null?Variable.sv:"");//渠道
			map.put("w", Variable.w!=null?Variable.w:"");//屏幕宽
			map.put("h", Variable.h!=null?Variable.h:"");//屏幕高
			map.put("nt", Variable.nt!=null?Variable.nt:"");//网络类型
			map.put("mm", Variable.mm!=null?Variable.mm:"");//手机型号
			map.put("ct", Variable.ct!=null?Variable.ct:"");//当前日期 yyyyMMdd
		}


		MultipartBody.Builder builder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM);

		String parameterLogInfo = "";
		if (map!=null && map.size()>0){
			for(Map.Entry<String, String> entry : map.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)){
					builder.addFormDataPart(key, value);
					parameterLogInfo += key+"："+value+"         ";
				}
			}
		}

		LogUtils.i("model", "------------------------------------------------------------");
		LogUtils.i("model", "url："+url);
		LogUtils.i("model", "参数："+parameterLogInfo);
		LogUtils.i("model", "------------------------------------------------------------");



		Request request = new Request.Builder()
				.url(url)
				.post(builder.build())
				.build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

				String strUrlCut;//本项目里的为 URL接口字段， 非本项目的为请求的url本身

				if (url.contains(AppConfig.getBaseLoginUrl())){
					strUrlCut = url.replace(AppConfig.getBaseLoginUrl(), "").split("/?")[0];

				} else if(url.contains(AppConfig.getBaseUrl())){

					strUrlCut = url.replace(AppConfig.getBaseUrl(), "").split("/?")[0];

				} else {

					strUrlCut = url;

				}

				PostCommonBean postCommonBean = new PostCommonBean(strUrlCut, false, listener);
				Message msg1 = new Message();
				msg1.obj = postCommonBean;
				msg1.what = WHAT_POST_COMMON;
				postHandler.sendMessage(msg1);

				PostFailureBean postFailureBean = new PostFailureBean(strUrlCut, request, e, listener);
				Message msg2 = new Message();
				msg2.obj = postFailureBean;
				msg2.what = WHAT_POST_FAILURE;
				postHandler.sendMessage(msg2);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {

				String strUrlCut;

				if (url.contains(AppConfig.getBaseLoginUrl())){

					String[] split = url.replace(AppConfig.getBaseLoginUrl(), "").split("\\?");
					LogUtils.e("分割的数量:"+split.length);

					for (String string : split){
						LogUtils.e("内容："+string);
					}

					strUrlCut = url.replace(AppConfig.getBaseLoginUrl(), "").split("\\?")[0];

				} else if(url.contains(AppConfig.getBaseUrl())){

					strUrlCut = url.replace(AppConfig.getBaseUrl(), "").split("/?")[0];

				} else {

					strUrlCut = url;

				}

				PostCommonBean postCommonBean = new PostCommonBean(strUrlCut, true, listener);
				Message msg1 = new Message();
				msg1.obj = postCommonBean;
				msg1.what = WHAT_POST_COMMON;
				postHandler.sendMessage(msg1);

				PostSuccessBean postSuccessBean = new PostSuccessBean<String>(strUrlCut, response, listener);
				Message msg2 = new Message();
				msg2.obj = postSuccessBean;
				msg2.what = WHAT_POST_SUCCESS;
				postHandler.sendMessage(msg2);
			}
		});
	}


	/**
	 * 联网的结果
	 */
	private final int WHAT_POST_COMMON = 1;
	/**
	 * 联网成功
	 */
	private final int WHAT_POST_SUCCESS = 2;
	/**
	 * 联网失败
	 */
	private final int WHAT_POST_FAILURE = 3;


	private Handler postHandler = new Handler(msg -> {
		switch (msg.what){
			case WHAT_POST_COMMON:
				PostCommonBean postCommonBean = (PostCommonBean) msg.obj;

				DataRequestListener<String> listener1 = postCommonBean.getListener();
				boolean result = postCommonBean.getResult();
				String strUrlCut1 = postCommonBean.getStrUrlCut();

				listener1.onRequestCommon(strUrlCut1, result);


				break;
			case WHAT_POST_SUCCESS:
				PostSuccessBean<String> postSuccessBean = (PostSuccessBean) msg.obj;

				DataRequestListener<String> listener2 = postSuccessBean.getListener();
				Response response = postSuccessBean.getResponse();
				String strUrlCut2 = postSuccessBean.getStrUrlCut();

				String content = "";
				try {
					content = response.body().string();
				} catch (IOException e) {
					e.printStackTrace();
				}
				listener2.onRequestSuccess(strUrlCut2, response, content);

				break;
			case WHAT_POST_FAILURE:
				PostFailureBean postFailureBean = (PostFailureBean) msg.obj;

				DataRequestListener<String> listener3 = postFailureBean.getListener();
				Request request = postFailureBean.getRequest();
				String strUrlCut3 = postFailureBean.getStrUrlCut();
				IOException e = postFailureBean.getE();

				listener3.onRequestFaile(strUrlCut3, request, e);

				break;
		}

		return true;
	});
}
