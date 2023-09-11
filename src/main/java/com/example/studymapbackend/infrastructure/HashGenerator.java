package com.example.studymapbackend.infrastructure;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashGenerator {
	
	public static void main (String[] args) {
		
	}

	public static String genHash() {
		try {
			SecureRandom secureRandom = new SecureRandom();
			byte[] randomBytes = new byte[32];

			secureRandom.nextBytes(randomBytes);
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(randomBytes);
			
			byte[] sha256Hash = md.digest();
			
			StringBuilder hexStringBuilder = new StringBuilder();
			for (byte b : sha256Hash) {
				String hex = String.format("%02x", b);
				hexStringBuilder.append(hex);
			}
			String sha256HashHex = hexStringBuilder.toString();
			return sha256HashHex;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
