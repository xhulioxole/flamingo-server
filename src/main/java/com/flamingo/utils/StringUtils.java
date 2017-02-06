package com.flamingo.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Xhulio on 2/15/2016.
 */
public class StringUtils {

    /**
     * Returns authorization header value after Bearer
     *
     * @param header
     * @return String
     */
    public static String getAuthorizationHeaderValue(String header) throws ArrayIndexOutOfBoundsException {
        return header.split("Bearer ")[1];
    }

    /**
     * Encrypt password using sha1 encryption
     * @param password
     * @return
     */
    public static String SHA1(String password) {
        MessageDigest messageDigest;
        String hashValue = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
            byte[] e = password.getBytes();
            messageDigest.update(e, 0, password.length());
            hashValue = (new BigInteger(1, messageDigest.digest())).toString(16);
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
        }

        return hashValue;
    }
}
