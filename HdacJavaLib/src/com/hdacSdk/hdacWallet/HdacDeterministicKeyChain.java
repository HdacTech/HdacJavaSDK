package com.hdacSdk.hdacWallet;



import javax.annotation.Nullable;

import org.bitcoinj.crypto.*;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import com.google.common.collect.ImmutableList;

import org.bitcoinj.crypto.DeterministicKey;

/**
 * @brief Generate Derive key of key chain of HD (hierarchical deterministic) wallet 
 * @details Generate all derivative keys of key chain from master seed
 * @class HdacDeterministicKeyChain
 * @date 2018-05-23.
 * @author Hdac Technology 
 *
 */
public class HdacDeterministicKeyChain {
    public static final String DEFAULT_PASSPHRASE_FOR_MNEMONIC = "";

    @Nullable private DeterministicKey rootKey;
    @Nullable private DeterministicSeed seed;
    
    private DeterministicKey externalKey;
    private DeterministicKey internalKey;
    private HdacDeterministicKey hdacExternalKey;
    private HdacDeterministicKey hdacInternalKey;

//    private long creationTimeSeconds = MnemonicCode.BIP39_STANDARDISATION_TIME_SECS;

    public static final ImmutableList<ChildNumber> ACCOUNT_ZERO_PATH = ImmutableList.of(ChildNumber.ZERO_HARDENED);
    public static final ImmutableList<ChildNumber> EXTERNAL_PATH = ImmutableList.of(ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    public static final ImmutableList<ChildNumber> INTERNAL_PATH = ImmutableList.of(ChildNumber.ZERO_HARDENED, ChildNumber.ONE);
    
    public static final ImmutableList<ChildNumber> HDAC_ACCOUNT_PARENT_ZERO_PATH = ImmutableList.of(new ChildNumber(44, true),
    		new ChildNumber(200, true), new ChildNumber(0, true));
    
    public static final ImmutableList<ChildNumber> HDAC_ACCOUNT_EXTERNAL_ZERO_PATH = ImmutableList.of(new ChildNumber(44, true),
    		new ChildNumber(200, true), new ChildNumber(0, true), ChildNumber.ZERO);
    
    public static final ImmutableList<ChildNumber> HDAC_ACCOUNT_INTERNAL_ZERO_PATH = ImmutableList.of(new ChildNumber(44, true),
    		new ChildNumber(200, true), new ChildNumber(0, true), ChildNumber.ONE);
    
    private final DeterministicKeyChain hdKeyChain;
    
    public HdacDeterministicKeyChain(DeterministicSeed seed, DeterministicKeyChain hdKeyChain) {                
        this.hdKeyChain = hdKeyChain;
        this.seed = seed;
        initHdacHierarchyUnencrypted();        
    }
    
    private void initHdacHierarchyUnencrypted() {
    	externalKey = hdKeyChain.getKeyByPath(HDAC_ACCOUNT_EXTERNAL_ZERO_PATH, true);
        internalKey = hdKeyChain.getKeyByPath(HDAC_ACCOUNT_INTERNAL_ZERO_PATH, true);
        
        hdacExternalKey = new HdacDeterministicKey(hdKeyChain, HDAC_ACCOUNT_EXTERNAL_ZERO_PATH, true);
        hdacInternalKey = new HdacDeterministicKey(hdKeyChain, HDAC_ACCOUNT_INTERNAL_ZERO_PATH, true);
    }
    
    /**
     * It's API for Hdac public/private address    
     */
    //common api
    /**
     * @brief Generate HdacDeterministicKey from generated key chain
     * @param path
     * @param create The parameter is ignored 
     * @return HdacDeterministicKey
     */
    public HdacDeterministicKey getKeyByPath(ImmutableList<ChildNumber> path, boolean create) {
    	return new HdacDeterministicKey(hdKeyChain, path, create);
    }    
    
    //external address
    /**
     * @brief Generate child public key from external key
     * @details external -> m/44'/200'/0'/0/childIndexs
     * @param childIndexs
     * @return
     */
    public byte[] getHdacExternalPubKey(int ...childIndexs) {
    	ImmutableList<ChildNumber> hdacExKey = ImmutableList.of(new ChildNumber(44, true),
        		new ChildNumber(200, true), new ChildNumber(0, true), new ChildNumber(0, false));
    	ImmutableList.Builder <ChildNumber> builder = ImmutableList.builder();
    	builder.addAll(hdacExKey);
    	for(int i=0; i<childIndexs.length; i++) 
    		builder.add(new ChildNumber(childIndexs[i], false));
    	
        return hdKeyChain.getKeyByPath(builder.build(), true).getPubKey();
    }
        
    /**
     * @brief Generate child private key from external key
     * @details external -> m/44'/200'/0'/0/childIndexs
     * @param childIndexs
     * @return
     */
    public byte[] getExternalPriKey(int ...childIndexs) {
    	ImmutableList<ChildNumber> hdacExKey = ImmutableList.of(new ChildNumber(44, true),
        		new ChildNumber(200, true), new ChildNumber(0, true), new ChildNumber(0, false));
    	ImmutableList.Builder <ChildNumber> builder = ImmutableList.builder();
    	builder.addAll(hdacExKey);
    	for(int i=0; i<childIndexs.length; i++) 
    		builder.add(new ChildNumber(childIndexs[i], false));
    	
    	return hdKeyChain.getKeyByPath(builder.build(), true).getPrivKeyBytes();
    }
    
    /**
     * @brief Generate external public key
     * @details m/44'/200'/0'/0 
     * @return bytes
     */
    public byte[] getHdacExternalZeroPublicKey() {
    	return externalKey.getPubKey();
    }
    
    /**
     * @brief Generate external private key
     * @details m/44'/200'/0'/0 
     * @return bytes
     */
    public byte[] getHdacExternalZeroPrivateKey() {
    	return externalKey.getPrivKeyBytes();
    }
    
    /**
     * @brief Generate external deterministic key
     * @details m/44'/200'/0'/0 
     * @return HdacDeterministicKey
     */
    public HdacDeterministicKey getHdacExternalKey() {
    	return hdacExternalKey;
    }
    
    //internal address
    /**
     * @brief Generate child public key from Internal key
     * @details internal -> m/44'/200'/0'/1/childIndexs
     * @param childIndexs
     * @return
     */
    public byte[] getHdacInternalPubKey(int ...childIndexs) {
    	ImmutableList<ChildNumber> hdacInKey = ImmutableList.of(new ChildNumber(44, true),
        		new ChildNumber(200, true), new ChildNumber(0, true), new ChildNumber(0, false));
    	
    	ImmutableList.Builder <ChildNumber> builder = ImmutableList.builder();
    	builder.addAll(hdacInKey);
    	for(int i=0; i<childIndexs.length; i++) 
    		builder.add(new ChildNumber(childIndexs[i], false));
    	
        return hdKeyChain.getKeyByPath(hdacInKey, true).getPubKey();
    }
      
    /**
     * @brief Generate child private key from Internal key
     * @details internal -> m/44'/200'/0'/1/childIndexs
     * @param childIndexs
     * @return
     */
    public byte[] getInternalPriKey(int ...childIndexs) {
    	ImmutableList<ChildNumber> hdacInKey = ImmutableList.of(new ChildNumber(44, true),
        		new ChildNumber(200, true), new ChildNumber(0, true), new ChildNumber(0, false));
    	
    	ImmutableList.Builder <ChildNumber> builder = ImmutableList.builder();
    	builder.addAll(hdacInKey);
    	for(int i=0; i<childIndexs.length; i++) 
    		builder.add(new ChildNumber(childIndexs[i], false));
    	
    	return hdKeyChain.getKeyByPath(hdacInKey, true).getPrivKeyBytes();
    }
    
    /**
     * @brief Generate internal public key
     * @details m/44'/200'/0'/1 
     * @return bytes
     */
    public byte[] getHdacInternalZeroPublicKey() {
    	return internalKey.getPubKey();
    }
    
    /**
     * @brief Generate internal private key
     * @details m/44'/200'/0'/1
     * @return bytes
     */
    public byte[] getHdacInternalZeroPrivateKey() {
    	return internalKey.getPrivKeyBytes();
    }    
    
    /**
     * @brief Generate internal deterministic key
     * @details m/44'/200'/0'/1
     * @return HdacDeterministicKey
     */
    public HdacDeterministicKey getHdacInternalKey() {
    	return hdacInternalKey;
    }
    
    //////////////////////////////////////
    
    /*
     * for etc coin    
     */
    /**
     * @brief Key chain support for coins other than Hdac \n
     * Generate public key
     * @param pubPath 
     * @return
     */
    public byte[] getPublicKey(ImmutableList<ChildNumber> pubPath) {
    	return hdKeyChain.getKeyByPath(pubPath, true).getPubKey();
    }
    
    /**
     * @brief Key chain support for coins other than Hdac \n
     * Generate private key
     * @param priPath
     * @return
     */
    public byte[] getPrivateKey(ImmutableList<ChildNumber> priPath) {
    	return hdKeyChain.getKeyByPath(priPath, true).getPrivKeyBytes();
    }   
    //////////////////////////////////////

    /**
     * @brief Deterministic Key Lookup from pubkeyHash
     * @param pubkeyHash
     * @return DeterministicKey
     */
    public DeterministicKey findKeyFromPubHash(byte[] pubkeyHash) {
        return (DeterministicKey) hdKeyChain.findKeyFromPubHash(pubkeyHash);        
    }

    /**
     * @brief Deterministic Key Lookup from pubkey
     * @param pubkey
     * @return DeterministicKey
     */
    public DeterministicKey findKeyFromPubKey(byte[] pubkey) {
        return (DeterministicKey) hdKeyChain.findKeyFromPubKey(pubkey);        
    }
        
	
}
