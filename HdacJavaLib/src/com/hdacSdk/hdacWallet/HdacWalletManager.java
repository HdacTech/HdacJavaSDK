package com.hdacSdk.hdacWallet;

import java.util.ArrayList;
import java.util.List;
import org.bitcoinj.wallet.DeterministicSeed;

/**
 * @brief Create and manage Hdac wallet
 * @details Create Hdac wallet and manage wallets created in list form
 * @class HdacWalletManager
 * @date 2018-05-23.
 * @author Hdac Technology 
 *
 */
public class HdacWalletManager {
	
	private static HdacWalletManager instance = null;
	private List<HdacWallet> mWalletList = new ArrayList<HdacWallet>();
	
	private HdacWalletManager() {
		initWalletManager();
	}
	
	public static HdacWalletManager getInstance() {
		if(instance == null) return new HdacWalletManager();
		return instance;
	}
	
	private void initWalletManager(){
		HdacCoreAddrParams params = new HdacCoreAddrParams(false);
		new HdacNetworkParams(params);		
	}	

	
	/* manage wallets
	 * start          */
	//new wallet
	
	/**
	 * @brief Create a wallet that is not included in the wallet list through seed words
	 * @param words seed words
	 * @param passphrase passphrase
	 * @param params HdacCoreAddrParams
	 * @return HdacWallet
	 */
	public static HdacWallet generateNewWallet(List<String> words, String passphrase, HdacCoreAddrParams params) {
		try {
		    if(words==null||words.size()<3) return null;
		    else if(params!=null) {
		    	if(passphrase!=null) return new HdacWallet(words, passphrase, new HdacNetworkParams(params));
		    	else return new HdacWallet(words, new HdacNetworkParams(params));
		    }
			else if(passphrase!=null) return new HdacWallet(words, passphrase, HdacNetworkParams.getDefault());
			else return new HdacWallet(words, HdacNetworkParams.getDefault());
		}catch(IllegalStateException e) {
			e.printStackTrace();		
		}
		return null;
	}
	
	//from DeterministicSeed
	/**
	 * @brief Create a wallet that is not included in the wallet list through seed words
	 * @param seed DeterministicSeed
	 * @param passphrase passphrase
	 * @param params HdacCoreAddrParams
	 * @return HdacWallet
	 */
	public static HdacWallet generateNewWallet(DeterministicSeed seed, String passphrase, HdacCoreAddrParams params) {
		HdacWallet wallet = null;
		try {	
			if(seed==null) return null;
			else if(params!=null) {
		    	if(passphrase!=null) return new HdacWallet(seed, new HdacNetworkParams(params));
		    	else return new HdacWallet(seed, new HdacNetworkParams(params));
		    }
			else if(passphrase!=null) return new HdacWallet(seed, HdacNetworkParams.getDefault());
			else return new HdacWallet(seed, HdacNetworkParams.getDefault());
		}catch(IllegalStateException e) {
			e.printStackTrace();		
		}
		return wallet;
	}
	
	//from entropy
	/**
	 * @brief Create a wallet that is not included in the wallet list via entropy
	 * @param entropy entropy
	 * @param passphrase passphrase
	 * @param params HdacCoreAddrParams
	 * @return HdacWallet
	 */
	public static HdacWallet generateNewWallet(byte[] entropy, String passphrase, HdacCoreAddrParams params) {
		HdacWallet wallet = null;
		try {
			if(entropy==null||entropy.length % 4 != 0) return null;
		    else if(params!=null) {
		    	if(passphrase!=null) wallet = new HdacWallet(entropy, passphrase, new HdacNetworkParams(params));
		    	else wallet = new HdacWallet(entropy, "", new HdacNetworkParams(params));
		    }
		    else if(passphrase!=null) wallet = new HdacWallet(entropy, passphrase, HdacNetworkParams.getDefault());
		    else wallet = new HdacWallet(entropy, "", HdacNetworkParams.getDefault());
		}catch(IllegalStateException e) {
			e.printStackTrace();		
		}
		return wallet;
	}	
	
	//from word list
	/**
	 * @brief Create a wallet of default HdacNetworkParams and add it to the wallet list
	 * @param words seed words
	 * @param passphrase passphrase
	 * @return HdacWallet
	 */
	public HdacWallet addNewWallet(List<String> words, String passphrase) {
		HdacWallet wallet = null;
		try {
			if(words==null||words.size()<3) return null;
		    else if(passphrase!=null) wallet = new HdacWallet(words, passphrase, HdacNetworkParams.getDefault());
		    else wallet = new HdacWallet(words, HdacNetworkParams.getDefault());
			
			mWalletList.add(wallet);
		}catch(IllegalStateException e) {
			e.printStackTrace();		
		}
		return wallet;		
	}
	
	/**
	 * @brief Create a wallet and add it to the wallet list
	 * @param words seed words
	 * @param passphrase passphrase
	 * @param params
	 * @return HdacWallet
	 */
	public HdacWallet addNewWallet(List<String> words, String passphrase, HdacNetworkParams params) {
		HdacWallet wallet = null;
		try {
			if(words==null||words.size()<3) return null;
		    else if(params!=null) {
		    	if(passphrase!=null) wallet = new HdacWallet(words, passphrase, params);
		    	else wallet = new HdacWallet(words, params);
		    }
		    else if(passphrase!=null) wallet = new HdacWallet(words, passphrase, HdacNetworkParams.getDefault());
		    else wallet = new HdacWallet(words, HdacNetworkParams.getDefault());
			mWalletList.add(wallet);
		}catch(IllegalStateException e) {
			e.printStackTrace();		
		}
		return wallet;		
	}
	
	/**
	 * @brief Create a wallet and add it to the wallet list
	 * @param words seed words
	 * @param passphrase passphrase
	 * @param params HdacCoreAddrParams
	 * @return HdacWallet
	 */
	public HdacWallet addNewWallet(List<String> words, String passphrase, HdacCoreAddrParams params) {
		HdacWallet wallet = null;
		try {
			if(words==null||words.size()<3) return null;
		    else if(params!=null) {
		    	if(passphrase!=null) wallet = new HdacWallet(words, passphrase, new HdacNetworkParams(params));
		    	else wallet = new HdacWallet(words, new HdacNetworkParams(params));
		    }
		    else if(passphrase!=null) wallet = new HdacWallet(words, passphrase, HdacNetworkParams.getDefault());
		    else wallet = new HdacWallet(words, HdacNetworkParams.getDefault());
			mWalletList.add(wallet);
		}catch(IllegalStateException e) {
			e.printStackTrace();		
		}
		return wallet;		
	}
	
	//from DeterministicSeed
	/**
	 * @brief Create a wallet with DeterministicSeed and add it to the wallet list
	 * @param seed DeterministicSeed
	 * @param params HdacCoreAddrParams
	 * @return HdacWallet
	 */
	public HdacWallet addWalletFromSeed(DeterministicSeed seed, HdacCoreAddrParams params) {
		HdacWallet wallet = null;
		try {	
			if(seed==null) return null;
			else if(params!=null) wallet = new HdacWallet(seed, new HdacNetworkParams(params));
		    else wallet = new HdacWallet(seed, HdacNetworkParams.getDefault());
			mWalletList.add(wallet);
		}catch(IllegalStateException e) {
			e.printStackTrace();		
		}
		return wallet;
	}
	
	//from entropy
	/**
	 * @brief Create a wallet via entropy and add it to the wallet list
	 * @param entropy entropy
	 * @param passphrase passphrase
	 * @param params HdacCoreAddrParams
	 * @return HdacWallet
	 */
	public HdacWallet addWalletFromEntropy(byte[] entropy, String passphrase, HdacCoreAddrParams params) {
		HdacWallet wallet = null;
		try {
			if(entropy==null||entropy.length % 4 != 0) return null;
		    else if(params!=null) {
		    	if(passphrase!=null) wallet = new HdacWallet(entropy, passphrase, new HdacNetworkParams(params));
		    	else wallet = new HdacWallet(entropy, "", new HdacNetworkParams(params));
		    }
		    else if(passphrase!=null) wallet = new HdacWallet(entropy, passphrase, HdacNetworkParams.getDefault());
		    else wallet = new HdacWallet(entropy, "", HdacNetworkParams.getDefault());
			mWalletList.add(wallet);
		
		}catch(IllegalStateException e) {
			e.printStackTrace();		
		}
		return wallet;
	}		
	
	/**
	 * @brief  getting wallet list
	 * @return List<HdacWallet>
	 */
	public List<HdacWallet> getWalletList(){
		return mWalletList;
	}
	
	/**
	 * @brief getting wallet from wallet list by index
	 * @param index index of list
	 * @return HdacWallet
	 */
	public HdacWallet getWalletByIndex(int index){
		if(index>=mWalletList.size() || index<0) return null;
		return mWalletList.get(index);
	}
	
	/**
	 * @brief getting index of wallet from list
	 * @param wallet HdacWallet
	 * @return int index
	 */
	public int getWalletIndex(HdacWallet wallet) {
		for(int i=0; i<mWalletList.size(); i++) {
			if(mWalletList.get(i)==wallet) return i;
		}
		
		return -1;
	}
	/* manage wallets
	 * end          */
	
	/* control wallet address
	 * start
	 */
	/**
	 * @brief Hdac address of public key derived from wallet external to index
	 * @param wallet HdacWallet
	 * @param index Derived index
	 * @return String address
	 */
	public String getHdacExternalAddress(HdacWallet wallet, int index) {
		String addr = "";
		
		if(wallet!=null) {
			HdacDeterministicKey hdKey = wallet.getHDKeyChain().getHdacExternalKey();
			byte[] pubkey = hdKey.derive(index, false).getPublicKey();
			addr = wallet.getHdacAddressByKey(pubkey);
		}
		
		return addr;
	}
	
	/**
	 * @brief The Hdac address of the public key derived from the wallet external corresponding to wallet_id as an index.
	 * @param wallet_id index of wallet list
	 * @param index Derived index
	 * @return String address
	 */
	public String getHdacExternalAddressById(int wallet_id, int index) {
		String addr = "";
		HdacWallet wallet = getWalletByIndex(wallet_id);
		if(wallet!=null) addr = getHdacExternalAddress(wallet, index);
		return addr;
	}
	
	/** 
	 * @brief Hdac address of the public key derived from wallet internal to index
	 * @param wallet HdacWallet
	 * @param index Derived index
	 * @return String address
	 */
	public String getHdacInternalAddress(HdacWallet wallet, int index) {
		String addr = "";
		
		if(wallet!=null) {
			HdacDeterministicKey hdKey = wallet.getHDKeyChain().getHdacInternalKey();
			byte[] pubkey = hdKey.derive(index, false).getPublicKey();
			addr = wallet.getHdacAddressByKey(pubkey);
		}
		return addr;
	}
	
	/**
	 * @brief The Hdac address of the public key derived from the wallet internal corresponding to wallet_id as an index.
	 * @param wallet_id index of wallet list
	 * @param index Derived index
	 * @return String address
	 */
	public String getHdacInternalAddressById(int wallet_id, int index) {
		String addr = "";
		
		HdacWallet wallet = getWalletByIndex(wallet_id);
		if(wallet!=null) addr = getHdacInternalAddress(wallet, index);
		return addr;
	}	   
    

}
