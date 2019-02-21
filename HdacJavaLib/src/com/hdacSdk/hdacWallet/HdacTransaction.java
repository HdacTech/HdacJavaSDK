package com.hdacSdk.hdacWallet;

import java.io.UnsupportedEncodingException;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptOpCodes;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @brief API support for Raw Transaction data configuration
 * @details Raw Transaction hex data generation via TransactionBuilder
 * @class HdacTransaction
 * @date 2018-05-30.
 * @author Hdac Technology 
 *
 */

public class HdacTransaction{
	
	private NetworkParameters mParams;
    
    private TransactionBuilder mTxBuilder = null;
    
    public HdacTransaction(NetworkParameters params) {
		this(params, null);
	}
	
	public HdacTransaction(HdacWallet wallet) {		
		this(wallet.getNetworkParams(), null);
	}
	
	public HdacTransaction(NetworkParameters params, byte[] payload) {
		this.mParams = params;
		mTxBuilder = new TransactionBuilder(payload);
	}
	
	public HdacTransaction(HdacWallet wallet, byte[] payload) {		
		this(wallet.getNetworkParams());
		mTxBuilder = new TransactionBuilder(payload);
	}
	
	/**
	 * @brief Creating output of transaction
	 * @param address to address
	 * @param amount satoshis
	 */
	public void addOutput(String address, long amount) {
		if(address!=null&&!address.isEmpty()) {
			byte[] hash160 = new byte[20];
			byte[] dec = Base58.decode(address);
			if(dec!=null&&dec.length==25) {
				System.arraycopy(dec, 1, hash160, 0, 20);
				mTxBuilder.mTransaction.addOutput(Coin.valueOf(amount), new Address(mParams, hash160));
			}
		}
	}
	
	/**
	 * @brief Creating input of transaction
	 * @param unspent Utxo list of type JSONObject
	 */
	public void addInput(JSONObject unspent) {
		
    	if(unspent!=null) {
    		Script script;
			try {
				script = new Script(HdacWalletUtils.hexToByte(unspent.getString("scriptPubKey")));
//				script = new Script(HdacWallet.hexToByte(unspent.getString("scriptPubKey")));
				Sha256Hash hash = Sha256Hash.wrap(unspent.getString("txid"));
	    		long index = unspent.getLong("vout");
	    		long amount = (long) (unspent.getLong("amount") * Math.pow(10, 8));
	    		if(script!=null && hash!=null && index>=0) {    	
	    			TransactionOutPoint outPoint = new TransactionOutPoint(mParams, index, hash);
	        		TransactionInput input = new TransactionInput(mParams, null, script.getProgram(), outPoint, Coin.valueOf(amount));
	        		mTxBuilder.getTransaction().addInput(input);
	    		}
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    		
    		
    	}
	}
	
	/**
	 * @brief Creating input of transaction
	 * @param utxo utxo object
	 */
	public void addInput(UTXO utxo) {    	    	
    	if(utxo!=null) {    		
    		TransactionOutPoint outPoint = new TransactionOutPoint(mParams, utxo.getIndex(), utxo.getHash());
    		TransactionInput input = new TransactionInput(mParams, null, utxo.getScript().getProgram(), outPoint, utxo.getValue());
    		mTxBuilder.getTransaction().addInput(input);
    	}
    	
	}
	
	/**
	 * @brief Add input to transaction by signing input as private key
	 * @param unspent Utxo of type JSONObject
	 * @param sign private key
	 */
    public void addSignedInput(JSONObject unspent, ECKey sign) {
    	if(unspent!=null) {
    		Script script;
			try {
				script = new Script(HdacWalletUtils.hexToByte(unspent.getString("scriptPubKey")));
//				script = new Script(HdacWallet.hexToByte(unspent.getString("scriptPubKey")));
				Sha256Hash hash = Sha256Hash.wrap(unspent.getString("txid"));
	    		long index = unspent.getLong("vout");
	    		if(script!=null && hash!=null && index>=0) {    	
		    		TransactionOutPoint outPoint = new TransactionOutPoint(mParams, index, hash);
		    		mTxBuilder.getTransaction().addSignedInput(outPoint, script, sign, Transaction.SigHash.ALL, false);
	    		}
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
	}
	
    /**
     * @brief Add input to transaction by signing input as private key
     * @param utxo utxo object
     * @param sign private key
     */
    public void addSignedInput(UTXO utxo, ECKey sign) {    	    	
    	if(utxo!=null) {
    		TransactionOutPoint outPoint = new TransactionOutPoint(mParams, utxo.getIndex(), utxo.getHash());
    		mTxBuilder.getTransaction().addSignedInput(outPoint, utxo.getScript(), sign, Transaction.SigHash.ALL, false);
    	}
    	
	}
	
    /**
     * @brief Add input to transaction by signing input as private key
     * @param utxo utxo object
     * @param sign private key
     * @param fAsset boolean asset
     */
    public void setSignedInput(int index, JSONObject unspent, ECKey sign) {
    	setSignedInput(index, unspent, sign, false);
    }
    
    /**
     * @brief Add input to transaction by signing input as private key
     * @param utxo utxo object
     * @param sign private key
     * @param fAsset boolean asset
     */
    public void setSignedInput(int index, JSONObject unspent, ECKey sign, boolean fAsset ) {

    	if(unspent!=null) {
			Script scriptPubKey;
			try {
				scriptPubKey = new Script(HdacWalletUtils.hexToByte(unspent.getString("scriptPubKey")));
//				scriptPubKey = new Script(HdacWallet.hexToByte(unspent.getString("scriptPubKey")));
				System.out.println("scriptPubKey : " + scriptPubKey);
				Sha256Hash hash = mTxBuilder.mTransaction.hashForSignature(index, scriptPubKey, Transaction.SigHash.ALL, true);
				System.out.println("hash : " + hash);
			    ECKey.ECDSASignature ecSig = sign.sign(hash);
			    TransactionSignature txSig = new TransactionSignature(ecSig, Transaction.SigHash.ALL, true);
			    
				System.out.println("scriptPubKey.isSentToRawPubKey() : " + scriptPubKey.isSentToRawPubKey());
				
				if (fAsset) {
			        mTxBuilder.mTransaction.getInput(index).setScriptSig(ScriptBuilder.createInputScript(txSig, sign));
				} else if (scriptPubKey.isSentToRawPubKey())
			    {
			    	mTxBuilder.mTransaction.getInput(index).setScriptSig(ScriptBuilder.createInputScript(txSig));
			    }
			    else 
			    {
					System.out.println("scriptPubKey.isSentToAddress() : " + scriptPubKey.isSentToAddress());
			        if (!scriptPubKey.isSentToAddress())
			        {
						System.out.println("Don\\'t know how to sign for this kind of scriptPubKey : " + scriptPubKey);
			            throw new ScriptException("Don\'t know how to sign for this kind of scriptPubKey: " + scriptPubKey);
			        }
			        mTxBuilder.mTransaction.getInput(index).setScriptSig(ScriptBuilder.createInputScript(txSig, sign));
			    }		    
			}
			catch (ScriptException e)
			{
				System.out.println("ScriptException : " + e);				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (JSONException e)
			{
				System.out.println("JSONException : " + e);				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
  	}
    /**
     * @brief op return output Create and add to transaction
     * @param data op return string data
     * @param encode encoding format 
     */
	public void addOpReturnOutput(String data, String encode) {
		if(data!=null&&!data.isEmpty()) {
			try {
				Coin coin = Coin.valueOf(0);				
				mTxBuilder.getTransaction().addOutput(coin, new ScriptBuilder().op(ScriptOpCodes.OP_RETURN).data(data.getBytes(encode)).build());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
     * @brief op return output Create and add to transaction
     * @param data op return byte data
     */
	public void addOpReturnOutput(byte[] data) {
		if(data!=null&&data.length>0) {
			Coin coin = Coin.valueOf(0);				
			mTxBuilder.getTransaction().addOutput(coin, new ScriptBuilder().op(ScriptOpCodes.OP_RETURN).data(data).build());
		}
	}
	
//	0000000000000000 //vout 0 amount
//	37 //vout 0 total size
//	76a914933d87694e1c32bb2c0e257945c6416df3d83f9b88ac //vout ScriptPubKey
	
//	1c //asset data size
//	73706b71 //identifier 4byte spkq or 0x73 0x70 0x6b 0x71
//	c1b1dd6240f16c2138bd4a29ac561858 //asset issuedtxid first 16byte reverse 
//	00e8764817000000 //asset amount 8byte reverse
//	75 //OPDROP

    /**
     * @brief asset output Create and add to transaction
     * @param data asset data
     */
	public void addAssetOutput(String address, String txid, long assetAmount, long amount) {
		byte[] hash160 = new byte[20];
		
		if(address!=null&&!address.isEmpty()) {
			byte[] dec = Base58.decode(address);
			if(dec!=null&&dec.length==25) {
				System.arraycopy(dec, 1, hash160, 0, 20);
			}
		}

		String asset_transfer_uid = "73706b71";
		String issuedtxid = HdacWalletUtils.reverseHexString(txid.substring(0, 32));
		String bal = HdacWalletUtils.toLittleEndian(assetAmount);
		
		String assetData = asset_transfer_uid + issuedtxid + bal;
		
		Script script = new ScriptBuilder()
								.op(ScriptOpCodes.OP_DUP) //0x76
								.op(ScriptOpCodes.OP_HASH160) //0xa9
								.data(hash160) //0x14(20) length + data
								.op(ScriptOpCodes.OP_EQUALVERIFY) //0x88
								.op(ScriptOpCodes.OP_CHECKSIG) //0xac
								.data(HdacWalletUtils.toByteArray(assetData)) //0x1c(28) + assetdata
								.op(ScriptOpCodes.OP_DROP) //0x75
								.build();
		
		mTxBuilder.getTransaction().addOutput(Coin.valueOf(amount), script);
	}

	/**
	 * @brief hex data of raw transaction
	 * @return TransactionBuilder
	 */
	public TransactionBuilder getTxBuilder() {
		return mTxBuilder;
	}
	
	/**
	 * @brief transaction object
	 * @return Transaction object of bitcoinj's Transaction
	 */
	public Transaction getTransaction() {
		return mTxBuilder.getTransaction();
	}
	
	static final String HEXES = "0123456789ABCDEF";
	private static String bytesToHex( byte [] raw ) {
	    if ( raw == null ) {
	        return null;
	    }
	    final StringBuilder hex = new StringBuilder( 2 * raw.length );
	    for ( final byte b : raw ) {
	        hex.append(HEXES.charAt((b & 0xF0) >> 4))
	            .append(HEXES.charAt((b & 0x0F)));
	    }
	    return hex.toString();
	}
	
	/**
	 * @brief Transaction Builder for generating raw transaction data
	 * @class raw transaction builder class
	 * @date 2018-05-30.
	 * @author Hdac Technology 
	 */
	public class TransactionBuilder{		
		private Transaction mTransaction;
		private Transaction.Purpose mPurpose = Transaction.Purpose.USER_PAYMENT;
		
		public TransactionBuilder(byte[] payload) {
			if(payload!=null) mTransaction = new Transaction(mParams, payload);
			else mTransaction = new Transaction(mParams);
		}
		
		public Transaction getTransaction() {
			return mTransaction;
		}	
		
		public TransactionBuilder build() {
			mTransaction.getConfidence().setSource(TransactionConfidence.Source.SELF);
			mTransaction.setPurpose(mPurpose);
			return this;
		}
		
		/**
		 * @brief getting transaction hash
		 * @return String transaction hash
		 */
		public String getHashAsString() {
			return mTransaction.getHashAsString();
		}
		
		/**
		 * @brief hex data of raw transaction
		 * @return String transaction raw data
		 */
		public String toHex() {
			return bytesToHex(mTransaction.unsafeBitcoinSerialize());
		}
		
	}

}
