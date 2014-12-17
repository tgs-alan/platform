package com.tgs.platform.util;

import java.security.MessageDigest;

public class HashUtil {
	/**
	 * @param arrayBytes
	 * @return
	 */
	private static String convertByteArrayToHexString(byte[] arrayBytes) {
	    StringBuffer stringBuffer = new StringBuffer();
	    for (int i = 0; i < arrayBytes.length; i++) {
	        stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
	                .substring(1));
	    }
	    return stringBuffer.toString();
	}
	
	public String getHash(String source, String algorithm)
	{
		try {
			MessageDigest digest;
			
			// making invitation hash(algorithm can be "MD5", "SHA-1", "SHA-256")
			digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(source.getBytes("UTF-8"));
			
			return convertByteArrayToHexString(hashedBytes);
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
