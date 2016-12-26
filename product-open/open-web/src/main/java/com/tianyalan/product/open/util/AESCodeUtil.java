
package com.tianyalan.product.open.util;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCodeUtil {

	/**
	 * 加密
	 * 
	 * @param sSrc
	 *            需要加密的字符串
	 * @param sKey
	 *            加密用的key，限制16位
	 * @return
	 * @throws Exception
	 */
	public static String Encrypt(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			System.out.print("加密使用的KEY为空");

			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("加密使用的KEY长度不是16位");

			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes());

		return byte2hex(encrypted).toLowerCase();
	}

	/**
	 * 解密
	 * 
	 * @param sSrc
	 *            加密后的字符串
	 * @param sKey
	 *            加密用的key，限制16位
	 * @return
	 * @throws Exception
	 */
	public static String Decrypt(String sSrc, String sKey) throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("加密使用的KEY为空");

				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("加密使用的KEY长度不是16位");

				return null;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = hex2byte(sSrc);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);

				return originalString;
			} catch (Exception e) {
				e.printStackTrace();

				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}
	}

	/**
	 * 生成指定长度的key
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomKey(int length) {
		String base = "xatb5icze0fh19jd2lqm3no4pr6su8vgw7yk";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}

		return sb.toString();
	}

	private static byte[] hex2byte(String strhex) {
		if (strhex == null) {

			return null;
		}
		int l = strhex.length();
		if (l % 2 == 1) {

			return null;
		}
		byte[] b = new byte[l / 2];
		for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
		}

		return b;
	}

	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}

		return hs.toUpperCase();
	}

	public static void main(String[] args) throws Exception {
//		String cKey = getRandomKey(16);
//		System.out.println("key:" + cKey);
//		// 需要加密的字串
//		String cSrc = "third_10010_20001_" + getRandomKey(10);
//		System.out.println("src:" + cSrc);
//		// 加密
//		long lStart = System.currentTimeMillis();
//		String enString = AESCodeUtil.Encrypt(cSrc, cKey);
//		System.out.println("加密后的字串是：" + enString);
//
//		long lUseTime = System.currentTimeMillis() - lStart;
//		System.out.println("加密耗时：" + lUseTime + "毫秒");
//		// 解密
//		lStart = System.currentTimeMillis();
//		String DeString = AESCodeUtil.Decrypt(enString, cKey);
//		System.out.println("解密后的字串是：" + DeString);
//		lUseTime = System.currentTimeMillis() - lStart;
//		System.out.println("解密耗时：" + lUseTime + "毫秒");		
		
		
		String test = AESCodeUtil.Encrypt("tianyalan_secret_key", "p3vgrz81vw3uyo4y");
		System.out.println("加密后的字串是：" + test);
	}
}