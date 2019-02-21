package com.hdacSdk.hdacWallet;

public class HdacCoreAddrParams
{
  public int addressHeader;
  public int p2shHeader;
  public String addressChecksumValue;
  
  public HdacCoreAddrParams(int addressHeader, int p2shHeader, String addressChecksumValue)
  {
    this.addressHeader = addressHeader;
    this.p2shHeader = p2shHeader;
    this.addressChecksumValue = addressChecksumValue;
  }
  
  public HdacCoreAddrParams(boolean publicNet)
  {
    if (!publicNet)
    {
      this.addressHeader = 100;
      this.p2shHeader = 8;
      this.addressChecksumValue = "48545354";
    }
    else
    {
      this.addressHeader = 40;
      this.p2shHeader = 8;
      this.addressChecksumValue = "48444143";
    }
  }
  
  public int getAddressHeader()
  {
    return this.addressHeader;
  }
  
  public void setAddressHeader(int verPubkeyhash)
  {
    this.addressHeader = verPubkeyhash;
  }
  
  public int getP2shHeader()
  {
    return this.p2shHeader;
  }
  
  public void setP2shHeader(int verScripthash)
  {
    this.p2shHeader = verScripthash;
  }
  
  public String getAddressChecksumValue()
  {
    return this.addressChecksumValue;
  }
  
  public void setAddressChecksumValue(String checksum)
  {
    this.addressChecksumValue = checksum;
  }
}
