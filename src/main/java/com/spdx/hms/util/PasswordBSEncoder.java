package com.spdx.hms.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordBSEncoder implements PasswordEncoder,InitializingBean {

    @Value("${password.encoder.secret}")
    private String secret;

    @Value("${password.encoder.iteration}")
    private Integer iteration;

    @Value("${password.encoder.keylength}")
    private Integer keylength;
    
    private SecretKeySpec secretKey;
    
    private IvParameterSpec ivspec;

    /**
     * More info (https://www.owasp.org/index.php/Hashing_Java) 404 :(
     * @param cs password
     * @return encoded password
     */
    @Override
    public String encode(CharSequence cs) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                                            .generateSecret(new PBEKeySpec(cs.toString().toCharArray(), secret.getBytes(), iteration, keylength))
                                            .getEncoded();
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }
    
   

    @Override
    public boolean matches(CharSequence cs, String string) {
        return encode(cs).equals(string);
    }
    
    
    public String encrypt(String data) {
    	try {
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        return Base64.getEncoder()
                .encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    	} catch(NoSuchAlgorithmException 
    			| NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
    		throw new RuntimeException(ex);
    	}
    }
    
    public String decrypt(String data)  {
    	try {
    		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	 cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
    	 return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
    	} catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
    		throw new RuntimeException(ex);
    	}
    }
    
    public static void main(String str[]) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
    	PasswordBSEncoder bsEncoder=new PasswordBSEncoder();
    	String cs="test";
    	byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(new PBEKeySpec(cs.toString().toCharArray(), "yo@4h5ConNet!".getBytes(), 65536, 256))
                .getEncoded();
    	String secc=Base64.getEncoder().encodeToString(result);
    	System.out.println(secc);
    	byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivspec = new IvParameterSpec(iv);
   
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        KeySpec spec = new PBEKeySpec("PBKDF2WithHmacSHA512".toCharArray(), "yo@4h5ConNet!".getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
   
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
        String test=Base64.getEncoder()
                .encodeToString(cipher.doFinal(cs.getBytes(StandardCharsets.UTF_8)));
        System.out.println(test);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        System.out.println( new String(cipher.doFinal(Base64.getDecoder().decode(test))));
    }



	@Override
	public void afterPropertiesSet() throws Exception {
		byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         ivspec = new IvParameterSpec(iv);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        KeySpec spec = new PBEKeySpec("PBKDF2WithHmacSHA512".toCharArray(), secret.getBytes(), iteration, keylength);
        SecretKey tmp = factory.generateSecret(spec);
        secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		
	}
}
