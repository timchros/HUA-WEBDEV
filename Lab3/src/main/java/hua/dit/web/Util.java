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
		
		final List<String> userList = Arrays.asList("usera", "userb", "userc");
		userList.forEach( user -> { 
			// Keep Last Character as a Password
			final String password = "" + user.charAt(user.length()-1);
			final String password_hash = getHash256(password);
			System.out.println(user + " : " + password + " :: " + password_hash);
		});
		
		System.out.println();
		System.out.println(">> Util - main() - END - " + new Date());
	}
	
}
