package com.hdacSdk.hdacWallet;

import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Utils;
//import org.bitcoinj.core.*;
import org.bitcoinj.crypto.*;
import org.bitcoinj.crypto.MnemonicException.MnemonicChecksumException;
import org.bitcoinj.crypto.MnemonicException.MnemonicWordException;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.KeyChainGroup;

import com.google.protobuf.ByteString;
import org.bitcoinj.wallet.Wallet;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA256Digest;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief This utility is to create an wallet for hdac(mulitchin) based on bitcoinj.
 * @details Hdac wallet creation and generated wallet data interface support
 * @class HdacWallet
 * @date 2018-05-23.
 * @author Hdac Technology 
 *
 */
public class HdacWallet {
	
    private Wallet wallet;
    private HdacDeterministicKeyChain hdDKeyChain;
    private HdacNetworkParams hdNetParams;
    //private static String password;
    private static int MAX_INTERNAL_KEY = 10;
    private static int MAX_EXTERNAL_KEY = 10;
    private boolean isValidWallet = true;
    private int walletVersion = 0;
    private byte[] entropy = null;
    
    
    public HdacWallet(List<String> words, String passphrase, HdacNetworkParams params) {
    	try {    		
    		isValidWallet = createWallet(words, passphrase, params);    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isValidWallet = false;
		}
    }
    
    public HdacWallet(List<String> words, HdacNetworkParams params) {
    	this(words, "", params);
    }    
    
    public HdacWallet(DeterministicSeed seed, HdacNetworkParams params) {
    	try {
			isValidWallet = createWallet(seed, params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isValidWallet = false;
			e.printStackTrace();
		}
    }   
    
    public HdacWallet(byte[] entropy, String passphrase, HdacNetworkParams params) {
    	try {
    		List<String> words = toMnemonic(entropy);
			isValidWallet = createWallet( words, passphrase, params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isValidWallet = false;
			e.printStackTrace();
		}
    }
    
    private boolean createWallet(List<String> words, String passphrase, HdacNetworkParams params) throws IOException {
        
    	DeterministicSeed seed;
    	
        seed = new DeterministicSeed(words, null, passphrase, 0);        
        try {
            seed.check();
        } catch (MnemonicException.MnemonicLengthException e) {
        	System.out.print("The seed did not have 12 words in, perhaps you need quotes around it?");
            return false;
        } catch (MnemonicException.MnemonicWordException e) {
        	System.out.print("The seed contained an unrecognised word: " + e.badWord);
            return false;
        } catch (MnemonicException.MnemonicChecksumException e) {
        	System.out.print("The seed did not pass checksumming, perhaps one of the words is wrong?");
            return false;
        } catch (MnemonicException e) {
            // not reached - all subclasses handled above
            throw new RuntimeException(e);
        }
        KeyChainGroup keyChainGroup = new KeyChainGroup(params, new DeterministicSeed(words, null,"", 0));
        wallet = new Wallet(params, keyChainGroup);
        wallet.setVersion(walletVersion);
        hdDKeyChain = new HdacDeterministicKeyChain(seed, wallet.getActiveKeyChain());
        hdNetParams = params;
        entropy = toEntropy(words);
        
        return true;
    }
    
    private boolean createWallet(DeterministicSeed seed, HdacNetworkParams params) throws IOException {  
    	
        try {
            seed.check();
        } catch (MnemonicException.MnemonicLengthException e) {
            return false;
        } catch (MnemonicException.MnemonicWordException e) {
            return false;
        } catch (MnemonicException.MnemonicChecksumException e) {
            return false;
        } catch (MnemonicException e) {
            // not reached - all subclasses handled above
            throw new RuntimeException(e);
        }
        
        wallet = Wallet.fromSeed(params, seed);        
        hdDKeyChain = new HdacDeterministicKeyChain(seed, wallet.getActiveKeyChain());
        hdNetParams = params;
        entropy = toEntropy(seed.getMnemonicCode());
        
        return true;
    }
    
    /**
     * @brief getting wallet object of bitcoinj
     * @return Wallet bitcoinj wallet object
     */
    public Wallet getWallet() {
    	return wallet;
    }
    
    /**
     * @brief View HdacNetworkParams registered on wallet
     * @return HdacNetworkParams
     */
    public HdacNetworkParams getNetworkParams(){
    	return hdNetParams;
    }
    
    /**
     * @brief getting deterministicSeed of wallet
     * @return DeterministicSeed
     */
    public DeterministicSeed getSeed() {
    	return wallet.getKeyChainSeed();
    }
      
    /**
     * @brief Check if wallet is available after creating wallet 
     * @return boolean false -> invalid wallet
     */
    public boolean isValidWallet() {
    	return isValidWallet;
    }
    
    /**
     * @brief Generate hex string type Hdac address through input public key
     * @param byteKey public key
     * @return String Hdac Address
     */
    public String getHdacAddressByKey(byte[] byteKey) {
    	return convPubkeyToHdacAddress(byteKey);
    }
    
    /**
     * @brief Generate Hdac Address in hex string form of wallet
     * @return String Hdac Address
     */
    public String getHdacAddress() {
    	if(hdDKeyChain==null) return null;
    	HdacDeterministicKey hdKey = hdDKeyChain.getHdacExternalKey();
		byte[] pubkey = hdKey.derive(0, false).getPublicKey();
    	return convPubkeyToHdacAddress(pubkey);
    }
    
    /**
     * @brief wallet의 HdacDeterministicKeyChain 조회
     * @return HdacDeterministicKeyChain
     */
    public HdacDeterministicKeyChain getHDKeyChain() {
    	return hdDKeyChain;
    }
    
    /**
     * @brief Generate hex string type Hdac address through input public key
     * @param buf public key
     * @return String Hdac Address
     */
    public String convPubkeyToHdacAddress(byte[] buf) {
    	if(hdNetParams==null || buf==null) return null;
    	byte[] hash = ripemd160(sha256(buf));
    	byte[] payload = new byte[hash.length + 1];
    	payload[0] = (byte)hdNetParams.getAddressHeader();   
    	for(int i=0;i<hash.length;i++) {
    		payload[i+1] = hash[i];
    	}
    	
    	ByteBuffer payloadHash = ByteBuffer.wrap(sha256(sha256(payload)));
    	
    	payloadHash.flip(); //position 0
    	payloadHash.limit(4);
    	
    	byte[] checksum = new byte[4];
    	for(int cs_id=0;cs_id<checksum.length;cs_id++) {
    		checksum[cs_id] = payloadHash.get(cs_id);
    	}
    	ArrayUtils.reverse(checksum);
    	
    	byte[] hdacChecksum = hexToByte(hdNetParams.getAddressChecksumValue());
    	ArrayUtils.reverse(hdacChecksum);
    	int length = hdacChecksum.length;//Math.max(checksum.length, hdacChecksum.length);
    	byte[] checksumBuf = new byte[length];
    	for (int i = 0; i < length; ++i) {
    		byte xor = (byte)(0xff & ((int)(i<checksum.length?checksum[i]:0) ^ (int)(i<hdacChecksum.length?hdacChecksum[i]:0)));
    		checksumBuf[i] = xor;
        }
    	
    	byte[] hdacAddrByte = new byte[payload.length + length];
    	int hdacAddrByte_index = 0;
    	for(;hdacAddrByte_index<payload.length;hdacAddrByte_index++) {
    		hdacAddrByte[hdacAddrByte_index] = payload[hdacAddrByte_index];
    	}
    	
    	ArrayUtils.reverse(checksumBuf);
    	for(int i=0; i<checksumBuf.length; i++) {
    		hdacAddrByte[hdacAddrByte_index + i] = checksumBuf[i];
    	}    	
    	return Base58.encode(hdacAddrByte);    	
    }
    
    private String encodeBase58WIF(byte[] buf) {
    	if(hdNetParams==null || buf==null) return null;
    	
    	byte[] payload = new byte[buf.length + 2];
    	payload[0] = (byte)hdNetParams.getDumpedPrivateKeyHeader();   
    	for(int i=0;i<buf.length;i++) {
    		payload[i+1] = buf[i];
    	}
    	payload[payload.length-1] = 0x01;
    	ByteBuffer payloadHash = ByteBuffer.wrap(sha256(sha256(payload)));
    	
    	payloadHash.flip(); //position 0
    	payloadHash.limit(4);
    	
    	byte[] checksum = new byte[4];
    	for(int cs_id=0;cs_id<checksum.length;cs_id++) {
    		checksum[cs_id] = payloadHash.get(cs_id);
    	}
    	
    	int length = checksum.length;
    	
    	byte[] addr_Byte = new byte[payload.length + length];
    	int addrByte_index = 0;
    	for(;addrByte_index<payload.length;addrByte_index++) {
    		addr_Byte[addrByte_index] = payload[addrByte_index];
    	}
    	
    	for(int i=0; i<length; i++) {
    		addr_Byte[addrByte_index + i] = checksum[i];
    	}    	
    	return Base58.encode(addr_Byte);    	
    }
    
    /**
     * @brief A private key lookup at the address (input address) of a wallet
     * @param address hdac address of wallet
     * @return ECKey private key
     */
    public ECKey getHdacSigKey(String address) {
    	
    	if(hdDKeyChain==null) return null;
    	
    	int i=0;
    	
    	for(i=0;i<MAX_INTERNAL_KEY;i++) {
    		String cmpAddr = getHdacAddress(true, i);
    		//System.out.print("\n" + address + "\n" + cmpAddr);
    		if(cmpAddr.equals(address)) {    			
    			byte[] priv = hdDKeyChain.getHdacInternalKey().derive(i, false).getPrivateKey();    			
    			DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(hdNetParams, encodeBase58WIF(priv));
    	    	return dumpedPrivateKey.getKey(); 
    		}
    	}
    	
    	for(i=0;i<MAX_EXTERNAL_KEY;i++) {
    		String cmpAddr = getHdacAddress(false, i);
    		if(cmpAddr.equals(address)) {    			
    			byte[] priv = hdDKeyChain.getHdacExternalKey().derive(i, false).getPrivateKey();    			
    			DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(hdNetParams, encodeBase58WIF(priv));
    	    	return dumpedPrivateKey.getKey(); 
    		}
    	}
    	
    	return null;
    }
    
    /**
     * @brief address list of wallet
     * @param isInternal true(internal)/false(external)
     * @return address list
     */
    public List<String> getHdacWalletAddresses(boolean isInternal) {
    	int max_count = isInternal?MAX_INTERNAL_KEY:MAX_EXTERNAL_KEY;
    	List<String> addrs = new ArrayList<String>();
    	for(int i=0;i<max_count;i++) {
    		addrs.add(getHdacAddress(isInternal, i));    		
    	}
    	
    	return addrs;
    }
    
    /**
     * @brief all addresses of wallet
     * @return address list
     */
    public List<String> getHdacWalletAddresses() {
    	int i=0;
    	List<String> addrs = new ArrayList<String>();
    	for(i=0;i<MAX_INTERNAL_KEY;i++) {
    		addrs.add(getHdacAddress(true, i));    		
    	}
    	
    	for(i=0;i<MAX_EXTERNAL_KEY;i++) {
    		addrs.add(getHdacAddress(false, i));    		
    	}
    	
    	return addrs;
    }    
    
    /**
     * @brief wallet getting hdac address of wallet
     * @param isInternal true(internal)/false(external)
     * @param index derive index
     * @return address
     */
    public String getHdacAddress(boolean isInternal, int index) {
    	if(hdDKeyChain==null) return null;
    	HdacDeterministicKey hdKey = isInternal?hdDKeyChain.getHdacInternalKey():hdDKeyChain.getHdacExternalKey();
		byte[] pubkey = hdKey.derive(index, false).getPublicKey();
    	return convPubkeyToHdacAddress(pubkey);
    }
    
    /**
     * @brief seed getting entropy
     * @return byte[] entropy
     */
    public byte[] getEntropyBytes() {    	
    	return entropy;		
    }
    
    private byte[] toEntropy(List<String> words) {
    	byte[] ep = null;
    	try {
            ep = MnemonicCode.INSTANCE.toEntropy(words);
        } catch (MnemonicException.MnemonicLengthException e) {
            // cannot happen
            throw new RuntimeException(e);
        } catch (MnemonicWordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MnemonicChecksumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ep;		
    }
    
    private List<String> toMnemonic(byte[] ep) {
    	List<String> words = null;
    	try {
    		words = MnemonicCode.INSTANCE.toMnemonic(ep);
        } catch (MnemonicException.MnemonicLengthException e) {
            // cannot happen
            throw new RuntimeException(e);
        }
    	return words;		
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
    
    
    public static ByteString bytesToHex(byte[] bytes) {
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


