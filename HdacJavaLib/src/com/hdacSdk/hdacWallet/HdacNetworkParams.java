package com.hdacSdk.hdacWallet;

import org.bitcoinj.core.BitcoinSerializer;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.Utils;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.utils.MonetaryFormat;

/**
 * @brief Class for setting the address system of Hdac
 * @details Define the settings of the Hdac address scheme
 * @class HdacNetworkParams
 * @date 2018-05-23.
 * @author Hdac Technology 
 *
 */
public class HdacNetworkParams extends NetworkParameters {
	
	public String addressChecksumValue;	
	protected long time;
	
	public HdacNetworkParams(HdacCoreAddrParams coreParams) {
        super();
        
        addressHeader = coreParams.getAddressHeader();           // pubkeyhash
        p2shHeader = coreParams.getP2shHeader();               // scripthash        
        addressChecksumValue = coreParams.getAddressChecksumValue();
        
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
        //Hdac
        dumpedPrivateKeyHeader = 131; // wif
        
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 22009;                 //network? rpc?
        packetMagic = 0xf9beb4d9L;

        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 210000;
        spendableCoinbaseDepth = 100;
        time = System.currentTimeMillis() / 1000;
        
        //System.out.print("currentTimeMillis " + time);
//      genesisBlock.setDifficultyTarget(0x1d00ffffL);
//      genesisBlock.setTime(1231006505L);
//      genesisBlock.setNonce(2083236893);
        
//        String genesisHash = genesisBlock.getHashAsString();
//        checkState(genesisHash.equals("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f"),
//                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
//        checkpoints.put(91722, new Sha256Hash("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
//        checkpoints.put(91812, new Sha256Hash("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
//        checkpoints.put(91842, new Sha256Hash("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
//        checkpoints.put(91880, new Sha256Hash("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
//        checkpoints.put(200000, new Sha256Hash("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));
//
//        dnsSeeds = new String[] {
//                "seed.bitcoin.sipa.be",        // Pieter Wuille
//                "dnsseed.bluematt.me",         // Matt Corallo
//                "dnsseed.bitcoin.dashjr.org",  // Luke Dashjr
//                "seed.bitcoinstats.com",       // Chris Decker
//                "seed.bitnodes.io",            // Addy Yeow
//        };
    }

    //private static HdacNetworkParams for public net;
    public static synchronized HdacNetworkParams getDefault() {        
    	return new HdacNetworkParams(new HdacCoreAddrParams(true));
    }
    
    public String getAddressChecksumValue() {
    	return addressChecksumValue;
    }
    
    @Override
    public String getPaymentProtocolId() {
    	return PAYMENT_PROTOCOL_ID_MAINNET;
    }

	@Override
	public void checkDifficultyTransitions(StoredBlock arg0, Block arg1, BlockStore arg2)
			throws VerificationException, BlockStoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coin getMaxMoney() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coin getMinNonDustOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MonetaryFormat getMonetaryFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDumpedPrivateKeyHeader() {
		// TODO Auto-generated method stub
		return dumpedPrivateKeyHeader;
	}

	@Override
	public int getProtocolVersionNum(ProtocolVersion arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BitcoinSerializer getSerializer(boolean parseRetain) {
		// TODO Auto-generated method stub
		return new BitcoinSerializer(this, parseRetain);
	}

	@Override
	public String getUriScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasMaxMoney() {
		// TODO Auto-generated method stub
		return false;
	}
    

}
