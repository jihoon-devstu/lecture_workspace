package com.sinse.shopadmin.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

	public static String getSecuredPass(String pwd) {

		String pass = "minzino";
		StringBuffer sb = new StringBuffer();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			byte[] hash = md.digest(pass.getBytes("UTF-8"));

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				// System.out.println(hex);

				if (hex.length() < 2)
					sb.append("0");
				sb.append(hex); // 스트링 누적
			}
			System.out.println(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
