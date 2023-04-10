package com.example.util;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author ：yangan
 * @date ：2022/8/15 下午3:12
 * @description：数据的加密与解密
 * @version:
 */
public class EncryptUtil {

    /**
     * AES解密
     * @param encryptStr 密文
     * @param decryptKey 秘钥，必须为16个字符组成
     * @return 明文
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        if (StringUtils.isEmpty(encryptStr) || StringUtils.isEmpty(decryptKey)) {
            return null;
        }

        byte[] encryptByte = Base64.getDecoder().decode(encryptStr);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptByte);
        return new String(decryptBytes);
    }

    /**
     * AES加密
     * @param content 明文
     * @param encryptKey 秘钥，必须为16个字符组成
     * @return 密文
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(encryptKey)) {
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        byte[] encryptStr = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptStr);
    }

    // 测试加密与解密
    public static void main(String[] args) {
        Short a = 1233;
        System.out.println(a);
    }

    /**
     * AES加密算法，采用同一个密钥进行加解密，是一种对称密钥加密算法。
     */
    public final class AESUtil {
        //自己定义的秘钥,128bit即16位的随机串。也支持192,25bit,长度越长安全性越高，对应的加解密时间越长
        private final static String KEY_STR = "gOZ+l59TRoBajn3G";
        private final Key key;
        private AESUtil instance = new AESUtil();
        private static final String ALGORITHM = "AES";
        private static final String RANDOM_ALGORITHM = "SHA1PRNG";

        /**
         * 私有构造函数，防止通过实例化使用
         */
        private AESUtil() {
            try {
                KeyGenerator generator = null;//Java的秘钥生产器，使用的是AES
                generator = KeyGenerator.getInstance(ALGORITHM);
                SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);//随机数的算法，NativePRNG和SHA1PRNG
                random.setSeed(KEY_STR.getBytes("GBK"));//用我们设定的秘钥串作为随机数的种子,因为种子是我们固定的，产生的随机数也是固定的
                generator.init(random);
                key = generator.generateKey();//生成的秘钥，我们在加密解密需要用到相同秘钥
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//                throw new AppException("AES构造函数异常", e);
                throw new RuntimeException(e);
            }
        }
    }

}