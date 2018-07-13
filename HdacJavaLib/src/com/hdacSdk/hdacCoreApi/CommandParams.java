package com.hdacSdk.hdacCoreApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @brief Generating parameters of type JSONArray corresponding to blockchain api 
 * @details A function that creates the format of parameters that have an input value of String array in api.
 * @class CommandParams
 * @date 2018-01-31.
 * @author Hdac Technology 
 *
 */

public class CommandParams {
	
	private int mMethod = CommandUtils.MAX_INDEX;
    
	public CommandParams(int mid) {
		mMethod = mid;
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

    /**
     * @brief makeStrParamValues
     * @detail be sure to convert to JSONArray params
     * @param argvs
     * @return JSONArray
     * @throws JSONException
     * @throws HdacException
     */    
    public JSONArray makeStrParamValues(String[] argvs) throws JSONException, HdacException {

        if(mMethod>CommandUtils.MAX_INDEX || mMethod<0) throw new HdacException(1);

        String[] params = null;
        if(argvs!=null&&argvs.length>0) {
        	int numParams = CommandUtils.sizeOfParamsByMId(mMethod);
            params = new String[numParams];
            for (int i = 0; i < numParams; i++) {
                if(i < argvs.length) params[i] = argvs[i];
                else params[i] = null;
            }
        }

        switch(mMethod){
            case CommandUtils.GET_INFO :
                return getParams_GetInfo();
            case CommandUtils.GET_NEW_ADDRESS :
                return getParams_GetNewAddress();
            case CommandUtils.GET_ADDRESS_BALANCES :
                return getParams_GetAddressBalances(params[0], params[1], params[2]);
            case CommandUtils.GET_ADDRESS_TRANSACTION :
                return getParams_GetAddressTransaction(params[0], params[1], params[2]);
            case CommandUtils.LIST_ADDRESS_TRANSACTIONS :
                return getParams_ListAddressTransactions(params[0], params[1], params[2], params[3]);
            case CommandUtils.LIST_WALLET_TRANSACTIONS :
                return getParams_ListWalletTransactions(params[0], params[1], params[2], params[3]);
            case CommandUtils.SEND_FROM :
                return getParams_SendFrom(params[0], params[1], params[2],params[3], params[4]);
            case CommandUtils.SEND_WITH_DATA :
                return getParams_SendWithData(params[0], params[1], params[2]);
            case CommandUtils.SEND_WITH_DATA_FROM :
                return getParams_SendWithDataFrom(params[0], params[1], params[2],params[3]);
            case CommandUtils.LIST_STREAM_PUBLISHERS:
                return getParams_ListStreamPublishers(params[0], params[1], params[2],params[3], params[4], params[5]);
            case CommandUtils.LIST_PERMISSIONS:
                return getParams_Permissions(params[0], params[1], params[2]);
            case CommandUtils.SEND_RAW_TRANSACTION:
                return getParams_Sendrawtransaction(params[0]);
            case CommandUtils.IMPORT_ADDRESS:
                return getParams_Importaddress(params[0], params[1], params[2]);
            case CommandUtils.LIST_ADDRESSES:
                return getParams_Listaddresses(params[0], params[1], params[2],params[3]);
                //작업해!!
            case CommandUtils.GET_BLOCK:
            	return getParams_Getblock(params[0], params[1]);                
            case CommandUtils.GRANT:
            	return getParams_Grant(params[0], params[1], params[2], params[3],params[4], params[5], params[6]);
            case CommandUtils.REVOKE:
            	return getParams_Revoke(params[0], params[1], params[2], params[3], params[4] );
            case CommandUtils.GET_TOTAL_BALANCES:
            	return getParams_Gettotalbalances(params[0], params[1], params[2]);
            case CommandUtils.LIST_UNSPENT:
            	return getParams_Listunspent(params[0], params[1], params[2]);
            case CommandUtils.GET_BLOCKCHAIN_PARAMS:  	
            	return getParams_Getblockchainparams (params[0], params[1]);
            case CommandUtils.GET_RUNTIME_PARAMS:  	
            	return getParams_Getruntimeparams ();
            case CommandUtils.SET_RUNTIME_PARAM: 	
            	return getParams_Setruntimeparam (params[0], params[1]);
            case CommandUtils.HELP: 	
            	return getParams_Help ();
            case CommandUtils.STOP:	
            	return getParams_Stop ();
            case CommandUtils.ADD_MULTISIG_ADDRESS:	
            	return getParams_Addmultisigaddress (params[0], params[1]);
            case CommandUtils.GET_ADDRESSES:
            	return getParams_Getaddresses (params[0]);
            case CommandUtils.CREATE_KEYPAIRS:
            	return getParams_Createkeypairs (params[0]);
            case CommandUtils.CREATE_MULTISIG:
            	return getParams_Createmultisig (params[0],params[1]);
            case CommandUtils.VALIDATE_ADDRESS:
            	return getParams_Validateaddress (params[0]);
            case CommandUtils.GRANT_FROM:
            	return getParams_Grantfrom (params[0], params[1], params[2], params[3], params[4], 
        		  params[5], params[6], params[7]);
            case CommandUtils.GRANT_WITH_DATA:
            	return getParams_Grantwithdata (params[0], params[1], params[2], params[3], params[4], 
		  params[5]);
            case CommandUtils.GRANT_WITH_DATA_FROM:
            	return getParams_Grantwithdatafrom (params[0], params[1], params[2], params[3], params[4], 
		  params[5], params[6]);
            case CommandUtils.REVOKE_FROM:
            	return getParams_Revokefrom (params[0], params[1], params[2], params[3], params[4], params[5]);
            case CommandUtils.ISSUE:
            	return getParams_Issue (params[0], params[1], params[2], params[3], params[4], params[5]) ;
            case CommandUtils.ISSUE_FROM:
            	return getParams_Issuefrom (params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
            case CommandUtils.ISSUE_MORE:
            	return getParams_Issuemore (params[0], params[1], params[2], params[3], params[4]) ;
            case CommandUtils.ISSUE_MORE_FROM:
            	return getParams_Issuemorefrom (params[0], params[1], params[2], params[3], params[4], params[5]);
            case CommandUtils.LIST_ASSETS:
            	return getParams_Listassets (params[0], params[1], params[2], params[3]);
 //           case TransactionDef.GET_ASSET_BALANCES:
 //           	return getParams_Getassetbalances (params[0], params[1], params[2], params[3]);
            case CommandUtils.GET_MULTIBALANCES:
            	return getParams_Getmultibalances (params[0], params[1], params[2], params[3], params[4]);
            case CommandUtils.GET_WALLET_TRANSACTION:
            	return getParams_Getwallettransaction (params[0], params[1], params[2]) ;
            case CommandUtils.SEND:
            	return getParams_Send (params[0], params[1], params[2], params[3]);
            case CommandUtils.SEND_ASSET:
            	return getParams_Sendasset (params[0], params[1], params[2], params[3], params[4], params[5]);
            case CommandUtils.SEND_ASSET_FROM:
            	return getParams_Sendassetfrom (params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
    		case CommandUtils.APPEND_RAW_EXCHANGE:
    			return getParams_Appendrawexchange (params[0], params[1], params[2], params[3]);
    		case CommandUtils.COMPLETE_RAW_EXCHANGE:
    			return getParams_Completerawexchange (params[0], params[1], params[2], params[3], params[4]);
    		case CommandUtils.CREATE_RAW_EXCHANGE:
    			return getParams_Createrawexchange (params[0], params[1], params[2]);
    		case CommandUtils.DECODE_RAW_EXCHANGE:
    			return getParams_Decoderawexchange (params[0], params[1]);
    		case CommandUtils.DISABLE_RAW_TRANSACTION:
    			return getParams_Disablerawtransaction (params[0]);
    		case CommandUtils.PREPARE_LOCK_UNSPENT:
    			return getParams_Preparelockunspent (params[0], params[1]);
    		case CommandUtils.PREPARE_LOCK_UNSPENT_FROM:
    			return getParams_Preparelockunspentfrom (params[0], params[1], params[2]);
    		case CommandUtils.CREATE_T_STREAM:
    			return getParams_Create_stream (params[0], params[1], params[2], params[3]);
    		case CommandUtils.CREATE_FROM_T_STREAM:
    			return getParams_Createfrom_stream (params[0], params[1], params[2], params[3], params[4]) ;
    		case CommandUtils.LIST_STREAMS:
    			return getParams_Liststreams (params[0], params[1], params[2], params[3]);
    		case CommandUtils.PUBLISH:
    			return getParams_Publish (params[0], params[1], params[2]);
    		case CommandUtils.PUBLISH_FROM:
    			return getParams_Publishfrom (params[0], params[1], params[2], params[3]);
    		case CommandUtils.SUBSCRIBE:
    			return getParams_Subscribe (params[0], params[1]);
    		case CommandUtils.UNSUBSCRIBE:
    			return getParams_Unsubscribe (params[0]);
    		case CommandUtils.GET_ASSET_TRANSACTION:
    			return getParams_Getassettransaction (params[0], params[1], params[2]);
    		case CommandUtils.LIST_ASSET_TRANSACTIONS:
    			return getParams_Listassettransactions (params[0], params[1], params[2], params[3], params[4]);
    		case CommandUtils.GET_STREAMITEM:
    			return getParams_Getstreamitem (params[0], params[1], params[2]);
    		case CommandUtils.GET_TXOUT_DATA:
    			return getParams_Gettxoutdata (params[0], params[1], params[2], params[3]);
    		case CommandUtils.LIST_STREAM_BLOCK_ITEMS:
    			return getParams_Liststreamblockitems (params[0], params[1], params[2], params[3], params[4]);
    		case CommandUtils.LIST_STREAM_KEY_ITEMS:
    			return getParams_Liststreamkeyitems (params[0], params[1], params[2], params[3], params[4], params[5]); 
    		case CommandUtils.LIST_STREAM_KEYS:
    			return getParams_Liststreamkeys (params[0], params[1], params[2], params[3], params[4], params[5]);
    		case CommandUtils.LIST_STREAM_ITEMS:
    			return getParams_Liststreamitems (params[0], params[1], params[2], params[3], params[4]);
    		case CommandUtils.LIST_STREAM_PUBLISHER_ITEMS:
    			return getParams_Liststreampublisheritems (params[0], params[1], params[2], params[3], params[4], params[5]);
    		case CommandUtils.COMBINE_UNSPENT:
    			return getParams_Combineunspent (params[0], params[1], params[2], params[3], params[4], params[5]);
    		case CommandUtils.LIST_LOCK_UNSPENT:
    			return getParams_Listlockunspent ();
    		case CommandUtils.LOCK_UNSPENT:
    			return getParams_Lockunspent (params[0], params[1]);
    		case CommandUtils.APPEND_RAW_CHANGE:
    			return getParams_Appendrawchange (params[0], params[1], params[2]); 
    		case CommandUtils.APPEND_RAW_DATA:
    			return getParams_Appendrawdata (params[0], params[1]); 
    		case CommandUtils.APPEND_RAW_TRANSACTION:
    			return getParams_Appendrawtransaction (params[0], params[1], params[2], params[3], params[4]);
    		case CommandUtils.CREATE_RAW_TRANSACTION:
    			return getParams_Createrawtransaction (params[0], params[1], params[2], params[3]); 
    		case CommandUtils.CREATE_RAW_SEND_FROM:
    			return getParams_Createrawsendfrom (params[0], params[1], params[2], params[3]);
    		case CommandUtils.DECODE_RAW_TRANSACTION:
    			return getParams_Decoderawtransaction (params[0]);
    		case CommandUtils.SIGN_RAW_TRANSACTION:
    			return getParams_Signrawtransaction (params[0], params[1], params[2], params[3]);
    		case CommandUtils.ADD_NODE:
    			return getParams_Addnode (params[0], params[1]);
    		case CommandUtils.GET_ADDED_NODE_INFO:
    			return getParams_Getaddednodeinfo (params[0], params[1]);
    		case CommandUtils.GET_NETWORK_INFO:
    			return getParams_Getnetworkinfo ();
    		case CommandUtils.GET_PEER_INFO:
    			return getParams_Getpeerinfo (); 
    		case CommandUtils.PING:
    			return getParams_Ping ();
    		case CommandUtils.SIGNMESSAGE:
    			return getParams_Signmessage (params[0], params[1]);
    		case CommandUtils.VERIFY_MESSAGE:
    			return getParams_Verifymessage (params[0], params[1], params[2]);
    		case CommandUtils.GET_BLOCKCHAIN_INFO:
    			return getParams_Getblockchaininfo ();
    		case CommandUtils.GET_BLOCKHASH:
    			return getParams_Getblockhash(params[0]);
    		case CommandUtils.GET_MEMPOOL_INFO:
    			return getParams_Getmempoolinfo ();
    		case CommandUtils.GET_RAW_MEMPOOL:
    			return getParams_Getrawmempool (); 
    		case CommandUtils.GET_RAW_TRANSACTION:
    			return getParams_Getrawtransaction (params[0], params[1]);
    		case CommandUtils.GET_TXOUT:
    			return getParams_Gettxout (params[0], params[1], params[2]); 
    		case CommandUtils.LIST_BLOCKS:
    			return getParams_Listblocks (params[0], params[1]);
    		case CommandUtils.BACKUP_WALLET:
    			return getParams_Backupwallet (params[0]); 
    		case CommandUtils.DUMP_PRIVKEY:
    			return getParams_Dumpprivkey (params[0]); 
    		case CommandUtils.DUMP_WALLET:
    			return getParams_Dumpwallet (params[0]); 
    		case CommandUtils.ENCRYPT_WALLET:
    			return getParams_Encryptwallet (params[0]);
    		case CommandUtils.GET_WALLET_INFO:
    			return getParams_Getwalletinfo ();
    		case CommandUtils.IMPORT_PRIVKEY:
    			return getParams_Importprivkey (params[0], params[1], params[2]);
    		case CommandUtils.IMPORT_WALLET:
    			return getParams_Importwallet (params[0]);
    		case CommandUtils.WALLET_LOCK:
    			return getParams_Walletlock ();
    		case CommandUtils.WALLET_PASSPHRASE:
    			return getParams_Walletpassphrase (params[0], params[1]);
    		case CommandUtils.WALLET_PASSPHRASE_CHANGE:
    			return getParams_Walletpassphrasechange (params[0], params[1]);
    		case CommandUtils.APPROVE_FROM:
    			return getParams_Approvefrom (params[0], params[1], params[2]);
//    		case TransactionDef.CREATE_T_UPGRADE:
//    			return getParams_Create_upgrade (params[0], params[1], params[2], params[3]); 
//    		case TransactionDef.CREATE_FROM_T_UPGRADE:
//    			return getParams_Createfrom_upgrade (params[0], params[1], params[2], params[3], params[4]); 
    		case CommandUtils.LIST_UPGRADES:
    			return getParams_Listupgrades (params[0]);
    		case CommandUtils.CLEAR_MEMPOOL:
    			return getParams_Clearmempool ();
    		case CommandUtils.PAUSE:
    			return getParams_Pause (params[0]);
    		case CommandUtils.RESUME:
    			return getParams_Resume (params[0]);
    		case CommandUtils.SET_LAST_BLOCK:
    			return getParams_Setlastblock (params[0]);
        }
        return new JSONArray();
    }

    private JSONArray getParams_GetInfo() {
        //{""},//"getinfo"
        JSONArray params = new JSONArray();
        return params;
    }
    
    private JSONArray getParams_GetNewAddress() {
        //{""},//"getnewaddress"
        JSONArray params = new JSONArray();
        return params;
    }
    
    private JSONArray getParams_GetAddressBalances(String address, String minconf, String includeLocked) {
        //{"address", "(minconf=1)", "(includeLocked=false)"},//"getaddressbalances",
        boolean locked = false;
        if(includeLocked!=null&&!includeLocked.isEmpty())
            locked = includeLocked.equals("true")?true:false;

        JSONArray params = new JSONArray();
        if(address!=null) params.put(address);
        if(minconf!=null) params.put(Integer.valueOf(minconf));
        if(includeLocked!=null) params.put(locked);
        return params;
    }
    
    private JSONArray getParams_GetAddressTransaction(String address, String txid,  String verbose) {
        //{"address", "txid",  "(verbose=false)"},//"getaddresstransaction"
        boolean vb = false;
        if(verbose!=null&&!verbose.isEmpty())
            vb = verbose.equals("true")?true:false;

        JSONArray params = new JSONArray();
        if(address!=null) params.put(address);
        if(txid!=null) params.put(txid);
        if(verbose!=null) params.put(vb);
        return params;
    }
    
    private JSONArray getParams_ListAddressTransactions(String address, String count, String skip, String verbose) throws JSONException {
        //{"address", "count", "skip", "(verbose=false)"},//"listaddresstransactions",
        boolean vb = false;
        if(verbose!=null&&!verbose.isEmpty())
            vb = verbose.equals("true")?true:false;

        JSONArray params = new JSONArray();
        if(address!=null) params.put(address);
        if(count!=null) params.put(Integer.valueOf(count));
        if(skip!=null) params.put(Integer.valueOf(skip));
        if(verbose!=null) params.put(vb);
        return params;
    }
    
    private JSONArray getParams_ListWalletTransactions(String count, String skip, String includeWatchOnly, String verbose) throws JSONException {
        //{"(count=10)", "(skip=0)", "(includeWatchOnly=false)", "(verbose=false)"},//"listwallettransactions",
    	if(count==null||count.isEmpty()) count = "10";
    	if(skip==null||skip.isEmpty()) skip = "0";
        boolean wo = false;
        if(includeWatchOnly!=null&&!includeWatchOnly.isEmpty())
            wo = includeWatchOnly.equals("true")?true:false;
        boolean vb = false;
        if(verbose!=null&&!verbose.isEmpty())
            vb = verbose.equals("true")?true:false;

        JSONArray params = new JSONArray();
        if(count!=null) params.put(Integer.valueOf(count));
        if(skip!=null) params.put(Integer.valueOf(skip));
        if(includeWatchOnly!=null) params.put(wo);
        else return params;
        if(verbose!=null) params.put(vb);
        return params;
    }
    
    private JSONArray getParams_SendFrom(String from_address, String to_address, String amount, String comment, String comment_to) throws JSONException {
        //{"from-address", "to-address", "amount", "(comment)", "(comment-to")},//"send",
        double am = 0;
        if(amount!=null&&!amount.isEmpty()) am = Double.parseDouble(amount);
        
        if(comment==null||comment.isEmpty()) comment = "";
    	if(comment_to==null||comment_to.isEmpty()) comment_to = "";

        JSONArray params = new JSONArray();
        if(from_address!=null) params.put(from_address);
        if(to_address!=null) params.put(to_address);
        if(amount!=null) params.put(am);
        if(comment!=null) params.put(comment);
        else return params;
        if(comment_to!=null) params.put(comment_to);
        return params;
    }
    
    private JSONArray getParams_SendWithData(String address, String amount, String data) throws JSONException {
        //{"address", "amount", "data-hex|object"},//"sendwithdata"
        double am = 0;
        if(amount!=null&&!amount.isEmpty()) am = Double.parseDouble(amount);
        String hexString = toHexString(data);

        JSONArray params = new JSONArray();
        if(address!=null) params.put(address);
        if(amount!=null) params.put(am);
        if(hexString!=null) params.put(hexString);
        return params;
    }

    private JSONArray getParams_SendWithDataFrom(String from_address, String to_address, String amount, String data) throws JSONException {
        //{"from-address", "to-address", "amount", "data-hex|object"},//"sendwithdatafrom"
        double am = 0;
        if(amount!=null&&!amount.isEmpty()) am = Double.parseDouble(amount);
        String hexString = toHexString(data);

        JSONArray params = new JSONArray();
        if(from_address!=null) params.put(from_address);
        if(to_address!=null) params.put(to_address);
        if(amount!=null) params.put(am);
        if(hexString!=null) params.put(hexString);
        return params;
    }

    private JSONArray getParams_ListStreamPublishers(String stream, String addresses, String verbose, String count, String start, String local_ordering){
        //{"stream", "(addresses=*)", "(verbose=false)", "(count=MAX)", "(start=-count)", "(local-ordering=false)"}//"liststreampublishers"

        boolean vb = false;
        if(verbose!=null&&!verbose.isEmpty())
            vb = verbose.equals("true")?true:false;
        boolean lo = false;
        if(local_ordering!=null&&!local_ordering.isEmpty())
            lo = local_ordering.equals("true")?true:false;

        JSONArray params = new JSONArray();
        if(stream!=null) params.put(stream);
        if(addresses!=null) params.put(addresses);
        else return params;
        if(verbose!=null) params.put(vb);
        else return params;
        if(count!=null) params.put(Integer.valueOf(count));
        else return params;
        if(start!=null) params.put(Integer.valueOf(start));
        else return params;
        if(local_ordering!=null) params.put(lo);

        return params;
    }

    private JSONArray getParams_Permissions(String permissions, String addresses, String verbose){
        //{"(permissions=*)", "(addresses=*)", "(verbose=false)"}//"listpermissions"

        boolean vb = false;
        if(verbose!=null&&!verbose.isEmpty())
            vb = verbose.equals("true")?true:false;

        JSONArray params = new JSONArray();
        if(permissions!=null) {
        	String items = permissions.replace(" ", "");
        	params.put(items);
        }
        else return params;
        if(addresses!=null){
        	String items = addresses.replace(" ", "");
        	params.put(items);
        }
        else return params;
        if(verbose!=null) params.put(vb);

        return params;
    }
    
    private JSONArray getParams_Sendrawtransaction(String tx_hex){
        //{"tx-hex"}//"sendrawtransaction"

        JSONArray params = new JSONArray();
        if(tx_hex!=null) params.put(tx_hex);
        
        return params;
    }
    
    private JSONArray getParams_Importaddress(String permissions, String addresses, String verbose){
        //{"address(es)", "(label)", "(rescan=true)"}//"listpermissions"

        boolean vb = false;
        if(verbose!=null&&!verbose.isEmpty())
            vb = verbose.equals("true")?true:false;

        JSONArray params = new JSONArray();
        if(permissions!=null) {
        	String items = permissions.replace(" ", "");
        	params.put(items);        	
        }
        if(addresses!=null) params.put(addresses);
        else return params;
        if(verbose!=null) params.put(vb);

        return params;
    }
    
    private JSONArray getParams_Listaddresses(String addresses, String verbose, String count, String start){
        //{"(addresses=*)", "(verbose=false)", "(count=MAX)", "(start=-count)"}//"listpermissions"

        boolean vb = false;
        if(verbose!=null&&!verbose.isEmpty())
            vb = verbose.equals("true")?true:false;

        JSONArray params = new JSONArray();
        if(addresses!=null){
        	String items = addresses.replace(" ", "");
        	params.put(items);
        }        	
        else return params;
        if(verbose!=null) params.put(vb);
        else return params;
        if(count!=null) params.put(Integer.valueOf(count));
        else return params;
        if(start!=null) params.put(Integer.valueOf(start));

        return params;
    }
    
    private JSONArray getParams_Getblock(String hash, String verbose) {
    	// hash|height (verbose=1)
    	boolean vb = false;
        if(verbose!=null&&!verbose.isEmpty())
            vb = verbose.equals("true")?true:false;
        
        JSONArray params = new JSONArray();
        if(hash!=null) {
			if(hash.length()>=32) params.put(hash);//hash
			else params.put(hash);//height
		}
        if(verbose!=null) params.put(vb);
        
        return params;    	
    }
    
	private JSONArray getParams_Grant(String addresses, String permissions, String nativeAmount, String startBlock, String endBlock, String comment, String commentTo) {
		//addresses permissions	(native-amount=0) (start-block) (end-block)	(comment) (comment-to)
		
		JSONArray params = new JSONArray();
        if(addresses!=null){
        	String items = addresses.replace(" ", "");
        	params.put(items);
        }       
        if(permissions!=null){
        	String items = permissions.replace(" ", "");
        	params.put(items);
        }       
        if(nativeAmount!=null) params.put(Integer.valueOf(nativeAmount));
        else return params;
        if(startBlock!=null) params.put(Integer.valueOf(startBlock));
        else return params;
        if(endBlock!=null) params.put(Integer.valueOf(endBlock));
        else return params;
        if(comment!=null) params.put(comment);
        else return params;
        if(commentTo!=null) params.put(commentTo);
        else return params;

        return params;
	}
	
	private JSONArray getParams_Revoke(String addresses, String permissions, String nativeAmount, String comment, String commentTo) {
		//addresses permissions	(native-amount=0) (comment) (comment-to)
		
		JSONArray params = new JSONArray();
		if(addresses!=null){
        	String items = addresses.replace(" ", "");
        	params.put(items);
        }       
        if(permissions!=null){
        	String items = permissions.replace(" ", "");
        	params.put(items);
        }       
        if(nativeAmount!=null) params.put(Integer.valueOf(nativeAmount));
        else return params;        
        if(comment!=null) params.put(comment);
        else return params;
        if(commentTo!=null) params.put(commentTo);

        return params;
		
	}
	
	private JSONArray getParams_Gettotalbalances(String minconf, String includeWatchOnly, String includeLocked) {
		//(minconf=1) (includeWatchOnly=false) (includeLocked=false)
		
		boolean iw = false;
        if(includeWatchOnly!=null&&!includeWatchOnly.isEmpty())
            iw = includeWatchOnly.equals("true")?true:false;
        
        boolean il = false;
        if(includeLocked!=null&&!includeLocked.isEmpty())
            il = includeLocked.equals("true")?true:false;
		
		JSONArray params = new JSONArray();
		if(minconf!=null) params.put(Integer.valueOf(minconf));
        else return params;
		if(includeWatchOnly!=null) params.put(iw);
        else return params;
		if(includeLocked!=null) params.put(il);
        else return params;
		
		return params;
	}
	
	private JSONArray getParams_Listunspent(String minconf, String maxconf, String addresses) {
		//"(minconf=1)", "(maxconf=999999)", "([\"address\", ...])"
		JSONArray params = new JSONArray();
		
		if(minconf!=null) params.put(Integer.valueOf(minconf));
        else return params;
		if(maxconf!=null) params.put(Integer.valueOf(maxconf));
        else return params;
		if(addresses!=null&&!addresses.isEmpty()) {
			try {
				JSONArray subParams;
				subParams = new JSONArray(addresses);
				params.put(subParams);		
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
				
		}
		
		return params;
	}
	
	
	private JSONArray getParams_Getblockchainparams (
			String display_names, String with_upgrades) {
	//(display-names=true) (with-upgrades=true)//"getblockchainparams",
		JSONArray params = new JSONArray();
		
		boolean dn = false;
        if(display_names!=null&&!display_names.isEmpty())
            dn = display_names.equals("true")?true:false;
        
        boolean wu = false;
        if(with_upgrades!=null&&!with_upgrades.isEmpty())
            wu = with_upgrades.equals("true")?true:false;
        
        if(display_names!=null) params.put(dn);
        else return params;
		if(with_upgrades!=null) params.put(wu);
        
		return params;
	}
	
	private JSONArray getParams_Getruntimeparams () {
	//null,//"getruntimeparams",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Setruntimeparam (
            String param, String value) {
	//param value//"setruntimeparam",
		
		JSONArray params = new JSONArray();
		if(param!=null) params.put(param);
        if(value!=null) params.put(value);
		return params;
	}
	
	private JSONArray getParams_Help () {
	//null,//"help",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Stop () {
	//null,//"stop",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Addmultisigaddress (
            String nrequired, String keys) {
	//nrequired ["key", ...]//"addmultisigaddress",
		JSONArray params = new JSONArray();
		
		if(nrequired!=null) params.put(nrequired);
		if(keys!=null&&!keys.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(keys);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		
		return params;
	}
	
	private JSONArray getParams_Getaddresses (
            String verbose) {
	//(verbose=false)//"getaddresses",
		JSONArray params = new JSONArray();
		
		boolean bData = false;
        if(verbose!=null&&!verbose.isEmpty())
        	bData = verbose.equals("true")?true:false;
        
		if(verbose!=null) params.put(bData);
		
		return params;
	}
	
	private JSONArray getParams_Createkeypairs (
            String count) {
	//(count=1)//"createkeypairs",
		JSONArray params = new JSONArray();
		
		if(count!=null) params.put(Integer.valueOf(count));
		return params;
	}
	
	private JSONArray getParams_Createmultisig (
             String nrequired, String keys) {
	//nrequired ["key", ...]//"createmultisig",
		JSONArray params = new JSONArray();
		
		if(nrequired!=null) params.put(nrequired);
		
		if(keys!=null&&!keys.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(keys);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return params;
	}
	
	private JSONArray getParams_Validateaddress (
             String address) {
	//address| privkey|pubkey//"validateaddress",
		JSONArray params = new JSONArray();
		
		if(address!=null) params.put(address);
		return params;
	}
	
	private JSONArray getParams_Grantfrom (			
            String from_address, String to_addresses, String permissions, String native_amount, 
            String start_block, String end_block, String comment, String comment_to) {
	//from-address to-addresses permissions (native-amount=0) (start-block) (end-block) (comment) (comment-to)//"grantfrom",
		JSONArray params = new JSONArray();
		
		if(from_address!=null) params.put(from_address);
		if(to_addresses!=null) params.put(to_addresses);
		if(permissions!=null){
        	String items = permissions.replace(" ", "");
        	params.put(items);
        }     
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(start_block!=null) params.put(Integer.valueOf(start_block));
		else return params;
		if(end_block!=null) params.put(Integer.valueOf(end_block));
		else return params;
		if(comment!=null) params.put(comment);
		else return params;
		if(comment_to!=null) params.put(comment_to);
		
		return params;
	}
	
	private JSONArray getParams_Grantwithdata (
            String addresses, String permissions, String data_hex, String native_amount,
            String start_block, String end_block) {
	//addresses permissions data-hex|object (native-amount=0) (start-block) (end-block)//"grantwithdata",
		JSONArray params = new JSONArray();
		
		if(addresses!=null) {
			String items = addresses.replace(" ", "");			
			params.put(items.trim());
		}
		if(permissions!=null){
        	String items = permissions.replace(" ", "");
        	params.put(items.trim());
        }     
		if(data_hex!=null) params.put(data_hex);
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(start_block!=null) params.put(Integer.valueOf(start_block));
		else return params;
		if(end_block!=null) params.put(Integer.valueOf(end_block));
		
		return params;
	}
	
	private JSONArray getParams_Grantwithdatafrom (
            String from_address, String to_addresses, String permissions, String data_hex, 
            String native_amount, String start_block, String end_block) {
	//from-address to-addresses permissions data-hex|object (native-amount=0) (start-block) (end-block)//"grantwithdatafrom",
		JSONArray params = new JSONArray();
		if(from_address!=null) params.put(from_address);
		if(to_addresses!=null) {
			String items = to_addresses.replace(" ", "");			
			params.put(items.trim());
		}
		if(permissions!=null){
        	String items = permissions.replace(" ", "");
        	params.put(items.trim());
        }   
		if(data_hex!=null) params.put(data_hex);
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(start_block!=null) params.put(Integer.valueOf(start_block));
		else return params;
		if(end_block!=null) params.put(Integer.valueOf(end_block));
		return params;
	}
	
	private JSONArray getParams_Revokefrom (
			String from_address, String to_addresses, String permissions, 
            String native_amount, String comment, String comment_to) {
	//from-address to-addresses permissions (native-amount=0) (comment) (comment-to)//"revokefrom",
		JSONArray params = new JSONArray();
		if(from_address!=null) params.put(from_address);
		if(to_addresses!=null) {
			String items = to_addresses.replace(" ", "");			
			params.put(items.trim());
		}
		if(permissions!=null){
        	String items = permissions.replace(" ", "");
        	params.put(items.trim());
        }   
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(comment!=null) params.put(comment);
		else return params;
		if(comment_to!=null) params.put(comment_to);
		return params;
	}
	
	private JSONArray getParams_Issue (
    		String address, String name, String qty,
    		String units, String native_amount, String custom_field) {
	//address name|params qty (units=1) (native-amount=min-per-output) ({"custom-field-1":"x",...})//"issue",
		JSONArray params = new JSONArray();
		if(address!=null) params.put(address);
		if(name!=null){
        	String items = name.replace(" ", "");
        	params.put(items.trim());
        }   
		if(qty!=null) params.put(Integer.valueOf(qty));
		if(units!=null) params.put(Integer.valueOf(units));
		else return params;
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(custom_field!=null&&!custom_field.isEmpty()) {			
			try {
				JSONObject subParams;
				subParams = new JSONObject(custom_field);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		
		return params;
	}
	
	private JSONArray getParams_Issuefrom (
    		String from_address, String to_address, String name, String qty,
    		String units, String native_amount, String custom_field) {
	//from-address to-address name|params qty (units=1) (native-amount=min-per-output) ({"custom-field-1":"x",...})//"issuefrom",
		JSONArray params = new JSONArray();
		
		if(from_address!=null) params.put(from_address);
		if(to_address!=null) params.put(to_address);
		if(name!=null){
        	String items = name.replace(" ", "");
        	params.put(items.trim());
        }   
		if(qty!=null) params.put(Integer.valueOf(qty));
		if(units!=null) params.put(Integer.valueOf(units));
		else return params;
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(custom_field!=null&&!custom_field.isEmpty()) {			
			try {
				JSONObject subParams;
				subParams = new JSONObject(custom_field);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		return params;
	}
	
	private JSONArray getParams_Issuemore (
    		String address, String asset, String qty,
    		String native_amount, String custom_field) {
	//address asset qty (native-amount=min-per-output) ({"custom-field-1":"x",...})//"issuemore",
		JSONArray params = new JSONArray();
		
		if(address!=null) params.put(address);
		
		if(asset!=null) params.put(asset);   
		if(qty!=null) params.put(Integer.valueOf(qty));
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(custom_field!=null&&!custom_field.isEmpty()) {			
			try {
				JSONObject subParams;
				subParams = new JSONObject(custom_field);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		return params;
	}
	
	private JSONArray getParams_Issuemorefrom (
    		String from_address, String to_address, String asset, String qty,
    		String native_amount, String custom_field) {
	//from-address to-address asset qty (native-amount=min-per-output) ({"custom-field-1":"x",...})//"issuemorefrom",
		JSONArray params = new JSONArray();
		
		if(from_address!=null) params.put(from_address);
		if(to_address!=null) params.put(to_address);
		if(asset!=null) params.put(asset);   
		if(qty!=null) params.put(Integer.valueOf(qty));
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(custom_field!=null&&!custom_field.isEmpty()) {			
			try {
				JSONObject subParams;
				subParams = new JSONObject(custom_field);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		return params;
	}
	
	private JSONArray getParams_Listassets (
    		String assets, String verbose, String count, String start) {
	//(assets=*) (verbose=false) (count=MAX) (start=-count)//"listassets",
		JSONArray params = new JSONArray();
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
        	vb = verbose.equals("true")?true:false;
		
		if(assets!=null) {
			String items = assets.replace(" ", "");
        	params.put(items.trim());			
		}
		else return params;
		if(verbose!=null) params.put(vb);
		else return params;
		if(count!=null) params.put(Integer.valueOf(count));
		else return params;
		if(start!=null) params.put(Integer.valueOf(start));
		
		return params;
	}
	
//	private JSONArray getParams_Getassetbalances (
//    		String account, String minconf, String includeWatchOnly, String includeLocked) {
//	//(account=&quot;&quot;) (minconf=1) (includeWatchOnly=false) (includeLocked=false)//"getassetbalances",
//		JSONArray params = new JSONArray();
//		return params;
//	}
	
	private JSONArray getParams_Getmultibalances (
    		String addresses, String assets, String minconf, String includeWatchOnly, String includeLocked) {
	//(addresses=*)</br>(assets=*)</br>(minconf=1) (includeWatchOnly=false) (includeLocked=false)//"getmultibalances",
		
		boolean iw = false;
		if(includeWatchOnly!=null&&!includeWatchOnly.isEmpty())
        	iw = includeWatchOnly.equals("true")?true:false;
		boolean il = false;
		if(includeLocked!=null&&!includeLocked.isEmpty())
        	il = includeLocked.equals("true")?true:false;
		
		JSONArray params = new JSONArray();
		if(addresses!=null) {
			String items = addresses.replace(" ", "");
        	params.put(items.trim());			
		}
		else return params;
		if(assets!=null) {
			String items = assets.replace(" ", "");
        	params.put(items.trim());			
		}
		else return params;
		if(minconf!=null) params.put(Integer.valueOf(minconf));
		else return params;
		if(includeWatchOnly!=null) params.put(iw);
		else return params;
		if(includeLocked!=null) params.put(il);
		
		return params;
	}
	
	private JSONArray getParams_Getwallettransaction (
			String txid, String includeWatchOnly, String verbose) {
	//txid (includeWatchOnly=false) (verbose=false)//"getwallettransaction",
		JSONArray params = new JSONArray();
		
		boolean iw = false;
		if(includeWatchOnly!=null&&!includeWatchOnly.isEmpty())
        	iw = includeWatchOnly.equals("true")?true:false;
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
        	vb = verbose.equals("true")?true:false;
		
		if(txid!=null) params.put(txid);			
		if(includeWatchOnly!=null) params.put(iw);
		else return params;
		if(verbose!=null) params.put(vb);
		else return params;
		
		return params;
	}
	
	private JSONArray getParams_Send (
	 String address, String amount, String comment, String comment_to) {
	//address amount (comment) (comment-to)//"send",
		JSONArray params = new JSONArray();
		
		if(address!=null) params.put(address);
		if(amount!=null) params.put(Integer.valueOf(amount));
		if(comment!=null) params.put(comment);
		else return params;
		if(comment_to!=null) params.put(comment_to);
		
		return params;
	}
	
	private JSONArray getParams_Sendasset (
			String address, String asset, String qty, String native_amount, String comment, String comment_to) {
	//address asset qty (native-amount=min-per-output) (comment) (comment-to)//"sendasset",   
		JSONArray params = new JSONArray();
		
		if(address!=null) params.put(address);
		if(asset!=null) params.put(asset);   
		if(qty!=null) params.put(Integer.valueOf(qty));
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(comment!=null) params.put(comment);
		else return params;
		if(comment_to!=null) params.put(comment_to);
		
		return params;
	}
	
	private JSONArray getParams_Sendassetfrom (			
			String from_address, String to_address, String asset, String qty, String native_amount, String comment, String comment_to) {
	//from-address to-address asset qty (native-amount=min-per-output) (comment) (comment-to)//"sendassetfrom",
		JSONArray params = new JSONArray();
		
		if(from_address!=null) params.put(from_address);
		if(to_address!=null) params.put(to_address);
		if(asset!=null) params.put(asset);   
		if(qty!=null) params.put(Integer.valueOf(qty));
		if(native_amount!=null) params.put(Integer.valueOf(native_amount));
		else return params;
		if(comment!=null) params.put(comment);
		else return params;
		if(comment_to!=null) params.put(comment_to);
		
		return params;
	}
	
	private JSONArray getParams_Appendrawexchange (
			String tx_hex, String txid, String vout, String asset) {
	//tx-hex txid vout {"asset":qty, ...}//"appendrawexchange",  
		JSONArray params = new JSONArray();
		
		if(tx_hex!=null) params.put(tx_hex);
		if(txid!=null) params.put(txid);
		if(vout!=null) params.put(Integer.valueOf(vout));
		if(asset!=null&&!asset.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(asset);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return params;
	}
	
	private JSONArray getParams_Completerawexchange (
			String tx_hex, String txid, String vout, String asset, String data_hex) {
	//tx-hex txid vout {"asset":qty, ...} (data-hex|object)//"completerawexchange",  
		JSONArray params = new JSONArray();
		if(tx_hex!=null) params.put(tx_hex);
		if(txid!=null) params.put(txid);
		if(vout!=null) params.put(Integer.valueOf(vout));
		if(asset!=null&&!asset.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(asset);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		if(data_hex!=null) params.put(data_hex);
		return params;
	}
	
	private JSONArray getParams_Createrawexchange (
			String tx_hex, String vout, String asset) {
	//txid vout {"asset":qty, ...}//"createrawexchange",    
		JSONArray params = new JSONArray();
		
		if(tx_hex!=null) params.put(tx_hex);
		if(vout!=null) params.put(Integer.valueOf(vout));
		if(asset!=null&&!asset.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(asset);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return params;
	}
	
	private JSONArray getParams_Decoderawexchange (
			String tx_hex, String verbose) {
	//tx-hex (verbose=false)//"decoderawexchange", 
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
        	vb = verbose.equals("true")?true:false;
		
		if(tx_hex!=null) params.put(tx_hex);			
		if(verbose!=null) params.put(vb);
		
		return params;
	}
	
	private JSONArray getParams_Disablerawtransaction (
			String tx_hex) {
	//tx-hex//"disablerawtransaction",  
		JSONArray params = new JSONArray();
		if(tx_hex!=null) params.put(tx_hex);		
		return params;
	}
	
	private JSONArray getParams_Preparelockunspent (
			String asset, String lock) {
	//{"asset":qty, ...} (lock=true)//"preparelockunspent",  
		JSONArray params = new JSONArray();
		
		boolean l = false;
		if(lock!=null&&!lock.isEmpty())
        	l = lock.equals("true")?true:false;
		
		if(asset!=null&&!asset.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(asset);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		if(lock!=null) params.put(l);
		
		return params;
	}
	
	private JSONArray getParams_Preparelockunspentfrom (
			String from_address, String asset, String lock) {
	//from-address {"asset":qty, ...} (lock=true)//"preparelockunspentfrom",   
		JSONArray params = new JSONArray();
		
		boolean l = false;
		if(lock!=null&&!lock.isEmpty())
        	l = lock.equals("true")?true:false;
		
		if(from_address!=null) params.put(from_address);	
		if(asset!=null&&!asset.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(asset);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		if(lock!=null) params.put(l);
		
		return params;
	}
	
	private JSONArray getParams_Create_stream (
			String type, String name, String open, String custom_field_1) {
	//type=stream name open ({"custom-field-1":"x",...})//"create",        
		JSONArray params = new JSONArray();
		
		boolean o = false;
		if(open!=null&&!open.isEmpty())
        	o = open.equals("true")?true:false;
		
		if(type!=null) params.put(type);	
		if(name!=null) params.put(name);	
		if(open!=null) params.put(o);
		if(custom_field_1!=null&&!custom_field_1.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(custom_field_1);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		
		return params;
	}
	
	private JSONArray getParams_Createfrom_stream (
			String from_address, String type, String name, String open, String custom_field_1) {
	//from-address type=stream name open ({"custom-field-1":"x",...})//"createfrom",  
		JSONArray params = new JSONArray();
		
		boolean o = false;
		if(open!=null&&!open.isEmpty())
        	o = open.equals("true")?true:false;
		
		if(from_address!=null) params.put(from_address);
		if(type!=null) params.put(type);	
		if(name!=null) params.put(name);	
		if(open!=null) params.put(o);
		if(custom_field_1!=null&&!custom_field_1.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(custom_field_1);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
		
		return params;
	}
	
	private JSONArray getParams_Liststreams (
			String streams, String verbose, String count, String start) {
	//(streams=*) (verbose=false)</br>(count=MAX) (start=-count)//"liststreams",  
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		if(streams!=null) {
			String items = streams.replace(" ", "");
        	params.put(items.trim());			
		}
		else return params;
		if(verbose!=null) params.put(vb);
		else return params;
		if(count!=null) params.put(Integer.valueOf(count));
		else return params;
		if(start!=null) params.put(Integer.valueOf(start));
		
		return params;
	}
	
	private JSONArray getParams_Publish (
			String stream, String key, String data_hex) {
	//stream key data-hex//"publish", 
		JSONArray params = new JSONArray();
		
		if(stream!=null) params.put(stream);
		if(key!=null) params.put(key);
		if(data_hex!=null) params.put(data_hex);
		
		return params;
	}
	
	private JSONArray getParams_Publishfrom (
			String from_address, String stream, String key, String data_hex) {
	//from-address stream key data-hex//"publishfrom",  
		JSONArray params = new JSONArray();
		
		if(from_address!=null) params.put(from_address);
		if(stream!=null) params.put(stream);
		if(key!=null) params.put(key);
		if(data_hex!=null) params.put(data_hex);
		
		return params;
	}
	
	private JSONArray getParams_Subscribe (
			String asset, String rescan) {
	//asset(s)|stream(s) (rescan=true)//"subscribe", 
		JSONArray params = new JSONArray();
		
		boolean rs = false;
		if(rescan!=null&&!rescan.isEmpty())
        	rs = rescan.equals("true")?true:false;                
		
		if(asset!=null) {
			String items = asset.replace(" ", "");
        	params.put(items.trim());			
		}
		if(rescan!=null) params.put(rs);
		
		
		return params;
	}
	
	private JSONArray getParams_Unsubscribe (
			String asset) {
	//asset(s)|stream(s)//"unsubscribe",  
		JSONArray params = new JSONArray();
		if(asset!=null) {
			String items = asset.replace(" ", "");
        	params.put(items.trim());			
		}
		return params;
	}
	
	
	private JSONArray getParams_Getassettransaction (
			String asset, String txid, String verbose) {
	//asset txid (verbose=false)//"getassettransaction", 
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		if(asset!=null) params.put(asset);
		if(txid!=null) params.put(txid);
        if(verbose!=null) params.put(vb);
                
		return params;
	}
	
	private JSONArray getParams_Listassettransactions (
			String asset, String verbose, String count, String start, String local_ordering) {
	//asset     (verbose=false) (count=10) (start=-count) (local-ordering=false)//"listassettransactions", 
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		boolean lo = false;
		if(local_ordering!=null&&!local_ordering.isEmpty())
			lo = local_ordering.equals("true")?true:false;
		
		if(asset!=null) params.put(asset);		
        if(verbose!=null) params.put(vb);
        else return params;
        if(count!=null) params.put(Integer.valueOf(count));
        else return params;
        if(start!=null) params.put(Integer.valueOf(start));
        else return params;
        if(local_ordering!=null) params.put(lo);
        
		return params;
	}
	
	private JSONArray getParams_Getstreamitem (
			String stream, String txid, String verbose) {
	//stream txid (verbose=false)//"getstreamitem",  
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		if(stream!=null) params.put(stream);
		if(txid!=null) params.put(txid);		
		if(verbose!=null) params.put(vb);
		
		return params;
	}
	
	private JSONArray getParams_Gettxoutdata (
			String txid, String vout, String count_bytes, String start_byte) {
	//txid vout (count-bytes=INT_MAX) (start-byte=0)//"gettxoutdata",
		JSONArray params = new JSONArray();
		
		if(txid!=null) params.put(txid);
		if(vout!=null) params.put(Integer.valueOf(vout));
		if(count_bytes!=null) params.put(Integer.valueOf(count_bytes));
        else return params;
        if(start_byte!=null) params.put(Integer.valueOf(start_byte));
        
		return params;
	}
	
	private JSONArray getParams_Liststreamblockitems (
			String stream, String blocks, String verbose, String count, String start) {
	//stream     blocks</br>(verbose=false) (count=MAX) (start=-count)//"liststreamblockitems", 
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		if(stream!=null) params.put(stream);
		if(blocks!=null) params.put(Integer.valueOf(blocks));
		if(verbose!=null) params.put(vb);
		else return params;
		if(count!=null) params.put(Integer.valueOf(count));
        else return params;
        if(start!=null) params.put(Integer.valueOf(start));
		
		return params;
	}
	
	private JSONArray getParams_Liststreamkeyitems (
			String stream, String key, String verbose, String count, String start, String local_ordering) {
	//stream key (verbose=false) (count=10) (start=-count) (local-ordering=false)//"liststreamkeyitems",  
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		boolean lo = false;
		if(local_ordering!=null&&!local_ordering.isEmpty())
			lo = local_ordering.equals("true")?true:false;
		
		if(stream!=null) params.put(stream);
		if(key!=null) params.put(key);
		if(verbose!=null) params.put(vb);
		else return params;
		if(count!=null) params.put(Integer.valueOf(count));
        else return params;
        if(start!=null) params.put(Integer.valueOf(start));
        else return params;
        if(local_ordering!=null) params.put(lo);
        
		return params;
	}
	
	private JSONArray getParams_Liststreamkeys (
			String stream, String keys, String verbose, String count, String start, String local_ordering) {
	//stream (keys=*) (verbose=false) (count=MAX) (start=-count) (local-ordering=false)//"liststreamkeys",  
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		boolean lo = false;
		if(local_ordering!=null&&!local_ordering.isEmpty())
			lo = local_ordering.equals("true")?true:false;
		
		if(stream!=null) params.put(stream);
		if(keys!=null) {
			String items = keys.replace(" ", "");
        	params.put(items.trim());			
		}
		else return params;
		if(verbose!=null) params.put(vb);
		else return params;
		if(count!=null) params.put(Integer.valueOf(count));
        else return params;
        if(start!=null) params.put(Integer.valueOf(start));
        else return params;
        if(local_ordering!=null) params.put(lo);
        
		return params;
	}
	
	private JSONArray getParams_Liststreamitems (
			String stream, String verbose, String count, String start, String local_ordering) {
	//stream (verbose=false) (count=10) (start=-count) (local-ordering=false)//"liststreamitems", 
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		boolean lo = false;
		if(local_ordering!=null&&!local_ordering.isEmpty())
			lo = local_ordering.equals("true")?true:false;
		
		if(stream!=null) params.put(stream);
		if(verbose!=null) params.put(vb);
		else return params;
		if(count!=null) params.put(Integer.valueOf(count));
        else return params;
        if(start!=null) params.put(Integer.valueOf(start));
        else return params;
        if(local_ordering!=null) params.put(lo);
        
		return params;
	}
	
	private JSONArray getParams_Liststreampublisheritems (
			String stream, String address, String verbose, String count, String start, String local_ordering) {
	//stream     address (verbose=false) (count=10) (start=-count) (local-ordering=false)//"liststreampublisheritems", 
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		boolean lo = false;
		if(local_ordering!=null&&!local_ordering.isEmpty())
			lo = local_ordering.equals("true")?true:false;
		
		if(stream!=null) params.put(stream);
		if(address!=null) params.put(address);
		if(verbose!=null) params.put(vb);
		else return params;
		if(count!=null) params.put(Integer.valueOf(count));
        else return params;
        if(start!=null) params.put(Integer.valueOf(start));
        else return params;
        if(local_ordering!=null) params.put(lo);
        
		return params;
	}
	
	private JSONArray getParams_Combineunspent (
			String addresses, String maxconf, String maxcombines, String mininputs, String maxinputs, String maxtime) {
	//(addresses=*) (minconf=1) (maxcombines=100) (mininputs=2) (maxinputs=100) (maxtime=15)//"combineunspent",                             
		JSONArray params = new JSONArray();
		
		if(addresses!=null) {
			String items = addresses.replace(" ", "");
        	params.put(items.trim());			
		}
		else return params;
		if(maxconf!=null) params.put(Integer.valueOf(maxconf));
        else return params;
		if(maxcombines!=null) params.put(Integer.valueOf(maxcombines));
        else return params;
		if(mininputs!=null) params.put(Integer.valueOf(mininputs));
        else return params;
		if(maxinputs!=null) params.put(Integer.valueOf(maxinputs));
        else return params;
		if(maxtime!=null) params.put(Integer.valueOf(maxtime));
        
		return params;
	}
	
	private JSONArray getParams_Listlockunspent () {
	//null,//"listlockunspent",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Lockunspent (
			String unlock, String txid) {
	//unlock ([{"txid":"id","vout":n},...])//"lockunspent",                                
		JSONArray params = new JSONArray();
		
		boolean ul = false;
		if(unlock!=null&&!unlock.isEmpty())
			ul = unlock.equals("true")?true:false;

        if(unlock!=null) params.put(ul);
		if(txid!=null&&!txid.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(txid);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return params;
	}
	
	private JSONArray getParams_Appendrawchange (
			String tx_hex, String address, String native_fee) {
	//tx-hex address (native-fee)//"appendrawchange",                            
		JSONArray params = new JSONArray();
		
		if(tx_hex!=null) params.put(tx_hex);
		if(address!=null) params.put(address);
		if(native_fee!=null) params.put(Integer.valueOf(native_fee));
		
		return params;
	}
	
	private JSONArray getParams_Appendrawdata (
			String tx_hex, String data_hex) {
	//tx-hex data-hex|object//"appendrawdata",         
		JSONArray params = new JSONArray();
		
		if(tx_hex!=null) params.put(tx_hex);
		if(data_hex!=null) params.put(data_hex);
		
		return params;
	}
	
	private JSONArray getParams_Appendrawtransaction (
			String tx_hex, String txid, String address, String data, String action) {
	//tx-hex [{"txid":"id","vout":n},...] ({"address":amount,...})</br>(data=[]) (action="")//"appendrawtransaction",                       
		JSONArray params = new JSONArray();
		
		if(tx_hex!=null) params.put(tx_hex);
		if(txid!=null&&!txid.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(txid);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
		if(address!=null&&!address.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(address);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		if(data!=null&&!data.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(data);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else return params;
		if(action!=null) params.put(action);
		
		return params;
	}
	
	private JSONArray getParams_Createrawtransaction (
			String txid, String address, String data, String action) {
	//[{"txid":"id","vout":n},...] {"address":amount,...}</br>(data=[]) (action="")//"createrawtransaction",                       
		JSONArray params = new JSONArray();
		
		if(txid!=null&&!txid.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(txid);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
		if(address!=null&&!address.isEmpty()) {
			try {
				JSONObject subParams;
				subParams = new JSONObject(address);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		if(data!=null&&!data.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(data);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else return params;
		if(action!=null) params.put(action);
		
		return params;
	}
	
	private JSONArray getParams_Createrawsendfrom (
			String from_address, String to_address, String data, String action) {
	//from-address {"to-address":amount,...}</br>(data=[]) (action="")//"createrawsendfrom",                          
		JSONArray params = new JSONArray();
		
		if(from_address!=null) params.put(from_address);
		if(to_address!=null&&!to_address.isEmpty()) {			
			try {
				JSONObject subParams;
				subParams = new JSONObject(to_address);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(data!=null&&!data.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(data);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else return params;
		if(action!=null) params.put(action);
		
		return params;
	}
	
	private JSONArray getParams_Decoderawtransaction (
			String tx_hex) {
	//tx-hex//"decoderawtransaction",                       
		JSONArray params = new JSONArray();
		
		if(tx_hex!=null) params.put(tx_hex);
		
		return params;
	}
	
	private JSONArray getParams_Signrawtransaction (
			String tx_hex, String parent_output, String private_key, String sighashtype) {
	//tx-hex ([{parent-output},...]) (["private-key",...]) (sighashtype=ALL)//"signrawtransaction",                         
		JSONArray params = new JSONArray();
		
		if(tx_hex!=null) params.put(tx_hex);
		if(parent_output!=null&&!parent_output.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(parent_output);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else return params;
		if(private_key!=null&&!private_key.isEmpty()) {			
			try {
				JSONArray subParams;
				subParams = new JSONArray(private_key);
				params.put(subParams);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else return params;
		if(sighashtype!=null) params.put(sighashtype);
		
		return params;
	}
	
	private JSONArray getParams_Addnode (String ip, String command) {
	//ip(:port) command//"addnode",                                    
		JSONArray params = new JSONArray();
		
		if(ip!=null) params.put(ip);
		if(command!=null) params.put(command);
		
		return params;
	}
	
	private JSONArray getParams_Getaddednodeinfo (
			String verbose, String ip) {
	//verbose (ip(:port))//"getaddednodeinfo",                           
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;

        if(verbose!=null) params.put(vb);
        if(ip!=null) params.put(ip);
		
		return params;
	}
	
	private JSONArray getParams_Getnetworkinfo () {
	//null,//"getnetworkinfo",                             
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Getpeerinfo () {
	//null,//"getpeerinfo",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Ping () {
	//null,//"ping"
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Signmessage (
			String address, String message) {
	//address|privkey message//"signmessage",                                
		JSONArray params = new JSONArray();
		
		if(address!=null) params.put(address);
		if(message!=null) params.put(message);
		
		return params;
	}
	
	private JSONArray getParams_Verifymessage (
			String address, String signature, String message) {
	//address signature message//"verifymessage",                              
		JSONArray params = new JSONArray();
		
		if(address!=null) params.put(address);
		if(signature!=null) params.put(signature);
		if(message!=null) params.put(message);
		
		return params;
	}
	
	private JSONArray getParams_Getblockchaininfo () {
	//null,//"getblockchaininfo",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Getblockhash (
			String height) {
	//height//"getblockhash",                               
		JSONArray params = new JSONArray();
		
		if(height!=null) params.put(Integer.valueOf(height));
		
		return params;
	}
	
	private JSONArray getParams_Getmempoolinfo () {
	//null,//"getmempoolinfo",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Getrawmempool () {
	//null,//"getrawmempool",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Getrawtransaction (
			String txid, String verbose) {
	//txid (verbose=0)//"getrawtransaction",                          
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		if(txid!=null) params.put(txid);
		if(verbose!=null) params.put(vb);
		
		return params;
	}
	
	private JSONArray getParams_Gettxout (
			String txid, String vout, String unconfirmed) {
	//txid vout (unconfirmed=false)//"gettxout",                                   
		JSONArray params = new JSONArray();
		
		boolean uf = false;
		if(unconfirmed!=null&&!unconfirmed.isEmpty())
			uf = unconfirmed.equals("true")?true:false;
		
		if(txid!=null) params.put(txid);
		if(vout!=null) params.put(Integer.valueOf(vout));
		if(unconfirmed!=null) params.put(uf);
		
		return params;
	}
	
	private JSONArray getParams_Listblocks (
			String blocks, String verbose) {
	//blocks (verbose=false)//"listblocks",                                 
		JSONArray params = new JSONArray();
		
		boolean vb = false;
		if(verbose!=null&&!verbose.isEmpty())
			vb = verbose.equals("true")?true:false;
		
		if(blocks!=null) params.put(blocks);
        if(verbose!=null) params.put(vb);
                
		return params;
	}
	
	private JSONArray getParams_Backupwallet (
			String filename) {
	//filename//"backupwallet",                               
		JSONArray params = new JSONArray();
		
		if(filename!=null) params.put(filename);
		
		return params;
	}
	
	private JSONArray getParams_Dumpprivkey (
			String address) {
	//address//"dumpprivkey",                                
		JSONArray params = new JSONArray();
		
		if(address!=null) params.put(address);
		
		return params;
	}
	
	private JSONArray getParams_Dumpwallet (
			String filename) {
	//filename//"dumpwallet",                                 
		JSONArray params = new JSONArray();
		
		if(filename!=null) params.put(filename);
		
		return params;
	}
	
	private JSONArray getParams_Encryptwallet (
			String passphrase) {
	//passphrase//"encryptwallet",                              
		JSONArray params = new JSONArray();
		
		if(passphrase!=null) params.put(passphrase);
		
		return params;
	}
	
	private JSONArray getParams_Getwalletinfo () {
	//null,//"getwalletinfo",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Importprivkey (
			String privkey, String label, String rescan) {
	//privkey(s) (label) (rescan=true)//"importprivkey",                              
		JSONArray params = new JSONArray();
		
		boolean rc = false;
		if(rescan!=null&&!rescan.isEmpty())
			rc = rescan.equals("true")?true:false;
				
		if(privkey!=null) {
			String items = privkey.replace(" ", "");
        	params.put(items.trim());			
		}
		if(label!=null) params.put(label);
		else return params;
		if(rescan!=null) params.put(rc);
		
		return params;
	}
	
	private JSONArray getParams_Importwallet (
			String filename) {
	//filename//"importwallet",                               
		JSONArray params = new JSONArray();
		
		if(filename!=null) params.put(filename);
		
		return params;
	}
	
	private JSONArray getParams_Walletlock () {
	//null,//"walletlock",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Walletpassphrase (
			String passphrase, String timeout) {
	//passphrase timeout//"walletpassphrase",                           
		JSONArray params = new JSONArray();
		
		if(passphrase!=null) params.put(passphrase);
		if(timeout!=null) params.put(Integer.valueOf(timeout));
		
		return params;
	}
	
	private JSONArray getParams_Walletpassphrasechange (
			String old_passphrase, String new_passphrase) {
	//old-passphrase new-passphrase//"walletpassphrasechange",                     
		JSONArray params = new JSONArray();
		
		if(old_passphrase!=null) params.put(old_passphrase);
		if(new_passphrase!=null) params.put(new_passphrase);
		
		return params;
	}
	
	private JSONArray getParams_Approvefrom (
			String from_address, String upgrade, String approve) {
	//from-address upgrade approve//"approvefrom",                                
		JSONArray params = new JSONArray();
		
		boolean ap = false;
		if(approve!=null&&!approve.isEmpty())
			ap = approve.equals("true")?true:false;
		
        if(from_address!=null) params.put(from_address);
		if(upgrade!=null) params.put(upgrade);
        if(approve!=null) params.put(ap);
        
		return params;
	}
	
//	private JSONArray getParams_Create_upgrade (
//			String type, String name, String open, String protocol_version) {
//	//type=upgrade name open=false {"protocol-version":100xx}//"create",                                     
//		JSONArray params = new JSONArray();
//		return params;
//	}
//	
//	private JSONArray getParams_Createfrom_upgrade (
//			String from_address, String type, String name, String open, String protocol_version) {
//	//from-address type=upgrade name open=false {"protocol-version":100xx}//"createfrom",                                 
//		JSONArray params = new JSONArray();
//		return params;
//	}
	
	private JSONArray getParams_Listupgrades (
			String upgrades) {
	//(upgrades=*)//"listupgrades",                               
		JSONArray params = new JSONArray();
		
		if(upgrades!=null) {
			String items = upgrades.replace(" ", "");
        	params.put(items.trim());			
		}
		
		return params;
	}
	
	private JSONArray getParams_Clearmempool () {
	//null,//"clearmempool",
		JSONArray params = new JSONArray();
		return params;
	}
	
	private JSONArray getParams_Pause (
			String tasks) {
	//tasks//"pause",                                     
		JSONArray params = new JSONArray();
		
		if(tasks!=null) {
			String items = tasks.replace(" ", "");
        	params.put(items.trim());			
		}
		
		return params;
	}
	
	private JSONArray getParams_Resume (
			String tasks) {
	//tasks//"resume",                              
		JSONArray params = new JSONArray();
		
		if(tasks!=null) {
			String items = tasks.replace(" ", "");
        	params.put(items.trim());			
		}
		
		return params;
	}
	
	private JSONArray getParams_Setlastblock (
			String hash) {
	//hash|height//"setlastblock"
		JSONArray params = new JSONArray();
		
		if(hash!=null) {
			if(hash.length()>=32) params.put(hash);//hash
			else params.put(Integer.valueOf(hash));//height
		}
		
		return params;
	}
	
}
