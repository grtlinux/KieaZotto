package org.tain.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CipherUtils {

	//private static int keySize = 1024;
	private static int RSABIT_SIZE = 2048;
	
	/*
	 * 1024비트 RSA 키쌍을 생성합니다.
	 */
	public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator gen;
		gen = KeyPairGenerator.getInstance("RSA");
		gen.initialize(RSABIT_SIZE, secureRandom);
		KeyPair keyPair = gen.generateKeyPair();
		return keyPair;
	}
	
	/*
	 * Public Key로 RSA 암호화를 수행합니다.
	 */
	public static String encryptRSA(String plainText, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bytePlain = cipher.doFinal(plainText.getBytes());
		String encrypted = Base64.getEncoder().encodeToString(bytePlain);
		return encrypted;
	}
	
	/*
	 * Private Key로 RAS 복호화를 수행합니다.
	 */
	public static String decryptRSA(String encrypted, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("RSA");
		byte[] byteEncrypted = Base64.getDecoder().decode(encrypted.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytePlain = cipher.doFinal(byteEncrypted);
		String decrypted = new String(bytePlain, "utf-8");
		return decrypted;
	}
	
	/*
	 * Base64 엔코딩된 개인키 문자열로부터 PrivateKey객체를 얻는다.
	 */
	public static PrivateKey getPrivateKeyFromBase64String(final String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		final String privateKeyString = keyString.replaceAll("\\n", "").replaceAll("-{5}[ a-zA-Z]*-{5}", "");
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString));
		
		return keyFactory.generatePrivate(keySpecPKCS8);
	}
	
	/*
	 * Base64 엔코딩된 공용키키 문자열로부터 PublicKey객체를 얻는다.
	 */
	public static PublicKey getPublicKeyFromBase64String(final String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		final String publicKeyString = keyString.replaceAll("\\n", "").replaceAll("-{5}[ a-zA-Z]*-{5}", "");
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyString));
		
		return keyFactory.generatePublic(keySpecX509);
	}
}
