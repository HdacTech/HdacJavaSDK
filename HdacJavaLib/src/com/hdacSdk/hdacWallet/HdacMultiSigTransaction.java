package com.hdacSdk.hdacWallet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptChunk;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hdacSdk.hdacCoreApi.CommandException;
import com.hdacSdk.hdacCoreApi.CommandUtils;
import com.hdacSdk.hdacCoreApi.HdacApiManager;
import com.hdacSdk.hdacCoreApi.HdacCommand;
import com.hdacSdk.hdacCoreApi.HdacException;
import com.hdacSdk.hdacCoreApi.HdacRpcClient;
import com.hdacSdk.hdacCoreApi.RpcHandler;
import com.sun.jna.Library.Handler;

/**
 * @brief API support for MultiSigniture Transaction data configuration
 * @details Raw Transaction hex data generation via Hdac RPC API
 * @class HdacMultiSigTransaction
 * @date 2018-09-20.
 * @author Hdac Technology 
 *
 */
public class HdacMultiSigTransaction implements RpcHandler{
	private static final int TIMEOUT = 5000; 
	private static final int GRANT_INTERVAL = 2000; 
	private String mRpcIp = null;
	private String mRpcPort = null;
	private String mRpcUser = null;
	private String mRpcPassword = null;
	private String mChainName = null;
	private HdacRpcClient mHdacRpcClient = null;
	private HdacCommand mHdacCommand = null;
	
	private JSONObject mResponse = null;	
	private boolean mDone = false;
	
	private String[] mAddrs = null;
	private String mMultiSigAddr = null;
	private String mRawTx = null;
	private String mAssetKey = null; 
	
	public HdacMultiSigTransaction(String ip, String port, String user, String pw, String chainName) {
		mRpcIp = ip;
		mRpcPort = port;
		mRpcUser = user;
		mRpcPassword = pw;
		mChainName = chainName;
		
		createHdacRpcClient(this);		
	}
	
	@Override
	public void onResponse(int method, JSONObject response) {
		// TODO Auto-generated method stub		
		if(response!=null&&response.isNull("error")) {
			mResponse = response;
		}
		else mResponse = null;
		
	}

	@Override
	public void onError(int method, String err_msg, int code) {
		// TODO Auto-generated method stub		
		if(err_msg!=null&&!err_msg.isEmpty()) {
			if(!err_msg.equals("Success")) System.out.print("RPC Response Error!!\n - "
				+ CommandUtils.Methods[method]
				+ "\n - " + err_msg + "\n");
		}
	}
	
	@Override
	public void done(int method) {
		// TODO Auto-generated method stub
		synchronized (this) {
			mDone = true; 
			this.notifyAll();
		}
	}
	
	/**
	 * @brief Creating address of multisig transaction
	 * @param nreq minimum count of public keys for signing
	 * @param String[] public keys
	 * @return String multisig address
	 */
	public String createMultiSigAddress(int nreq, String... addrs) {
		if(!mDone) {
			mAddrs = addrs;
			JSONArray params = new JSONArray();
			for(int i = 0; i < addrs.length; i++) params.put(addrs[i]);
			try {
				mResponse = null;
				mHdacCommand.addmultisigaddress(String.valueOf(nreq), params);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);						
						if(++i*200>TIMEOUT) break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			
			mDone = false;
			if(mResponse!=null) {
				mMultiSigAddr = mResponse.getString("result");
			}
					
		}else {
			System.out.print("RPC Resource is locked!!\n");
			return null;
		}		

		return mMultiSigAddr;
		
	}
	
	public void createIssue(String multiSigAddr, String assetName, double qty, double unit) {
		
		if(!mDone) {
			mAssetKey = assetName;
			try {
				mResponse = null;
				mHdacCommand.issue(multiSigAddr, assetName, String.valueOf(qty), String.valueOf(unit), null, null);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
		}	
		
		mDone = false;		
	}
	
	/**
	 * @brief grant send and receive permission for address
	 * @param multisig address
	 */
	public void grantPermission(String multiSigAddr) {
		
		if(!mDone) {
			try {
				mResponse = null;
				mHdacCommand.grant(multiSigAddr, "send,receive", null, null, null, null, null);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}
					Thread.sleep(GRANT_INTERVAL);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
		}		
		mDone = false;		
	}
	
	/**
	 * @brief hex data of multisig raw transaction
	 * @param fromAddr from address 
	 * @param be included address and transaction details
	 * @param data data
	 * @param action action
	 * @return String raw transaction
	 */
	public String createRawSendFrom(String fromAddr, JSONObject toAddrWithData, @Nullable JSONArray data, @Nullable String action ) {
		if(!mDone) {
			try {
				mResponse = null;
				mHdacCommand.createrawsendfrom(fromAddr, toAddrWithData, data, action);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}									
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
			return null;
		}
		
		mDone = false;
		if(mResponse!=null) {
			JSONObject rst = mResponse.getJSONObject("result");
			mRawTx = rst.getString("hex");
		}
		return mRawTx;
	}
	
	public boolean sendToMultiSigAddr(String msAddr, double amount ) {
		if(!mDone) {
			try {
				mResponse = null;
				mHdacCommand.send(msAddr, String.valueOf(amount), null, null);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}									
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
			return false;
		}
		
		mDone = false;
		String rst = null;
		if(mResponse!=null) {
			rst = mResponse.getString("result");			
		}
		return (rst!=null&&!rst.isEmpty())?true:false;
	}
	
	public JSONObject decodeRawTransaction(String rawTx) {
		if(!mDone) {
			try {
				mResponse = null;
				mHdacCommand.decoderawtransaction(rawTx);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}									
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
			return null;
		}
		
		mDone = false;
		JSONObject rst = null;
		if(mResponse!=null) {
			rst = mResponse.getJSONObject("result");
		}
		return rst;
	}
	
	
	/**
     * @brief block chain api listunspent
     * @param addresses
     * @return JSONArray utxo
     */
	public JSONArray getListUnspent(String addr) {
		if(!mDone) {
			try {
				mResponse = null;
				JSONArray addrs = new JSONArray();
				addrs.put(addr);
				mHdacCommand.listunspent("0", "99999999", addrs);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}									
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
			return null;
		}
		
		mDone = false;
		JSONArray rst = null;
		if(mResponse!=null) {
			rst = mResponse.getJSONArray("result");
		}
		return rst;
	}
	
	public void importPrivKey(String privKey) {
		if(!mDone) {
			try {
				mResponse = null;
				mHdacCommand.importprivkey(privKey, "", "false");
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}		
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}									
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
			return;
		}
		
		mDone = false;		
	}
	
	
	/**
     * @brief block chain api listunspent
     * @param rawTx
     * @param privKey
     * @param utxo
     * @return String signed raw transaction
     */
	public String signRawTransaction(String rawTx, String privKey, JSONObject utxo) {
		if(!mDone) {
			try {
				mResponse = null;
				JSONArray parentOutput = new JSONArray();
				JSONObject poValue = new JSONObject();
				poValue.put("txid", utxo.getString("txid"));
				poValue.put("vout", utxo.getInt("vout"));
				poValue.put("scriptPubKey", utxo.getString("scriptPubKey"));
				poValue.put("redeemScript", utxo.getString("redeemScript"));
				parentOutput.put(poValue);
				JSONArray privateKey = new JSONArray();
				privateKey.put(privKey);
				mHdacCommand.signrawtransaction(rawTx, parentOutput, privateKey, null);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}									
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
			return null;
		}
		
		mDone = false;
		if(mResponse!=null) {
			if(mResponse.toString().contains("hex")) {
				JSONObject rst = mResponse.getJSONObject("result");
				mRawTx = rst.getString("hex");
			}else {
				mRawTx = mResponse.getString("result");
			}
		}
		return mRawTx;
	}
	
	public String sendRawTrasnsaction(String rawTx) {
		if(!mDone) {
			try {
				mResponse = null;
				mHdacCommand.sendrawtransaction(rawTx);
			} catch (CommandException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			synchronized (this) {
				try {
					int i = 0;
					while(!mDone) {					
						this.wait(200);
						if(++i*200>TIMEOUT) break;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}									
			
		}else {
			System.out.print("RPC Resource is locked!!\n");
			return null;
		}
		
		mDone = false;
		String rst = null;
		if(mResponse!=null) {
			rst = mResponse.getString("result");			
		}
		return rst;
	}
	
	public String getSignAddress(int index) {
		if(mAddrs.length >= index) return null;
		return mAddrs[index];
	}
	
	public String getMultiSigAddress() {
		return mMultiSigAddr;
	}

	public String getRawTx() {
		return mRawTx;
	}
	
	public String getAssetKey() {
		return mAssetKey;
	}
	
	private HdacCommand createHdacRpcClient(RpcHandler handler) {
		try {
			mHdacRpcClient = HdacApiManager.createRPCClient(mRpcIp, mRpcPort, mRpcUser, mRpcPassword, mChainName, handler);
			mHdacCommand = new HdacCommand(mHdacRpcClient);
		} catch (HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return mHdacCommand;
    }
	
	
}
