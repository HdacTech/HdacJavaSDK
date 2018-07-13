package com.hdacSdk.hdacWallet;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.wallet.DeterministicKeyChain;

import com.google.common.collect.ImmutableList;

/**
 * @brief Hierarchical deterministic key generation
 * @details Generate deterministic public/private key
 * @class HdacDeterministicKey
 * @date 2018-05-23.
 * @author Hdac Technology 
 */
public class HdacDeterministicKey{
	
	private DeterministicKeyChain hdKeyChain;
	private ImmutableList<ChildNumber> parentPath;
	
	private boolean create = false;
	
	public HdacDeterministicKey(DeterministicKeyChain hdKeyChain, ImmutableList<ChildNumber> parentPath, boolean create) {	
		this.hdKeyChain = hdKeyChain;
		this.parentPath = parentPath;
		this.create = create;
	}
	
	/**
	 * @brief Generate child key through chain code
	 * @param index derive index
	 * @param hardened derive key : private->true, public->false 
	 * @return HdacDeterministicKey child key
	 */
	public HdacDeterministicKey derive(int index, boolean hardened) {
		ImmutableList.Builder<ChildNumber> builder = ImmutableList.builder();
		builder.addAll(parentPath)
               .add(new ChildNumber(index, hardened));
		
		ImmutableList<ChildNumber> path = builder.build();
		return new HdacDeterministicKey(hdKeyChain, path, create);
	}
	
	public void setParentPath(ImmutableList<ChildNumber> parentPath) {
		this.parentPath = parentPath;
	}
	
	/**
	 * @brief Change instance parent to child key
	 * @param index derive index
	 * @param hardened derive key : private->true, public->false 
	 */
	public void addChildPath(int index, boolean hardened) {
		ImmutableList.Builder<ChildNumber> builder = ImmutableList.builder();
		builder.addAll(parentPath)
               .add(new ChildNumber(index, hardened));
		
		parentPath = builder.build();
	}	
	
	public byte[] getPublicKey() {
		return hdKeyChain.getKeyByPath(parentPath, true).getPubKey();
	}
	
	public byte[] getPublicKeyHash() {
		return hdKeyChain.getKeyByPath(parentPath, true).getPubKeyHash();
	}
	
	public String getPublicKeyAsHex() {
		return hdKeyChain.getKeyByPath(parentPath, true).getPublicKeyAsHex();
	}
	
	public byte[] getPrivateKey() {
		return hdKeyChain.getKeyByPath(parentPath, true).getPrivKeyBytes();
	}
	
	public String getPrivateKeyAsHex() {
		return hdKeyChain.getKeyByPath(parentPath, true).getPrivateKeyAsHex();
	}
	
	public String getStringPath() {
		return parentPath.toString();
	}
	
	public ImmutableList<ChildNumber> getPath() {
		return parentPath;
	}

}
