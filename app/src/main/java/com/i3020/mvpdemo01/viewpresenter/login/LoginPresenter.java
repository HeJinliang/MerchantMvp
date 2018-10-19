package com.i3020.mvpdemo01.viewpresenter.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;

import com.i3020.mvpdemo01.config.Constant;
import com.i3020.mvpdemo01.config.Variable;
import com.i3020.mvpdemo01.event.EventBusConfig;
import com.i3020.mvpdemo01.event.MainEvent;
import com.i3020.mvpdemo01.model.bean.LoginInfoBean;
import com.i3020.mvpdemo01.model.bean.PublicKeyBean;
import com.i3020.mvpdemo01.model.impl.OkHttpModelImpl;
import com.i3020.mvpdemo01.model.impl.SPUtils;
import com.i3020.mvpdemo01.utils.FastJsonUtils;
import com.i3020.mvpdemo01.utils.HPUtils;
import com.i3020.mvpdemo01.utils.LogUtils;
import com.i3020.mvpdemo01.utils.RSAHelper;
import com.i3020.mvpdemo01.utils.RSAUtils;
import com.i3020.mvpdemo01.viewpresenter.main.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import okhttp3.Response;

import static android.util.Base64.NO_WRAP;

/**
 * describe:
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinliang on 2018/8/25.
 */
public class LoginPresenter extends LoginContract.AbstractPresenter {


	private String publicKeyString;//APP端公钥
	private String privateKeyString;//APP端私钥

	private String phone;
	private String psw;

	LoginPresenter(Context mContext) {
		super(mContext);
	}


	@Override
	protected void init() {

	}

	@Override
	void toLogin(String account, String password) {

		if (TextUtils.isEmpty(account)){
			HPUtils.showToast(mContext, "请输入手机号");
			return;
		}
		if (TextUtils.isEmpty(password)){
			HPUtils.showToast(mContext, "请输入密码");
			return;
		}

		phone = account;
		psw = password;

		generateKey();

		//请求服务器端公钥

		Map map = new HashMap();
		map.put("uid", account);
		OkHttpModelImpl.getInstance().loadLoginDataByPost(Constant.URL_GET_PUBLICKEY, map, this);
	}

	@Override
	void toRegister() {

	}

	@Override
	void toForgetPassword() {

	}

//--------------------------------------------------------------------------------------------------
	//联网请求数据 start
//--------------------------------------------------------------------------------------------------

	@Override
	public void onRequestSuccess(String urlCut, Response response, String content) {
		super.onRequestSuccess(urlCut, response, content);
		switch (urlCut){
			case Constant.URL_GET_PUBLICKEY:
				//获取公钥
				PublicKeyBean publicKeyBean = FastJsonUtils.getInstance().JsonToBean(content, PublicKeyBean.class);
				if (publicKeyBean != null){
					String return_code = publicKeyBean.getReturn_code();
					LogUtils.i("return_code:"+return_code);
					if ("0".equals(return_code)){
						PublicKeyBean.DataBean data = publicKeyBean.getData();
						if (data != null){
							String public_key = data.getPublic_key();
							if (!TextUtils.isEmpty(public_key)){
								postLogin(public_key);
							}
						}
					} else {
						String msg = publicKeyBean.getMsg();
						if (!TextUtils.isEmpty(msg)){
							HPUtils.showToast(mContext, msg);
						}
					}
				}

				break;
			case Constant.URL_LOGIN_LOGIN:
				//请求登录接口
				LoginInfoBean loginInfoBean = FastJsonUtils.getInstance().JsonToBean(content, LoginInfoBean.class);
				if (loginInfoBean != null){
					String return_code = loginInfoBean.getReturn_code();
					if ("0".equals(return_code)){

						LoginInfoBean.DataBean dataBean = loginInfoBean.getData();
						if (dataBean != null){
							String tokenEncrypt  = dataBean.getToken();
							String uid = dataBean.getUid();
							String store_id = dataBean.getStore_id();

							SPUtils.setLoginUser(mContext, phone);//将登录账号保存在本地


							if (!TextUtils.isEmpty(uid)){
								SPUtils.setUid(mContext, uid);
								Variable.uid = uid;
								LogUtils.i("注册推送");
								//登录成功之后，注册推送
//								SocketThreadManager.sharedInstance().startThreads();
								EventBus.getDefault().post(new MainEvent(EventBusConfig.EVENT_REGISTER_PUSH));

							}

							if (!TextUtils.isEmpty(store_id)){
								SPUtils.setStoreId(mContext, store_id);
								Variable.storeId = store_id;
							}

							try {
								byte[] decode = Base64.decode(tokenEncrypt, NO_WRAP);
								PrivateKey privateKey = RSAHelper.getPrivateKey(privateKeyString);
								Cipher cipher = Cipher.getInstance(Constant.Cipher_Content);
								cipher.init(Cipher.DECRYPT_MODE, privateKey);
								byte[] bytes1 = cipher.doFinal(decode);
								String tokenStr = new String(bytes1);

								Variable.LOGIN_BOOLEAN = true;
								Variable.Token = tokenStr;//将token写入内存
								SPUtils.setToken(mContext, tokenStr);//将token保存在本地
								Intent intent = new Intent(mContext, MainActivity.class);
								intent.putExtra("tag", 1);
								if (getView() != null){
									getView().startActivity(intent, -1);
									getView().finishActivity();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} else if ("-402".equals(return_code)){
						HPUtils.showToast(mContext, "账号不存在！");
					} else {
						String msg = loginInfoBean.getMsg();
						if (!TextUtils.isEmpty(msg)) {
							HPUtils.showToast(mContext, msg);
						}
					}
				}

				break;
		}
	}

//--------------------------------------------------------------------------------------------------
	//联网请求数据 end
//--------------------------------------------------------------------------------------------------




//--------------------------------------------------------------------------------------------------
	//内部方法 start
//--------------------------------------------------------------------------------------------------

	/**
	 * 生成公钥私钥
	 */
	private void generateKey(){
		KeyPair keyPair = RSAUtils.generateRSAKeyPair();
		PrivateKey aPrivate = keyPair.getPrivate();
		PublicKey aPublic = keyPair.getPublic();

		byte[] publicKeyData = aPublic.getEncoded();
		byte[] privateKeyData = aPrivate.getEncoded();

		publicKeyString = Base64.encodeToString(publicKeyData, NO_WRAP);
		privateKeyString = Base64.encodeToString(privateKeyData, NO_WRAP);

//        LogUtils.e("公钥："+publicKeyString);
//        LogUtils.e("私钥："+privateKeyString);

	}


	/**
	 * 请求后台进行登陆操作
	 * @param Key 后台传递过来的公钥
	 */
	private void postLogin(String Key) {

		String pwdL = RSAUtils.getHiEncrypt(psw, Key);
		String loginLength = String.valueOf(psw.length());

		Map map = new HashMap();
		map.put("uid", phone);
		map.put("password", pwdL);
		map.put("pwd_len", loginLength);
		map.put("public_key", publicKeyString);

		String deviceToken;//将友盟推送的deviceToken传递给后台
		if (TextUtils.isEmpty(Variable.uMengDeviceToken)){
			deviceToken = SPUtils.getDeviceToken(mContext);
		} else {
			deviceToken = Variable.uMengDeviceToken;
		}
		if (!TextUtils.isEmpty(deviceToken)){
			map.put("device_token", deviceToken);
		}

		OkHttpModelImpl.getInstance().loadLoginDataByPost(Constant.URL_LOGIN_LOGIN, map, this);
	}


//--------------------------------------------------------------------------------------------------
	//内部方法  end
//--------------------------------------------------------------------------------------------------


}
