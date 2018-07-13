package com.hdacSdk.hdacCoreApi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @brief HdacApiManager blockchain API transmission and RPC clients creation management
 * @details Ability to create and manage RPC clients \n
 * Support for calling blockchain RPC APIs with sendRpcCommand function \n
 * Supports creation of HdacCommand object supporting java function defined by API of blockchain
 * @class HdacApiManager
 * @date 2018-01-31.
 * @author Hdac Technology 
 */
public class HdacApiManager {
	
	public static final int MAX_COUNT_MCAPIRPCClIENT = 10;
	public static String VERSION = "v0.0.1";
	
	private List<HdacRpcClient> mRPCClientList = null;
	
    public HdacApiManager() {
    	init();
	}
    
    private void init() {
    	mRPCClientList = new ArrayList<HdacRpcClient>();
    }    
    
    /**
     * @brief Creating RPC client and adding it to the client list
     * @param ip RPC server ip
     * @param port RPC port
     * @param user RPC user
     * @param password RPC password
     * @param chainName chain name ( ex> hdac )
     * @param handler RpcHandler
     * @return HdacRpcClient
     * @throws HdacException
     */
    public HdacRpcClient newRPCClient(String ip, String port, String user, String password, String chainName, RpcHandler handler) throws HdacException {
		
		if(mRPCClientList.size() >= MAX_COUNT_MCAPIRPCClIENT) throw new HdacException(3);
		
		HdacRpcClient mac = new HdacRpcClient(ip, port, user, password, chainName, handler);
		
		mRPCClientList.add(mac);
		
		return mac;
	}
	
    /**
     * @brief Creating RPC Client
     * @param ip RPC server ip
     * @param port RPC port
     * @param user RPC user
     * @param password RPC password
     * @param chainName chain name ( ex> hdac )
     * @param handler RpcHandler 
     * @return HdacRpcClient
     * @throws HdacException
     */
    public static HdacRpcClient createRPCClient(String ip, String port, String user, String password, String chainName, RpcHandler handler) throws HdacException {
		HdacRpcClient mac = new HdacRpcClient(ip, port, user, password, chainName, handler);
		return mac;
	}
    
    /**
     * @brief Reference via id of HdacRpcClient
     * @param id
     * @return HdacRpcClient
     */
	public HdacRpcClient getRPCClientById(int id) {
		if(id < 0 || id >= mRPCClientList.size()) return null;
		return mRPCClientList.get(id);
	}
	
	/**
     * @brief Query id of HdacRpcClient
     * @param mac HdacRpcClient object
     * @return HdacRpcClient의 id
     */
	public int getRPCClientId(HdacRpcClient mac) {
		return mRPCClientList.indexOf(mac);
	}
	
	/**
	 * @brief Query status of server set in RPC Client
	 * @param RPC client
	 * @return boolean Server connection status 
	 */
	public boolean getHdacCoreState(HdacRpcClient client) {	
		HttpURLConnection urlConnect;
		try {
			urlConnect = (HttpURLConnection) new URL(client.getRPCUrl()).openConnection();
			if(urlConnect!=null){
	            return true;
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
        return false;
	}
	
	/**
	 * @brief Creating HdacCommand object that supports blockchain APIs in java interface form
	 * @param hrc HdacRpcClient
	 * @return HdacCommand
	 * @throws CommandException
	 */
	public HdacCommand getHdacCommand(HdacRpcClient hrc) throws CommandException {
		if(hrc==null) return null;
		
		return new HdacCommand(hrc);
	}
	
	public HdacCommand getHdacCommand(int hrc_id) throws CommandException {
		HdacRpcClient hrc = getRPCClientById(hrc_id);
		
		if(hrc==null) return null;
		
		return new HdacCommand(hrc);
	}
	
	/**
	 * @brief Blockchain APIs support via a single interface  
	 * @detail Calling all blockchain APIs per each case on a single interface by entering the corresponding parameters array along with the API Id
	 * @param hrc HdacRpcClient
	 * @param methodId API(method) Id
	 * @param paramArray An array of parameters for the API
	 * @throws CommandException An exception occurs when the API and parameters do not match each other
	 */
	public void sendRpcCommand(HdacRpcClient hrc, int methodId, Object[] paramArray) throws CommandException {
    	
		HdacCommand hdacCmd = new HdacCommand(hrc);
    	
    	int numParams = CommandUtils.sizeOfParamsByMId(methodId);
    	Object[] params = new Object[numParams];
    	if(paramArray!=null&&paramArray.length>0) {
	    	for(int i=0; i<numParams; i++) {
	    		if(i<paramArray.length) params[i] = paramArray[i];
	    		else params[i] = null;
	    	}
    	}
    	
    	switch(methodId){
	        case CommandUtils.GET_INFO :
	        	hdacCmd.getinfo();
	            break;
	        case CommandUtils.GET_NEW_ADDRESS :
	        	hdacCmd.getnewaddress();
	            break;
	        case CommandUtils.GET_ADDRESS_BALANCES :
	        	hdacCmd.getaddressbalances( (String) params[0], (String) params[1], (String) params[2]);
	            break;
	        case CommandUtils.GET_ADDRESS_TRANSACTION :
	        	hdacCmd.getaddresstransaction( (String) params[0], (String) params[1], (String) params[2]);
	            break;
	        case CommandUtils.LIST_ADDRESS_TRANSACTIONS :
	        	hdacCmd.listaddresstransactions( (String) params[0], (String) params[1], (String) params[2], (String) params[3]);
	            break;
	        case CommandUtils.LIST_WALLET_TRANSACTIONS :
	        	hdacCmd.listwallettransactions( (String) params[0], (String) params[1], (String) params[2], (String) params[3]);
	            break;
	        case CommandUtils.SEND_FROM :
	        	hdacCmd.sendfrom( (String) params[0], (String) params[1], (String) params[2],(String) params[3], (String) params[4]);
	            break;
	        case CommandUtils.SEND_WITH_DATA :
	        	hdacCmd.sendwithdata( (String) params[0], (String) params[1], (String) params[2]);
	            break;
	        case CommandUtils.SEND_WITH_DATA_FROM :
	        	hdacCmd.sendwithdatafrom( (String) params[0], (String) params[1], (String) params[2],(String) params[3]);
	            break;
	        case CommandUtils.LIST_STREAM_PUBLISHERS:
	        	hdacCmd.liststreampublishers( (String) params[0], (String) params[1], (String) params[2],(String) params[3], (String) params[4], (String) params[5]);
	            break;
	        case CommandUtils.LIST_PERMISSIONS:
	        	hdacCmd.listpermissions( (String) params[0], (String) params[1], (String) params[2]);
	            break;
	        case CommandUtils.SEND_RAW_TRANSACTION:
	        	hdacCmd.sendrawtransaction( (String) params[0]);
	            break;
	        case CommandUtils.IMPORT_ADDRESS:
	        	hdacCmd.importaddress( (String) params[0], (String) params[1], (String) params[2]);
	            break;
	        case CommandUtils.LIST_ADDRESSES:
	        	hdacCmd.listaddresses( (String) params[0], (String) params[1], (String) params[2],(String) params[3]);
	            break;
	        case CommandUtils.GET_BLOCK:
	        	hdacCmd.getblock( (String) params[0], (String) params[1]);        
	            break;        
	        case CommandUtils.GRANT:
	        	hdacCmd.grant( (String) params[0], (String) params[1], (String) params[2], (String) params[3],(String) params[4], (String) params[5], (String) params[6]);
	            break;
	        case CommandUtils.REVOKE:
	        	hdacCmd.revoke( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4] );
	            break;
	        case CommandUtils.GET_TOTAL_BALANCES:
	        	hdacCmd.gettotalbalances( (String) params[0], (String) params[1], (String) params[2]);
	            break;
	        case CommandUtils.LIST_UNSPENT:
	        	hdacCmd.listunspent( (String) params[0], (String) params[1], (JSONArray) params[2]);
	            break;
	        case CommandUtils.GET_BLOCKCHAIN_PARAMS:  	
	        	hdacCmd.getblockchainparams ( (String) params[0], (String) params[1]);
	            break;
	        case CommandUtils.GET_RUNTIME_PARAMS:  	
	        	hdacCmd.getruntimeparams ();
	            break;
	        case CommandUtils.SET_RUNTIME_PARAM: 	
	        	hdacCmd.setruntimeparam ( (String) params[0], (String) params[1]);
	            break;
	        case CommandUtils.HELP: 	
	        	hdacCmd.help ();
	            break;
	        case CommandUtils.STOP:	
	        	hdacCmd.stop ();
	            break;
	        case CommandUtils.ADD_MULTISIG_ADDRESS:	
	        	hdacCmd.addmultisigaddress ( (String) params[0], (JSONArray) params[1]);
	            break;
	        case CommandUtils.GET_ADDRESSES:
	        	hdacCmd.getaddresses ( (String) params[0]);
	            break;
	        case CommandUtils.CREATE_KEYPAIRS:
	        	hdacCmd.createkeypairs ( (String) params[0]);
	            break;
	        case CommandUtils.CREATE_MULTISIG:
	        	hdacCmd.createmultisig ( (String) params[0],(JSONArray) params[1]);
	            break;
	        case CommandUtils.VALIDATE_ADDRESS:
	        	hdacCmd.validateaddress ( (String) params[0]);
	            break;
	        case CommandUtils.GRANT_FROM:
	        	hdacCmd.grantfrom ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5], (String) params[6], (String) params[7]);
	            break;
	        case CommandUtils.GRANT_WITH_DATA:
	        	hdacCmd.grantwithdata ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5]);
	            break;
	        case CommandUtils.GRANT_WITH_DATA_FROM:
	        	hdacCmd.grantwithdatafrom ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5], (String) params[6]);
	            break;
	        case CommandUtils.REVOKE_FROM:
	        	hdacCmd.revokefrom ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5]);
	            break;
	        case CommandUtils.ISSUE:
	        	hdacCmd.issue ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (JSONObject) params[5]) ;
	            break;
	        case CommandUtils.ISSUE_FROM:
	        	hdacCmd.issuefrom ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5], (JSONObject) params[6]);
	            break;
	        case CommandUtils.ISSUE_MORE:
	        	hdacCmd.issuemore ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (JSONObject) params[4]) ;
	            break;
	        case CommandUtils.ISSUE_MORE_FROM:
	        	hdacCmd.issuemorefrom ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (JSONObject) params[5]);
	            break;
	        case CommandUtils.LIST_ASSETS:
	        	hdacCmd.listassets ( (String) params[0], (String) params[1], (String) params[2], (String) params[3]);
	            break;
	//           case TransactionDef.GET_ASSET_BALANCES:
	//           	Getassetbalances ((String) params[0], (String) params[1], (String) params[2], (String) params[3]);
	        case CommandUtils.GET_MULTIBALANCES:
	        	hdacCmd.getmultibalances ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4]);
	            break;
	        case CommandUtils.GET_WALLET_TRANSACTION:
	        	hdacCmd.getwallettransaction ( (String) params[0], (String) params[1], (String) params[2]) ;
	            break;
	        case CommandUtils.SEND:
	        	hdacCmd.send ( (String) params[0], (String) params[1], (String) params[2], (String) params[3]);
	            break;
	        case CommandUtils.SEND_ASSET:
	        	hdacCmd.sendasset ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5]);
	            break;
	        case CommandUtils.SEND_ASSET_FROM:
	        	hdacCmd.sendassetfrom ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5], (String) params[6]);
	            break;
			case CommandUtils.APPEND_RAW_EXCHANGE:
				hdacCmd.appendrawexchange ( (String) params[0], (String) params[1], (String) params[2], (JSONObject) params[3]);
	            break;
			case CommandUtils.COMPLETE_RAW_EXCHANGE:
				hdacCmd.completerawexchange ( (String) params[0], (String) params[1], (String) params[2], (JSONObject) params[3], (String) params[4]);
	            break;
			case CommandUtils.CREATE_RAW_EXCHANGE:
				hdacCmd.createrawexchange ( (String) params[0], (String) params[1], (JSONObject) params[2]);
	            break;
			case CommandUtils.DECODE_RAW_EXCHANGE:
				hdacCmd.decoderawexchange ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.DISABLE_RAW_TRANSACTION:
				hdacCmd.disablerawtransaction ( (String) params[0]);
	            break;
			case CommandUtils.PREPARE_LOCK_UNSPENT:
				hdacCmd.preparelockunspent ( (JSONObject) params[0], (String) params[1]);
	            break;
			case CommandUtils.PREPARE_LOCK_UNSPENT_FROM:
				hdacCmd.preparelockunspentfrom ( (String) params[0], (JSONObject) params[1], (String) params[2]);
	            break;
			case CommandUtils.CREATE_T_STREAM:
				hdacCmd.create ( (String) params[0], (String) params[1], (String) params[2], (JSONObject) params[3]);
	            break;
			case CommandUtils.CREATE_FROM_T_STREAM:
				hdacCmd.createfrom ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (JSONObject) params[4]) ;
	            break;
			case CommandUtils.LIST_STREAMS:
				hdacCmd.liststreams ( (String) params[0], (String) params[1], (String) params[2], (String) params[3]);
	            break;
			case CommandUtils.PUBLISH:
				hdacCmd.publish ( (String) params[0], (String) params[1], (String) params[2]);
	            break;
			case CommandUtils.PUBLISH_FROM:
				hdacCmd.publishfrom ( (String) params[0], (String) params[1], (String) params[2], (String) params[3]);
	            break;
			case CommandUtils.SUBSCRIBE:
				hdacCmd.subscribe ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.UNSUBSCRIBE:
				hdacCmd.unsubscribe ( (String) params[0]);
	            break;
			case CommandUtils.GET_ASSET_TRANSACTION:
				hdacCmd.getassettransaction ( (String) params[0], (String) params[1], (String) params[2]);
	            break;
			case CommandUtils.LIST_ASSET_TRANSACTIONS:
				hdacCmd.listassettransactions ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4]);
	            break;
			case CommandUtils.GET_STREAMITEM:
				hdacCmd.getstreamitem ( (String) params[0], (String) params[1], (String) params[2]);
	            break;
			case CommandUtils.GET_TXOUT_DATA:
				hdacCmd.gettxoutdata ( (String) params[0], (String) params[1], (String) params[2], (String) params[3]);
	            break;
			case CommandUtils.LIST_STREAM_BLOCK_ITEMS:
				hdacCmd.liststreamblockitems ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4]);
	            break;
			case CommandUtils.LIST_STREAM_KEY_ITEMS:
				hdacCmd.liststreamkeyitems ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5]); 
	            break;
			case CommandUtils.LIST_STREAM_KEYS:
				hdacCmd.liststreamkeys ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5]);
	            break;
			case CommandUtils.LIST_STREAM_ITEMS:
				hdacCmd.liststreamitems ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4]);
	            break;
			case CommandUtils.LIST_STREAM_PUBLISHER_ITEMS:
				hdacCmd.liststreampublisheritems ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5]);
	            break;
			case CommandUtils.COMBINE_UNSPENT:
				hdacCmd.combineunspent ( (String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4], (String) params[5]);
	            break;
			case CommandUtils.LIST_LOCK_UNSPENT:
				hdacCmd.listlockunspent ();
	            break;
			case CommandUtils.LOCK_UNSPENT:
				hdacCmd.lockunspent ( (String) params[0], (JSONArray) params[1]);
	            break;
			case CommandUtils.APPEND_RAW_CHANGE:
				hdacCmd.appendrawchange ( (String) params[0], (String) params[1], (String) params[2]); 
	            break;
			case CommandUtils.APPEND_RAW_DATA:
				hdacCmd.appendrawdata ( (String) params[0], (String) params[1]); 
	            break;
			case CommandUtils.APPEND_RAW_TRANSACTION:
				hdacCmd.appendrawtransaction ( (String) params[0], (JSONArray) params[1], (JSONObject) params[2], (JSONArray) params[3], (String) params[4]);
	            break;
			case CommandUtils.CREATE_RAW_TRANSACTION:
				hdacCmd.createrawtransaction ( (JSONArray) params[0], (JSONObject) params[1], (JSONArray) params[2], (String) params[3]); 
	            break;
			case CommandUtils.CREATE_RAW_SEND_FROM:
				hdacCmd.createrawsendfrom ( (String) params[0], (JSONObject) params[1], (JSONArray) params[2], (String) params[3]);
			case CommandUtils.DECODE_RAW_TRANSACTION:
				hdacCmd.decoderawtransaction ( (String) params[0]);
			case CommandUtils.SIGN_RAW_TRANSACTION:
				hdacCmd.signrawtransaction ( (String) params[0], (JSONArray) params[1], (JSONArray) params[2], (String) params[3]);
	            break;
			case CommandUtils.ADD_NODE:
				hdacCmd.addnode ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.GET_ADDED_NODE_INFO:
				hdacCmd.getaddednodeinfo ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.GET_NETWORK_INFO:
				hdacCmd.getnetworkinfo ();
	            break;
			case CommandUtils.GET_PEER_INFO:
				hdacCmd.getpeerinfo (); 
	            break;
			case CommandUtils.PING:
				hdacCmd.ping ();
	            break;
			case CommandUtils.SIGNMESSAGE:
				hdacCmd.signmessage ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.VERIFY_MESSAGE:
				hdacCmd.verifymessage ( (String) params[0], (String) params[1], (String) params[2]);
	            break;
			case CommandUtils.GET_BLOCKCHAIN_INFO:
				hdacCmd.getblockchaininfo ();
	            break;
			case CommandUtils.GET_BLOCKHASH:
				hdacCmd.getblockhash( (String) params[0]);
	            break;
			case CommandUtils.GET_MEMPOOL_INFO:
				hdacCmd.getmempoolinfo ();
	            break;
			case CommandUtils.GET_RAW_MEMPOOL:
				hdacCmd.getrawmempool (); 
	            break;
			case CommandUtils.GET_RAW_TRANSACTION:
				hdacCmd.getrawtransaction ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.GET_TXOUT:
				hdacCmd.gettxout ( (String) params[0], (String) params[1], (String) params[2]); 
	            break;
			case CommandUtils.LIST_BLOCKS:
				hdacCmd.listblocks ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.BACKUP_WALLET:
				hdacCmd.backupwallet ( (String) params[0]); 
	            break;
			case CommandUtils.DUMP_PRIVKEY:
				hdacCmd.dumpprivkey ( (String) params[0]); 
	            break;
			case CommandUtils.DUMP_WALLET:
				hdacCmd.dumpwallet ( (String) params[0]); 
	            break;
			case CommandUtils.ENCRYPT_WALLET:
				hdacCmd.encryptwallet ( (String) params[0]);
	            break;
			case CommandUtils.GET_WALLET_INFO:
				hdacCmd.getwalletinfo ();
	            break;
			case CommandUtils.IMPORT_PRIVKEY:
				hdacCmd.importprivkey ( (String) params[0], (String) params[1], (String) params[2]);
	            break;
			case CommandUtils.IMPORT_WALLET:
				hdacCmd.importwallet ( (String) params[0]);
	            break;
			case CommandUtils.WALLET_LOCK:
				hdacCmd.walletlock ();
	            break;
			case CommandUtils.WALLET_PASSPHRASE:
				hdacCmd.walletpassphrase ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.WALLET_PASSPHRASE_CHANGE:
				hdacCmd.walletpassphrasechange ( (String) params[0], (String) params[1]);
	            break;
			case CommandUtils.APPROVE_FROM:
				hdacCmd.approvefrom ( (String) params[0], (String) params[1], (String) params[2]);
	            break;
			case CommandUtils.LIST_UPGRADES:
				hdacCmd.listupgrades ( (String) params[0]);
	            break;
			case CommandUtils.CLEAR_MEMPOOL:
				hdacCmd.clearmempool ();
	            break;
			case CommandUtils.PAUSE:
				hdacCmd.pause ( (String) params[0]);
	            break;
			case CommandUtils.RESUME:
				hdacCmd.resume ( (String) params[0]);
	            break;
			case CommandUtils.SET_LAST_BLOCK:
				hdacCmd.setlastblock ( (String) params[0]);
	            break;
	    }    	
    	
    }
	
}
