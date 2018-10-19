package com.i3020.mvpdemo01.utils;

import android.util.Base64;

import com.i3020.mvpdemo01.config.Constant;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import Decoder.BASE64Decoder;

import static android.util.Base64.NO_WRAP;

/**
 * describe: RSA加密工具类
 * Company: 杭州洞见科技有限公司（www.i3020.com）
 * Created by HeJinLiang on 2017/7/4.
 */

public class RSAUtils {


    private static String RSA = "RSA";

    /**
     * 随机生成RSA密钥对(默认密钥长度为1024)
     *
     * @return
     */
    public static KeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(1024);
    }

    /**
     * 随机生成RSA密钥对
     *
     * @param keyLength 密钥长度，范围：512～2048<br>
     *                  一般1024
     * @return
     */
    public static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 公钥字段转为 publicKey
     * @param key   公钥字段
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    // 通过公钥byte[]将公钥还原，适用于RSA算法
    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }


    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    /**
     * 嗨皮数据加密规则    先MD5加密 再RSA加密 最后Base64编码
     * @param content   要加密的内容
     * @param pKey      公钥
     * @return
     */
    public static String getHiEncrypt(String content, String pKey){
        String encryptContent = "";
        String Md5Login = EncryptUtils.md5(content);
        try {
            PublicKey publicKey = RSAUtils.getPublicKey(pKey);
            Cipher cipher = Cipher.getInstance(Constant.Cipher_Content);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] rsaLogin = cipher.doFinal(Md5Login.getBytes());
            encryptContent = Base64.encodeToString(rsaLogin, NO_WRAP);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptContent;
    }

    /**
     * 嗨皮数据加密规则    先MD5加密 再RSA加密 最后Base64编码
     * @param content   要加密的内容
     * @param pKey      公钥
     * @return
     */
    public static byte[] getHiEncrypt2(String content, String pKey){
        byte[] bts;
        try {
            PublicKey publicKey = RSAUtils.getPublicKey(pKey);
            Cipher cipher = Cipher.getInstance(Constant.Cipher_Content);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            bts = cipher.doFinal(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return bts;
    }


    /**
     * 公钥解密
     * @param v   待解密的内容
     * @param publicKey  公钥
     * @return
     */
    public static String decryptByPuk(byte[] v, String publicKey) {
        try {
            byte[] myPublicKeyData = Base64.decode(publicKey, Base64.NO_PADDING);
            PublicKey myPublicKey = getPublicKey(myPublicKeyData);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            // A得到B发过来的信息后用自己的私钥进行解密
            cipher.init(Cipher.DECRYPT_MODE, myPublicKey);
            cipher.update(v);
            byte[] result2 = cipher.doFinal();
            return new String(result2, "utf-8");
        } catch (Exception e) {
        }
        return null;
    }


//    /**
//     * 用公钥加密 <br>
//     * 每次加密的字节数，不能超过密钥的长度值减去11
//     *
//     * @param data 需加密数据的byte数据
//     * @return 加密后的byte型数据
//     * @param公钥
//     */
//    public static byte[] encryptData(byte[] data, PublicKey publicKey) {
//        try {
//            Cipher cipher = Cipher.getInstance(RSA);
//            // 编码前设定编码方式及密钥
//            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//            // 传入编码数据并返回编码结果
//            return cipher.doFinal(data);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 用私钥解密
//     *
//     * @param encryptedData 经过encryptedData()加密返回的byte数据
//     * @param privateKey    私钥
//     * @return
//     */
//    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey) {
//        try {
//            Cipher cipher = Cipher.getInstance(RSA);
//            cipher.init(Cipher.DECRYPT_MODE, privateKey);
//            return cipher.doFinal(encryptedData);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * 通过公钥byte[](publicKey.getEncoded())将公钥还原，适用于RSA算法
//     *
//     * @param keyBytes
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws InvalidKeySpecException
//     */
//    public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException,
//            InvalidKeySpecException {
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
//        PublicKey publicKey = keyFactory.generatePublic(keySpec);
//        return publicKey;
//    }
//
//    /**
//     * 通过私钥byte[]将公钥还原，适用于RSA算法
//     *
//     * @param keyBytes
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws InvalidKeySpecException
//     */
//    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
//            InvalidKeySpecException {
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
//        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//        return privateKey;
//    }
//
//    /**
//     * 使用N、e值还原公钥
//     *
//     * @param modulus
//     * @param publicExponent
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws InvalidKeySpecException
//     */
//    public static PublicKey getPublicKey(String modulus, String publicExponent)
//            throws NoSuchAlgorithmException, InvalidKeySpecException {
//        BigInteger bigIntModulus = new BigInteger(modulus);
//        BigInteger bigIntPrivateExponent = new BigInteger(publicExponent);
//        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
//        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
//        PublicKey publicKey = keyFactory.generatePublic(keySpec);
//        return publicKey;
//    }
//
//    /**
//     * 使用N、d值还原私钥
//     *
//     * @param modulus
//     * @param privateExponent
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws InvalidKeySpecException
//     */
//    public static PrivateKey getPrivateKey(String modulus, String privateExponent)
//            throws NoSuchAlgorithmException, InvalidKeySpecException {
//        BigInteger bigIntModulus = new BigInteger(modulus);
//        BigInteger bigIntPrivateExponent = new BigInteger(privateExponent);
//        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
//        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
//        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//        return privateKey;
//    }
//
//    /**
//     * 从字符串中加载公钥
//     *
//     * @param publicKeyStr 公钥数据字符串
//     * @throws Exception 加载公钥时产生的异常
//     */
//    public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
//        try {
//            byte[] buffer = Base64Utils.decode(publicKeyStr);
//            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
//            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
//        } catch (NoSuchAlgorithmException e) {
//            throw new Exception("无此算法");
//        } catch (InvalidKeySpecException e) {
//            throw new Exception("公钥非法");
//        } catch (NullPointerException e) {
//            throw new Exception("公钥数据为空");
//        }
//    }
//
//    /**
//     * 从字符串中加载私钥<br>
//     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
//     *
//     * @param privateKeyStr
//     * @return
//     * @throws Exception
//     */
//    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
//        try {
//            byte[] buffer = Base64Utils.decode(privateKeyStr);
//            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
//            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
//            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
//        } catch (NoSuchAlgorithmException e) {
//            throw new Exception("无此算法");
//        } catch (InvalidKeySpecException e) {
//            throw new Exception("私钥非法");
//        } catch (NullPointerException e) {
//            throw new Exception("私钥数据为空");
//        }
//    }
//
//    /**
//     * 从文件中输入流中加载公钥
//     *
//     * @param in 公钥输入流
//     * @throws Exception 加载公钥时产生的异常
//     */
//    public static PublicKey loadPublicKey(InputStream in) throws Exception {
//        try {
//            return loadPublicKey(readKey(in));
//        } catch (IOException e) {
//            throw new Exception("公钥数据流读取错误");
//        } catch (NullPointerException e) {
//            throw new Exception("公钥输入流为空");
//        }
//    }
//
//    /**
//     * 从文件中加载私钥
//     *
//     * @return 是否成功
//     * @throws Exception
//     * @param私钥文件名
//     */
//    public static PrivateKey loadPrivateKey(InputStream in) throws Exception {
//        try {
//            return loadPrivateKey(readKey(in));
//        } catch (IOException e) {
//            throw new Exception("私钥数据读取错误");
//        } catch (NullPointerException e) {
//            throw new Exception("私钥输入流为空");
//        }
//    }
//
//    /**
//     * 读取密钥信息
//     *
//     * @param in
//     * @return
//     * @throws IOException
//     */
//    private static String readKey(InputStream in) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//        String readLine = null;
//        StringBuilder sb = new StringBuilder();
//        while ((readLine = br.readLine()) != null) {
//            if (readLine.charAt(0) == '-') {
//                continue;
//            } else {
//                sb.append(readLine);
//                sb.append('\r');
//            }
//        }
//
//        return sb.toString();
//    }
//
//    /**
//     * 打印公钥信息
//     *
//     * @param publicKey
//     */
//    public static void printPublicKeyInfo(PublicKey publicKey) {
//        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
//
//        byte[] encoded = rsaPublicKey.getEncoded();
//
//        String string = Base64.encodeToString(encoded, Base64.NO_WRAP);
//
//
//        Constant.MyPublicKey = string;
//
//        Log.e("RSA----------公钥------", "" + string);
//
//        System.out.println("----------RSAPublicKey----------");
//        System.out.println("Modulus.length=" + rsaPublicKey.getModulus().bitLength());
//        System.out.println("Modulus=" + rsaPublicKey.getModulus().toString());
//        System.out.println("PublicExponent.length=" + rsaPublicKey.getPublicExponent().bitLength());
//        System.out.println("PublicExponent=" + rsaPublicKey.getPublicExponent().toString());
//    }
//
//    public static void printPrivateKeyInfo(PrivateKey privateKey) {
//        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
//        byte[] encoded = rsaPrivateKey.getEncoded();
//        String string = Base64.encodeToString(encoded, Base64.NO_WRAP);
//
//        Log.e("RSA----------私钥------", "" + string);
//        Constant.MyPrivateKey = string;
//
//
//        System.out.println("----------RSAPrivateKey ----------");
//        System.out.println("Modulus.length=" + rsaPrivateKey.getModulus().bitLength());
//        System.out.println("Modulus=" + rsaPrivateKey.getModulus().toString());
//        System.out.println("PrivateExponent.length=" + rsaPrivateKey.getPrivateExponent().bitLength());
//        System.out.println("PrivatecExponent=" + rsaPrivateKey.getPrivateExponent().toString());
//    }
}
