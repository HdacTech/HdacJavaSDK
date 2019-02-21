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

public class HdacNetworkParams
  extends NetworkParameters
{
  public String addressChecksumValue;
  protected long time;
  
  public HdacNetworkParams(HdacCoreAddrParams coreParams)
  {
    this.addressHeader = coreParams.getAddressHeader();
    this.p2shHeader = coreParams.getP2shHeader();
    this.addressChecksumValue = coreParams.getAddressChecksumValue();
    
    this.interval = 2016;
    this.targetTimespan = 1209600;
    this.maxTarget = Utils.decodeCompactBits(486604799L);
    
    this.dumpedPrivateKeyHeader = 131;
    
    this.acceptableAddressCodes = new int[] { this.addressHeader, this.p2shHeader };
    this.port = 22009;
    this.packetMagic = 4190024921L;
    
    this.id = "org.bitcoin.production";
    this.subsidyDecreaseBlockCount = 210000;
    this.spendableCoinbaseDepth = 100;
    this.time = (System.currentTimeMillis() / 1000L);
  }
  
  public static synchronized HdacNetworkParams getDefault()
  {
    return new HdacNetworkParams(new HdacCoreAddrParams(true));
  }
  
  public String getAddressChecksumValue()
  {
    return this.addressChecksumValue;
  }
  
  public String getPaymentProtocolId()
  {
    return "main";
  }
  
  public void checkDifficultyTransitions(StoredBlock arg0, Block arg1, BlockStore arg2)
    throws VerificationException, BlockStoreException
  {}
  
  public Coin getMaxMoney()
  {
    return null;
  }
  
  public Coin getMinNonDustOutput()
  {
    return null;
  }
  
  public MonetaryFormat getMonetaryFormat()
  {
    return null;
  }
  
  public int getDumpedPrivateKeyHeader()
  {
    return this.dumpedPrivateKeyHeader;
  }
  
  public int getProtocolVersionNum(NetworkParameters.ProtocolVersion arg0)
  {
    return 0;
  }
  
  public BitcoinSerializer getSerializer(boolean parseRetain)
  {
    return new BitcoinSerializer(this, parseRetain);
  }
  
  public String getUriScheme()
  {
    return null;
  }
  
  public boolean hasMaxMoney()
  {
    return false;
  }
}
