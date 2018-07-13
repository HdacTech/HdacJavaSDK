package com.hdacSdk.hdacWallet;

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
	
	public int addressHeader; // pubkeyhash
	public int p2shHeader;    // scripthash        
	public String addressChecksumValue;
    
	public HdacCoreAddrParams(int addressHeader, int p2shHeader, String addressChecksumValue) {
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
    		this.addressHeader = 0x64;    	
    		this.p2shHeader = 0x08;        
    		this.addressChecksumValue = "48545354";
    	}else {
    		this.addressHeader = 0x28;    	
    		this.p2shHeader = 0x08;        
    		this.addressChecksumValue = "48444143";
    	}
		/**
		 * @endcode
		 */
    }
    
    public int getAddressHeader() {
		return addressHeader;
	}

	public void setAddressHeader(int verPubkeyhash) {
		this.addressHeader = verPubkeyhash;
	}

	public int getP2shHeader() {
		return p2shHeader;
	}

	public void setP2shHeader(int verScripthash) {
		this.p2shHeader = verScripthash;
	}

	public String getAddressChecksumValue() {
		return addressChecksumValue;
	}

	public void setAddressChecksumValue(String checksum) {
		this.addressChecksumValue = checksum;
	}
    

}
