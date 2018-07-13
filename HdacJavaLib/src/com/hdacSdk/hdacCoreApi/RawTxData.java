package com.hdacSdk.hdacCoreApi;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.script.Script;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @brief Configuring Raw Transaction data
 * @details Generate data for Raw Transaction through listUnspent
 * @class RawTxData
 * @date 2018-03-19.
 * @author Hdac Technology 
 *
 */
public class RawTxData {
	
	private long amount;
	private String toAddress;
	private int feeType;
	private List<UTXO> utxos = new ArrayList<UTXO>();
	   
	private RawTxData(JSONArray listUnspent) throws CommandException {
		if(listUnspent==null ||listUnspent.length()<=0) throw new CommandException("listUnspent", 0);
	
		for(int i = 0; i<listUnspent.length(); i++) {
			try {
				utxos.add(toUTXO((JSONObject) listUnspent.get(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
	public RawTxData(JSONArray listUnspent, String toAddr, long amount, int feeType) throws CommandException{
		this(listUnspent);
		this.amount = amount;
		this.feeType = feeType;
		this.toAddress = toAddr;
		
	}
	
	@SuppressWarnings("deprecation")
	private UTXO toUTXO(JSONObject unspent) {
		
		byte[] txid;
		long index;
        Coin value;
        int height = 0; // not need
        boolean coinbase = false; // ???
        Script script;
        String address; 
        
		try {
			txid = new BigInteger((String) unspent.get("txid") ,32).toByteArray();
			index = (long) unspent.get("vout");
			value = Coin.valueOf((long)((long) unspent.get("amount") * Math.pow(10, 8)));
			script = new Script(new BigInteger((String) unspent.get("scriptPubKey") ,16).toByteArray());
			address = (String) unspent.get("address");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Sha256Hash hash = new Sha256Hash(txid);
        
        return new UTXO(hash, index, value, height, coinbase, script, address);	    
	}
	
	public String getToAddress() {
		return toAddress;
	}

	
	public long getAmount() {
		return amount;
	}

	public int getFeeType() {
		return feeType;
	}

	public List<UTXO> getUtxos() {
		return utxos;
	}

}
