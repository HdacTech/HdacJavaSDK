package com.hdacSdk.hdacWallet;

import java.security.SecureRandom;
import java.util.List;

import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException;

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

}
