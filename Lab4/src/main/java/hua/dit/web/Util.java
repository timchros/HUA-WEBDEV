package hua.dit.web;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Util {

	/** Ensure that we will not create an instance of this class */
	private Util() {
		
	}
	
	// Hashing in Java
	// SHA (Secure Hash Algorithm) 256
	// https://www.baeldung.com/sha-256-hashing-java
	
	public static String getHash256(final String str) {
		if (str == null) return null;
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(encodedhash);
		} catch(NoSuchAlgorithmException e) {
			throw new RuntimeException("getHash256() problem !", e);
		}
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	/** For testing purposes */
	public static void main(String[] args) {
		System.out.println(">> Util - main() - START - " + new Date());
		System.out.println();
		
		// "Short" Strings
		final List<String> strList = Arrays.asList("str1", "str2", "str3");
		strList.forEach( str -> { 
			final String hash_value = getHash256(str);
			System.out.println(str + " :: " + hash_value);
		});
		
		// A "Long" String
		final String longStr = 
			"This is a long string we created for testing purposes. It can be as " +
			"long as we want. For example it can contains only 4 characters, such " + 
			"as in the previous examples. However, its obvious that this String " +
			"contains much more characters. In fact, its size exceeds 250 characters";
		final String longStrHashValue = getHash256(longStr);
		System.out.println("");
		System.out.println("String Length: " + longStr.length());
		System.out.println("Hash :: " + longStrHashValue);
		System.out.println("Hash Value Length: " + longStrHashValue.length());
		
		System.out.println();
		System.out.println(">> Util - main() - END - " + new Date());
		
	}
	
}
