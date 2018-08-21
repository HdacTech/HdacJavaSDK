package com.hdacSdk.hdacWallet;

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
    
	private HdacTransaction(NetworkParameters params) {
		this.mParams = params;
	}
	
	public HdacTransaction(HdacWallet wallet) {		
		this(wallet.getNetworkParams());
		mTxBuilder = new TransactionBuilder();
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
				script = new Script(HdacWallet.hexToByte(unspent.getString("scriptPubKey")));
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
				script = new Script(HdacWallet.hexToByte(unspent.getString("scriptPubKey")));
				Sha256Hash hash = Sha256Hash.wrap(unspent.getString("txid"));
	    		long index = unspent.getLong("vout");
	    		if(script!=null && hash!=null && index>=0) {    	
		    		TransactionOutPoint outPoint = new TransactionOutPoint(mParams, index, hash);
		    		mTxBuilder.getTransaction().addSignedInput(outPoint, script, sign, Transaction.SigHash.ALL, true);
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
    		mTxBuilder.getTransaction().addSignedInput(outPoint, utxo.getScript(), sign, Transaction.SigHash.ALL, true);
    	}
    	
    }
	
    /**
     * @brief op return output Create and add to transaction
     * @param data op return data
     */
     public void addOpReturnOutput(String data) {
	if(data!=null&&!data.isEmpty()) {
		long data_satoshi = data.getBytes().length * 1000;
		Coin fee = Coin.valueOf(data_satoshi);
		mTxBuilder.getTransaction().addOutput(fee, new ScriptBuilder().op(ScriptOpCodes.OP_RETURN).data(data.getBytes()).build());
	}
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
		
		public TransactionBuilder() {
	    	mTransaction = new Transaction(mParams);
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
//			final int size = mTransaction.unsafeBitcoinSerialize().length;
//            if (size > MAX_TX_SIZE)
//                throw new HdacWalletException("Exceed Transaction Size", 1);
			return bytesToHex(mTransaction.unsafeBitcoinSerialize());
		}
		
	}

}
