package org.yxs.shiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MD5Utils {
	private static final String SALT = "yxs";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String password) {
		String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String password) {
		String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}
	public static void main(String[] args) {

		System.out.println(MD5Utils.encrypt("test", "123456"));

		String key = "yxs";
		byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
		System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keyByte, 16)));

	}

}
