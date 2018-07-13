package com.hdacSdk.hdacCoreApi;

import java.util.List;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.UTXO;
import org.json.JSONArray;
import org.json.JSONException;

import com.hdacSdk.hdacWallet.HdacTransaction;
import com.hdacSdk.hdacWallet.HdacWallet;

/**
 * @brief Class to generate raw transaction data
 * @details API support for creating Raw Transaction \n
 * Support for api to generate raw transaction
 * @class TransactionUtils
 * @date 2018-03-19.
 * @author Hdac Technology 
 */
public class TransactionUtils {
	
	public static final int FEE_MAX_TYPE = 0;
	public static final int FEE_MEDIUM_TYPE = 1;
	public static final int FEE_LOW_TYPE = 2;	
	
	private static final double max_fee = 0.1;
	private static final double medium_fee = 0.03;
	private static final double low_fee = 0.01;
	
	/**
	 * @warning We do not recommend using CommandUtils and duplicated api
	 * @param mid
	 * @return
	 */
	public static String getMethodName(int mid) {
		String mtd = "";
		try {
			mtd = CommandUtils.getMethodName(mid);
		} catch (HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mtd;
	}
	
	/**
	 * @warning We do not recommend using CommandUtils and duplicated api
	 * @param mid
	 * @return
	 */
	public static String[] getParamFields(int mid) {
		String[] p_arr = null;
		try {
			p_arr = CommandUtils.getParamArray(mid);
		} catch (HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p_arr;
	}
	
	/**
	 * @warning We do not recommend using CommandUtils and duplicated api
	 * @param mid
	 * @return
	 */
	public static int getParamsLength(int mid) {
		String[] p_arr = null;
		try {
			p_arr = CommandUtils.getParamArray(mid);
		} catch (HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(p_arr==null) return 0;
		return p_arr.length;
	}
	
	/**
	 * @warning We do not recommend using CommandUtils and duplicated api
	 * @param mid
	 * @param params
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray strParamsToJSONParams(int mid, String[] params) throws JSONException {
		CommandParams rpc_p = new CommandParams(mid);
		JSONArray rst = new JSONArray();
		
		try {
			rst = rpc_p.makeStrParamValues(params);
		} catch (HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rst;
	}
	
	/**
	 * @brief Generate raw data (parameter) for sendrawtransaction
	 * @details Create raw tx data based on UTXO of wallet (address) 
	 * @param wallet
	 * @param txData
	 * @return String Hex string of raw data
	 * @throws CommandException
	 */
	public static String getRawTransaction(HdacWallet wallet, RawTxData txData) throws CommandException {
		String txHex = null;
		
		HdacTransaction transaction = new HdacTransaction(wallet);
		List<UTXO> utxos;
		long balance = 0;
		utxos = txData.getUtxos();
		for(int i=0; i<utxos.size();i++) {
			UTXO utxo;
			utxo = utxos.get(i);
			balance += utxo.getValue().value;										
		}
		
		long send_amount = (long) (txData.getAmount() * Math.pow(10, 8));								
		long remain = (long) (balance - send_amount - getMaxFee(txData.getFeeType()));

		if(remain >= 0) {
			transaction.addOutput(txData.getToAddress(), send_amount);
			transaction.addOutput(wallet.getHdacAddress(), remain);
			for(int i=0; i<utxos.size();i++) {
				UTXO utxo = utxos.get(i);
				ECKey sign = wallet.getHdacSigKey(utxo.getAddress());
				if(sign!=null) transaction.addSignedInput(utxo, sign);
			}
			txHex = transaction.getTxBuilder().build().toHex();			
		}
		return txHex;
	}
	
	/**
	 * @brief Balance calculation of utxo-based wallet
	 * @details Balance calculation based on UTXO of wallet (address)
	 * @param utxos
	 * @return long balance(not satoshi)
	 */
	public long getBalance(List<UTXO> utxos) {
		long balance = 0;
		for(int i=0; i<utxos.size();i++) {
			UTXO utxo;
			utxo = utxos.get(i);
			balance += utxo.getValue().value;										
		}
		balance/=Math.pow(10, 8);
		return balance;
	}
	
	
	private static double getMaxFee(int type) {
		
		switch(type) {
			case FEE_MAX_TYPE:
				return max_fee;
			case FEE_MEDIUM_TYPE:
				return medium_fee;
			case FEE_LOW_TYPE:
				return low_fee;
		}
		
	    return low_fee;
	}
	
	
	public static String toHexString(String data){
        String hexString = "";
        if(data!=null) {
            byte[] bytes = data.getBytes();
            for (byte aByte : bytes) {
                hexString += Integer.toString((aByte & 0xf0) >> 4, 16);
                hexString += Integer.toString(aByte & 0x0f, 16);
            }
            return hexString;
        }
        return null;
    }

	
}
