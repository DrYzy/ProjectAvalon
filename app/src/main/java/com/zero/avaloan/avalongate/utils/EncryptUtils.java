package com.zero.avaloan.avalongate.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密算法类
 */
public class EncryptUtils {

    /* ==MD5加密算法======================================================== */

    /**
     * 32位MD5加密
     * @param paramString
     * @return
     */
    public static String md5(String paramString) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            return toHexString(localMessageDigest.digest());
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        return paramString;
    }

    /**
     * 16位MD5加密
     * @param paramString
     * @return
     */
    public static String md5_16(String paramString) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            return toHexString(localMessageDigest.digest()).substring(8, 24);
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        return paramString;
    }

    private static final char[] HEX_DIGITS = {48, 49, 50, 51, 52, 53, 54, 55,
            56, 57, 97, 98, 99, 100, 101, 102};

    private static String toHexString(byte[] paramArrayOfByte) {
        int length = paramArrayOfByte.length;
        StringBuilder localStringBuilder = new StringBuilder(2 * length);
        for (int i = 0; ; ++i) {
            if (i >= length)
                return localStringBuilder.toString().toUpperCase();
            localStringBuilder
                    .append(HEX_DIGITS[((0xF0 & paramArrayOfByte[i]) >>> 4)]);
            localStringBuilder.append(HEX_DIGITS[(0xF & paramArrayOfByte[i])]);
        }
    }

	/* ================================================================== */

}
