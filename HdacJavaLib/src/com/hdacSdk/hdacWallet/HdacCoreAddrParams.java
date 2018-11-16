package com.hdacSdk.hdacWallet;

import java.math.BigInteger;

/*
 * Referred values of params.dat in a core 
 * address-pubkeyhash-version         # Version bytes used for pay-to-pubkeyhash addresses.
 * address-scripthash-version         # Version bytes used for pay-to-scripthash addresses.
 * address-checksum-value             # Bytes used for XOR in address checksum calculation.
*/	

/**
 * @brief Settings for Hdac address generation
 * @details Setting values ​​for Hdac core params-based Hdac address creation
 * @class HdacCoreAddrParams
 * @date 2018-05-23.
 * @author Hdac Technology 
 */
public class HdacCoreAddrParams {	
	
	public String addressHeader; // pubkeyhash
	public String p2shHeader;    // scripthash  
	public String addressChecksumValue;
	
	/**
	 * @code Custom Net : Be able to use specific data by user 
	 * Input parameters( addressHeader, p2shHeader, addressChecksum) of custom net 
	 */
	
	public HdacCoreAddrParams(String addressHeader, String p2shHeader, String addressChecksumValue) {
		this.addressHeader = addressHeader;
		this.p2shHeader = p2shHeader;
		this.addressChecksumValue = addressChecksumValue;
	}
    
	public HdacCoreAddrParams(boolean publicNet){
		/**
		 * @code publicNet : false -> private block chain, true -> public block chain
		 * If private, the first character of the generated address is "h"
		 * If public, the first character of the generated address is "H"
		 */
		if(!publicNet) {
    		this.addressHeader = "64";
    		this.p2shHeader = "08";
    		this.addressChecksumValue = "48545354";
    	}else {
    		this.addressHeader = "28";
    		this.p2shHeader = "08";
    		this.addressChecksumValue = "48444143";
    	}
		/**
		 * @endcode
		 */
    }
    
    public String getAddressHeader() {
		return addressHeader;
	}

	public void setAddressHeader(String verPubkeyhash) {
		this.addressHeader = verPubkeyhash;
	}	

	public String getP2shHeader() {
		return p2shHeader;
	}

	public void setP2shHeader(String verScripthash) {
		this.p2shHeader = verScripthash;
	}

	public String getAddressChecksumValue() {
		return addressChecksumValue;
	}

	public void setAddressChecksumValue(String checksum) {
		this.addressChecksumValue = checksum;
	}
    

}
