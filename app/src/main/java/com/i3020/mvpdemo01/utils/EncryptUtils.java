package com.i3020.mvpdemo01.utils;

import com.i3020.mvpdemo01.config.Variable;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * decribe:
 * create by HeJinliang on 2018/8/22
 */
public class EncryptUtils {

//	private static EncryptUtils encryptUtils = null;
//
//	private EncryptUtils(){
//	}
//
//	public static EncryptUtils getInstance() {
//		if (encryptUtils == null) {
//			synchronized (EncryptUtils.class) {
//				if (encryptUtils == null) {
//					encryptUtils = new EncryptUtils();
//				}
//			}
//		}
//		return encryptUtils;
//	}




	/**
	 * 联网签名（勿外泄）
	 * @param time  当前时间 秒
	 * @param token  token 值，不存在则取 time前四位
	 * @param uTag  用户标识（登录状态下 uid, 未登录 cpn 前端写死）
	 * @return
	 */
	public static String getPostSign(String time, String token, String uTag){

		String timeline_6 = time.substring(time.length() - 6, time.length());

		String string = Variable.ai + Variable.vc
				+ Variable.dt + Variable.os
				+ token + "hzdjsms17" + timeline_6 + uTag;

		int length = string.length();

		String md5 = md5(string + "" + length);
		return md5;
	}


	/**
	 * md5加密
	 * @param string
	 * @return
	 */
	public static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10) hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}



}
