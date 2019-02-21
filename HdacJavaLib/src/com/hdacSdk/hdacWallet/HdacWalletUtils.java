package com.hdacSdk.hdacWallet;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.util.List;

import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA256Digest;

import com.google.protobuf.ByteString;

/**
 * @brief Mnemonic Seed Word creation utilities for wallet creation
 * @details API support for generating random seed words \n
 * Mnemonic Seed Word generation for wallet generation class
 * @class HdacWalletUtils
 * @date 2018-05-23.
 * @author Hdac Technology 
 *
 */

public class HdacWalletUtils {
	/**
	 * @brief mnemonic seed words Number of words allowed in creation
	 */
	public enum NnmberOfWords {
		MNEMONIC_3_WORDS(1),
		MNEMONIC_6_WORDS(2),
		MNEMONIC_9_WORDS(3),
		MNEMONIC_12_WORDS(4),
		MNEMONIC_15_WORDS(5),
		MNEMONIC_18_WORDS(6),
		MNEMONIC_21_WORDS(7),
		MNEMONIC_24_WORDS(8);
		
		private int value;
	    private NnmberOfWords(int value){
	        this.value = value;
	    }
	    
	    public int getLengthValue() {
	    	return value * 32;
	    }
	}
	
	/**
	 * @code OS Names
	 */
	public static final String MAC = "mac";
	public static final String LINUX = "linux";
	public static final String WINDOWS = "win";
	public static final String UNKNOWN_OS = "unknown";
	
	/**
	 * @endcode
	 */
	
	/**
	 * @brief getting random seed words
	 * @param size NnmberOfWords
	 * @return List<String> seed words
	 */
	public static List<String> getRandomSeedWords(NnmberOfWords size){

		List<String> words = null;
		int len = size.getLengthValue();
		SecureRandom secureRandom = new SecureRandom();
		byte[] initialEntropy = new byte[len/8];
		secureRandom.nextBytes(initialEntropy);
		try {
			words = MnemonicCode.INSTANCE.toMnemonic(initialEntropy);
		} catch (MnemonicLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return words;
	}	
	
	/**
	 * @brief checking android os 
	 * @return boolean
	 */
	static public boolean isAndroidRuntime() {
    	final String runtime = System.getProperty("java.runtime.name");
        return (runtime != null && runtime.equals("Android Runtime")) ? true : false;        
    }
	
	/**
	 * @brief getting current os's name
	 * @return String os name
	 */
	static public String getOSName() {
		final String osName = System.getProperty("os.name").toLowerCase();
		
        if (osName.startsWith("win")) return WINDOWS;
        else if (osName.startsWith("linux")) return LINUX;
        else if (osName.startsWith("mac os")) return MAC;
        else return UNKNOWN_OS;
        
	}
	
	public static byte[] hexToBytes(String hex) { 
		if (hex == null || hex.length() == 0) {
			return null; 
		} 
		byte[] ba = new byte[hex.length() / 2]; 
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16); 
		} 
		return ba; 
	} 
	
	public static String bytesToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null; 
		} 
		StringBuffer sb = new StringBuffer(ba.length * 2); 
		String hexNumber; 
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString(); 
	} 

	public static byte[] toByteArray(String str)
	{
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte)Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
		}
		return bytes;
	}
	
	public static String reverseHexString(String hexStr)
	{
	    StringBuilder result = new StringBuilder();
	    for (int i = 0; i <=hexStr.length()-2; i=i+2)
	    {
	        result.append(new StringBuilder(hexStr.substring(i,i+2)).reverse());
	    }
	    return result.reverse().toString();
	}

	public static String toLittleEndian(long value)
	{
		byte[] a = getLittleEndian(value);

		StringBuilder sb = new StringBuilder(a.length * 2);
		for (byte b : a)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}
	
	private static byte[] getLittleEndian(long v)
	{
		byte[] buf = new byte[8];
		buf[0] = (byte)((v >>> 000) & 0xFF);
		buf[1] = (byte)((v >>> 010) & 0xFF);
		buf[2] = (byte)((v >>> 020) & 0xFF);
		buf[3] = (byte)((v >>> 030) & 0xFF);
		buf[4] = (byte)((v >>> 040) & 0xFF);
		buf[5] = (byte)((v >>> 050) & 0xFF);
		buf[6] = (byte)((v >>> 060) & 0xFF);
		buf[7] = (byte)((v >>> 070) & 0xFF);
		return buf;
	}
	
    private static byte[] ripemd160(byte[] buf) {    	
    	byte byteData[] = null;
    	RIPEMD160Digest digest = new RIPEMD160Digest();
    	digest.update(buf, 0, buf.length);
		byteData = new byte[digest.getDigestSize()];
		digest.doFinal(byteData, 0);
    	
		return byteData;    	
    }
    
    private static byte[] sha256(byte[] buf) {    	
    	SHA256Digest digest = new SHA256Digest();
        byte[] byteData = new byte[digest.getDigestSize()];
        digest.update(buf, 0, buf.length);
        digest.doFinal(byteData, 0);    	

		return byteData;    	
    }
	
    public static ByteString wbytesToHex(byte[] bytes) {
        return ByteString.copyFrom(Utils.HEX.encode(bytes).getBytes());
    }
    
    
    public static byte[] intToByte(int integer, ByteOrder order) {
    	
		ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
		buff.order(order);
 		buff.putInt(integer);
 		System.out.println("intTobyte : " + buff);
		return buff.array();
	}
    
    public static byte[] hexToByte(String hex) {    	
        return new BigInteger(hex,16).toByteArray();
	}
    
    public static byte calculateChecksum(byte[] initialEntropy) {
        int ent = initialEntropy.length * 8;
        byte mask = (byte) (0xff << 8 - ent / 32);
        byte[] bytes = sha256(initialEntropy);

        return (byte) (bytes[0] & mask);
    }	
}
