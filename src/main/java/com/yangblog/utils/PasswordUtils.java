package com.yangblog.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.regex.Pattern;

public class PasswordUtils {
    private final static Pattern SAFE_PASSWORD_PATTERN = Pattern.compile("^.{6,20}$");
    private final static int HASH_ITERATIONS = 16;

    public static String encrypt(String password) {
        MessageDigest digest = DigestUtils.getSha1Digest();
        byte[] hashed = digest.digest(password.getBytes());
        for (int i = 1; i < HASH_ITERATIONS; i++) {
            digest.reset();
            hashed = digest.digest(hashed);
        }
        return Hex.encodeHexString(hashed);
    }

    public static boolean isSafe(String password) {
        return password != null && SAFE_PASSWORD_PATTERN.matcher(password).matches();
    }
}
