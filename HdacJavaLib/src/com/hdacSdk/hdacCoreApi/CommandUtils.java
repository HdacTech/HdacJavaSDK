package com.hdacSdk.hdacCoreApi;

/**
 * @brief Indexing of blockchain APIs and parameters
 * @details Supporting information of blockchain API Id definition and parameters corresponding to API (help function)
 * @class CommandUtils
 * @date 2018-01-31.
 * @author Hdac Technology 
 *
 */
public class CommandUtils {
	
	public static final int GET_INFO                                       = 0;
    public static final int GET_NEW_ADDRESS                                = 1;
    public static final int GET_ADDRESS_BALANCES                           = 2;
    public static final int GET_ADDRESS_TRANSACTION                        = 3;
    public static final int LIST_ADDRESS_TRANSACTIONS                      = 4;
    public static final int LIST_WALLET_TRANSACTIONS                       = 5;
    public static final int SEND_FROM                                      = 6;
    public static final int SEND_WITH_DATA                                 = 7;
    public static final int SEND_WITH_DATA_FROM                            = 8;
    public static final int LIST_STREAM_PUBLISHERS                         = 9;
    public static final int LIST_PERMISSIONS                               = 10;
    public static final int SEND_RAW_TRANSACTION                           = 11;
    public static final int IMPORT_ADDRESS                                 = 12;
    public static final int LIST_ADDRESSES                                 = 13;
    public static final int GET_BLOCK                                      = 14;
    public static final int GRANT                                          = 15;
    public static final int REVOKE                                         = 16;
    public static final int GET_TOTAL_BALANCES                             = 17;
    public static final int LIST_UNSPENT                                   = 18;

    public static final int GET_BLOCKCHAIN_PARAMS                          = 19;               
    public static final int GET_RUNTIME_PARAMS                             = 20;
    public static final int SET_RUNTIME_PARAM                              = 21;              
    public static final int HELP                                           = 22;
    public static final int STOP                                           = 23;
    public static final int ADD_MULTISIG_ADDRESS                           = 24;           
    public static final int GET_ADDRESSES                                  = 25; 
    public static final int CREATE_KEYPAIRS                                = 26;
    public static final int CREATE_MULTISIG                                = 27;
    public static final int VALIDATE_ADDRESS                               = 28;
    public static final int GRANT_FROM                                     = 29;
    public static final int GRANT_WITH_DATA                                = 30;
    public static final int GRANT_WITH_DATA_FROM                           = 31;
    public static final int REVOKE_FROM                                    = 32;
    public static final int ISSUE                                          = 33;
    public static final int ISSUE_FROM                                     = 34;
    public static final int ISSUE_MORE                                     = 35;
    public static final int ISSUE_MORE_FROM                                = 36;
    public static final int LIST_ASSETS                                    = 37;
//    public static final int GET_ASSET_BALANCES                             = 38;
    public static final int GET_MULTIBALANCES                              = 39;
    public static final int GET_WALLET_TRANSACTION                         = 40;
    public static final int SEND                                           = 41;
    public static final int SEND_ASSET                                     = 42;
    public static final int SEND_ASSET_FROM                                = 43;
    public static final int APPEND_RAW_EXCHANGE                            = 44;
    public static final int COMPLETE_RAW_EXCHANGE                          = 45;
    public static final int CREATE_RAW_EXCHANGE                            = 46;
    public static final int DECODE_RAW_EXCHANGE                            = 47;
    public static final int DISABLE_RAW_TRANSACTION                        = 48;
    public static final int PREPARE_LOCK_UNSPENT                           = 49;
    public static final int PREPARE_LOCK_UNSPENT_FROM                      = 50;
    public static final int CREATE_T_STREAM                                = 51;
    public static final int CREATE_FROM_T_STREAM                           = 52;
    public static final int LIST_STREAMS                                   = 53;
    public static final int PUBLISH                                        = 54;
    public static final int PUBLISH_FROM                                   = 55;
    public static final int SUBSCRIBE                                      = 56;
    public static final int UNSUBSCRIBE                                    = 57;
    public static final int GET_ASSET_TRANSACTION                          = 58;
    public static final int LIST_ASSET_TRANSACTIONS                        = 59;
    public static final int GET_STREAMITEM                                 = 60;
    public static final int GET_TXOUT_DATA                                 = 61;
    public static final int LIST_STREAM_BLOCK_ITEMS                        = 62;
    public static final int LIST_STREAM_KEY_ITEMS                          = 63;
    public static final int LIST_STREAM_KEYS                               = 64;
    public static final int LIST_STREAM_ITEMS                              = 65;
    public static final int LIST_STREAM_PUBLISHER_ITEMS                    = 66;
    public static final int COMBINE_UNSPENT                                = 67;
    public static final int LIST_LOCK_UNSPENT                              = 68;
    public static final int LOCK_UNSPENT                                   = 69;                   
    public static final int APPEND_RAW_CHANGE                              = 70;          
    public static final int APPEND_RAW_DATA                                = 71; 
    public static final int APPEND_RAW_TRANSACTION                         = 72;
    public static final int CREATE_RAW_TRANSACTION                         = 73;
    public static final int CREATE_RAW_SEND_FROM                           = 74;
    public static final int DECODE_RAW_TRANSACTION                         = 75;
    public static final int SIGN_RAW_TRANSACTION                           = 76;
    public static final int ADD_NODE                                       = 77;
    public static final int GET_ADDED_NODE_INFO                            = 78;
    public static final int GET_NETWORK_INFO                               = 79;
    public static final int GET_PEER_INFO                                  = 80;
    public static final int PING                                           = 81;
    public static final int SIGNMESSAGE                                    = 82;
    public static final int VERIFY_MESSAGE                                 = 83;
    public static final int GET_BLOCKCHAIN_INFO                            = 84;
    public static final int GET_BLOCKHASH                                  = 85;                 
    public static final int GET_MEMPOOL_INFO                               = 86;
    public static final int GET_RAW_MEMPOOL                                = 87;
    public static final int GET_RAW_TRANSACTION                            = 88;                     
    public static final int GET_TXOUT                                      = 89;            
    public static final int LIST_BLOCKS                                    = 90;   
    public static final int BACKUP_WALLET                                  = 91;
    public static final int DUMP_PRIVKEY                                   = 92;
    public static final int DUMP_WALLET                                    = 93;
    public static final int ENCRYPT_WALLET                                 = 94;
    public static final int GET_WALLET_INFO                                = 95;
    public static final int IMPORT_PRIVKEY                                 = 96;                     
    public static final int IMPORT_WALLET                                  = 97;            
    public static final int WALLET_LOCK                                    = 98;
    public static final int WALLET_PASSPHRASE                              = 99;                        
    public static final int WALLET_PASSPHRASE_CHANGE                       = 100;               
    public static final int APPROVE_FROM                                   = WALLET_PASSPHRASE_CHANGE + 1;      
    public static final int CREATE_T_UPGRADE                               = APPROVE_FROM + 1;
    public static final int CREATE_FROM_T_UPGRADE                          = CREATE_T_UPGRADE + 1;
    public static final int LIST_UPGRADES                                  = CREATE_FROM_T_UPGRADE + 1;
    public static final int CLEAR_MEMPOOL                                  = LIST_UPGRADES + 1;
    public static final int PAUSE                                          = CLEAR_MEMPOOL + 1;                      
    public static final int RESUME                                         = PAUSE + 1;             
    public static final int SET_LAST_BLOCK                                 = RESUME + 1;   
    
    /** 
     * @code same APIs(methods), another method which same index is implementing instead of
     * public static final int SEND_FROM_ADDRESS                              =SEND_FROM
     * public static final int SEND_WITH_METADATA                             =SEND_WITH_DATA
     * public static final int SEND_WITH_METADATA_FROM                        =SEND_WITH_DATA_FROM
     * public static final int GRANT_WITH_METADATA                            =GRANT_WITH_DATA
     * public static final int GRANT_WITH_METADATA_FROM                       =GRANT_WITH_DATA_FROM
     * public static final int SEND_TO_ADDRESS                                =SEND
     * public static final int SEND_ASSET_TO_ADDRESS                          =SEND_ASSET
     * public static final int APPEND_RAW_METADATA                            =APPEND_RAW_DATA
    */
    public static final int SEND_FROM_ADDRESS                              = 6;//=SEND_FROM
    public static final int SEND_WITH_METADATA                             = 7;//=SEND_WITH_DATA
    public static final int SEND_WITH_METADATA_FROM                        = 8;//=SEND_WITH_DATA_FROM
    public static final int GRANT_WITH_METADATA                            = 30; //=GRANT_WITH_DATA
    public static final int GRANT_WITH_METADATA_FROM                       = 31;//=GRANT_WITH_DATA_FROM
    public static final int SEND_TO_ADDRESS                                = 41;//=SEND
    public static final int SEND_ASSET_TO_ADDRESS                          = 42;//=SEND_ASSET
    public static final int APPEND_RAW_METADATA                            = 71;//=APPEND_RAW_DATA
    /**
     * @endcode
     */
    
    public static final int MAX_INDEX                                      = SET_LAST_BLOCK + 1;
    
    ///////////////////////////////////////////////////////////////////////////////////    
    /**
     * @code block chain APIs String List
     * 
     */
    public static final String[] Methods = {
            "getinfo",
            "getnewaddress",
            "getaddressbalances",
            "getaddresstransaction",
            "listaddresstransactions",
            "listwallettransactions",
            "sendfrom",
            "sendwithdata",
            "sendwithdatafrom",
            "liststreampublishers",
            "listpermissions",
            "sendrawtransaction",
            "importaddress",
            "listaddresses",
            "getblock",
            "grant",
            "revoke",
            "gettotalbalances",
            "listunspent",
            "getblockchainparams",                        
            "getruntimeparams",
            "setruntimeparam",                            
            "help",
            "stop",
            "addmultisigaddress",
            "getaddresses",
            "createkeypairs",
            "createmultisig",                             
            "validateaddress",
            "grantfrom",                                  
            "grantwithdata",
            "grantwithdatafrom", 
            "revokefrom",
            "issue",                               
            "issuefrom",                                  
            "issuemore",
            "issuemorefrom",
            "listassets",
            "getassetbalances",
            "getmultibalances",
            "getwallettransaction",
            "send",
            "sendasset",           
            "sendassetfrom",
            "appendrawexchange",                          
            "completerawexchange",                        
            "createrawexchange",                     
            "decoderawexchange",                          
            "disablerawtransaction",                   
            "preparelockunspent",                         
            "preparelockunspentfrom",                     
            "create",                      
            "createfrom",                                 
            "liststreams",                                
            "publish",                             
            "publishfrom",                                
            "subscribe",                             
            "unsubscribe",                               
            "getassettransaction",                     
            "listassettransactions",                      
            "getstreamitem",                              
            "gettxoutdata",                               
            "liststreamblockitems",                       
            "liststreamkeyitems",                         
            "liststreamkeys",                             
            "liststreamitems",                            
            "liststreampublisheritems",                   
            "combineunspent",                             
            "listlockunspent",
            "lockunspent",                                
            "appendrawchange",                            
            "appendrawdata",         
            "appendrawtransaction",                       
            "createrawtransaction",                       
            "createrawsendfrom",                          
            "decoderawtransaction",                       
            "signrawtransaction",                         
            "addnode",                                    
            "getaddednodeinfo",                           
            "getnetworkinfo",
            "getpeerinfo",
            "ping",                                     
            "signmessage",                                
            "verifymessage",                              
            "getblockchaininfo",
            "getblockhash",                               
            "getmempoolinfo",
            "getrawmempool",
            "getrawtransaction",                          
            "gettxout",                                   
            "listblocks",                                 
            "backupwallet",                               
            "dumpprivkey",                                
            "dumpwallet",                                 
            "encryptwallet",                              
            "getwalletinfo",
            "importprivkey",                              
            "importwallet",                               
            "walletlock",
            "walletpassphrase",                           
            "walletpassphrasechange",                     
            "approvefrom",                                
            "create",                                     
            "createfrom",                                 
            "listupgrades",                               
            "clearmempool",
            "pause",                                     
            "resume",                              
            "setlastblock"
            
    };
    /**
     * @endcode
     */

    
    /**
     * @code Reference to parameter information type corresponding to blockchain API (index)
     *  : Params[ RPC API index ]
     */
    public static final String[][] Params = {
            null,                                                                     //"getinfo"
            null,                                                                     //"getnewaddress"
            {"address", "(minconf=1)", "(includeLocked=false)"},                      //"getaddressbalances"
            {"address", "txid",  "(verbose=false)"},                                          //"getaddresstransaction"
            {"address", "(count=10)", "(skip=0)", "(verbose=false)"},                                  //"listaddresstransactions"
            {"(count=10)", "(skip=0)", "(includeWatchOnly=false)", "(verbose=false)"},                         //"listwallettransactions"
            {"from-address", "to-address", "amount", "(comment)", "(comment-to)"},        //"sendfrom"
            {"address", "amount", "data-hex|object"},                                        //"sendwithdata"
            {"from-address", "to-address", "amount", "data-hex|object"},                     //"sendwithdatafrom"
            {"stream", "(addresses=*)", "(verbose=false)", "(count=MAX)", "(start=-count)", "(local-ordering=false)"},   //"liststreampublishers"
            {"(permissions=*)", "(addresses=*)", "(verbose=false)"},                                  //"listpermissions"
            {"tx-hex"},                                                               //"sendrawtransaction"
            {"address(es)", "(label)", "(rescan=true)"},                                           //"importaddress"
            {"(addresses=*)", "(verbose=false)", "(count=MAX)", "(start=-count)"},                               //"listaddresses"
            {"hash|height", "(verbose=1)"},                                                      //"getblock"
            {"addresses", "permissions", "(native-amount=0)", "(start-block)", "(end-block)", "(comment)", "(comment-to)"},         //"grant"
            {"addresses", "permissions", "(native-amount=0)", "(comment)", "(comment-to)"},   //"revoke"
            {"(minconf=1)", "(includeWatchOnly=false)", "(includeLocked=false)"},                         //"gettotalbalances"
            {"(minconf=1)", "(maxconf=999999)", "([\"address\", ...])"},                                      //"listunspent"
            
            
            {"(display-names=true)", "(with-upgrades=true)"},//"getblockchainparams",
            null,//"getruntimeparams",
            {"param", "value"},//"setruntimeparam",
            null,//"help",
            null,//"stop",
            {"nrequired", "[\"key\", ...]"},//"addmultisigaddress",
            {"(verbose=false)"},//"getaddresses",
            {"(count=1)"},//"createkeypairs",
            {"nrequired", "[\"key\", ...]"},//"createmultisig",
            {"address|privkey|pubkey"},//"validateaddress",
            {"from-address", "to-addresses", "permissions", "(native-amount=0)", "(start-block)", "(end-block)", "(comment)", "(comment-to)"},//"grantfrom",
            {"addresses", "permissions", "data-hex|object", "(native-amount=0)", "(start-block)", "(end-block)"},//"grantwithdata",
            {"from-address", "to-addresses", "permissions", "data-hex|object", "(native-amount=0)", "(start-block)", "(end-block)"},//"grantwithdatafrom",
            {"from-address", "to-addresses", "permissions", "(native-amount=0)", "(comment)", "(comment-to)"},//"revokefrom",
            {"address", "name|params", "qty", "(units=1)", "(native-amount=min-per-output)", "({\"custom-field-1\":\"x\",...})"},//"issue",
            {"from-address", "to-address", "name|params", "qty", "(units=1)", "(native-amount=min-per-output)", "({\"custom-field-1\":\"x\",...})"},//"issuefrom",
            {"address", "asset", "qty", "(native-amount=min-per-output)", "({\"custom-field-1\":\"x\",...})"},//"issuemore",
            {"from-address", "to-address", "asset", "qty", "(native-amount=min-per-output)", "({\"custom-field-1\":\"x\",...})"},//"issuemorefrom",
            {"(assets=*)", "(verbose=false)", "(count=MAX)", "(start=-count)"},//"listassets",
            {"(account=&quot;&quo)", "(minconf=1)", "(includeWatchOnly=false)", "(includeLocked=false)"},//"getassetbalances",
            {"(addresses=*)", "(assets=*)", "(minconf=1)", "(includeWatchOnly=false)", "(includeLocked=false)"},//"getmultibalances",
            {"txid", "(includeWatchOnly=false)", "(verbose=false)"},//"getwallettransaction",
            {"address", "amount", "(comment)", "(comment-to)"},//"send",
            {"address", "asset", "qty", "(native-amount=min-per-output)", "(comment)", "(comment-to)"},//"sendasset",   
            {"from-address", "to-address", "asset", "qty", "(native-amount=min-per-output)", "(comment)", "(comment-to)"},//"sendassetfrom",
            {"tx-hex", "txid", "vout", "{\"asset\":qty, ...}"},//"appendrawexchange",  
            {"tx-hex", "txid", "vout", "{\"asset\":qty, ...}", "(data-hex|object)"},//"completerawexchange",  
            {"txid", "vout", "{\"asset\":qty, ...}"},//"createrawexchange",    
            {"tx-hex", "(verbose=false)"},//"decoderawexchange", 
            {"tx-hex"},//"disablerawtransaction",  
            {"{\"asset\":qty, ...}", "(lock=true)"},//"preparelockunspent",  
            {"from-address", "{\"asset\":qty, ...}", "(lock=true)"},//"preparelockunspentfrom",   
            {"type=stream", "name", "open", "({\"custom-field-1\":\"x\",...})"},//"create",        
            {"from-address", "type=stream", "name", "open", "({\"custom-field-1\":\"x\",...})"},//"createfrom",  
            {"(streams=*)", "(verbose=false)", "(count=MAX)", "(start=-count)"},//"liststreams",  
            {"stream", "key", "data-hex"},//"publish", 
            {"from-address", "stream", "key", "data-hex"},//"publishfrom",  
            {"asset(s)|stream(s)", "(rescan=true)"},//"subscribe", 
            {"asset(s)|stream(s)"},//"unsubscribe",  
            {"asset", "txid", "(verbose=false)"},//"getassettransaction", 
            {"asset", "(verbose=false)", "(count=10)", "(start=-count)", "(local-ordering=false)"},//"listassettransactions", 
            {"stream", "txid", "(verbose=false)"},//"getstreamitem",  
            {"txid", "vout", "(count-bytes=INT_MAX)", "(start-byte=0)"},//"gettxoutdata",
            {"stream", "blocks", "(verbose=false)", "(count=MAX)", "(start=-count)"},//"liststreamblockitems", 
            {"stream", "key", "(verbose=false)", "(count=10)", "(start=-count)", "(local-ordering=false)"},//"liststreamkeyitems",  
            {"stream", "(keys=*)", "(verbose=false)", "(count=MAX)", "(start=-count)", "(local-ordering=false)"},//"liststreamkeys",  
            {"stream", "(verbose=false)", "(count=10)", "(start=-count)", "(local-ordering=false)"},//"liststreamitems", 
            {"stream", "address", "(verbose=false)", "(count=10)", "(start=-count)", "(local-ordering=false)"},//"liststreampublisheritems", 
            {"(addresses=*)", "(minconf=1)", "(maxcombines=100)", "(mininputs=2)", "(maxinputs=100)", "(maxtime=15)"},//"combineunspent",                             
            null,//"listlockunspent",
            {"unlock", "([{\"txid\":\"id\",\"vout\":n},...])"},//"lockunspent",                                
            {"tx-hex", "address", "(native-fee)"},//"appendrawchange",                            
            {"tx-hex", "data-hex|object"},//"appendrawdata",         
            {"tx-hex", "[{\"txid\":\"id\",\"vout\":n},...]", "({\"address\":amount,...})", "(data=[])", "(action=\"\")"},//"appendrawtransaction",                       
            {"[{\"txid\":\"id\",\"vout\":n},...]", "{\"address\":amount,...}", "(data=[])", "(action=\"\")"},//"createrawtransaction",                       
            {"from-address", "{\"to-address\":amount,...}", "(data=[])", "(action=\"\")"},//"createrawsendfrom",                          
            {"tx-hex"},//"decoderawtransaction",                       
            {"tx-hex", "([{parent-output},...])", "([\"private-key\",...])", "(sighashtype=ALL)"},//"signrawtransaction",                         
            {"ip(:port)", "command"},//"addnode",                                    
            {"verbose", "(ip(:port))"},//"getaddednodeinfo",                           
            null,//"getnetworkinfo",                             
            null,//"getpeerinfo",
            null,//"ping"
            {"address|privkey", "message"},//"signmessage",                                
            {"address signature", "message"},//"verifymessage",                              
            null,//"getblockchaininfo",
            {"height"},//"getblockhash",                               
            null,//"getmempoolinfo",
            null,//"getrawmempool",
            {"txid", "(verbose=0)"},//"getrawtransaction",                          
            {"txid", "vout", "(unconfirmed=false)"},//"gettxout",                                   
            {"blocks", "(verbose=false)"},//"listblocks",                                 
            {"filename"},//"backupwallet",                               
            {"address"},//"dumpprivkey",                                
            {"filename"},//"dumpwallet",                                 
            {"passphrase"},//"encryptwallet",                              
            null,//"getwalletinfo",
            {"privkey(s)", "(label)", "(rescan=true)"},//"importprivkey",                              
            {"filename"},//"importwallet",                               
            null,//"walletlock",
            {"passphrase", "timeout"},//"walletpassphrase",                           
            {"old-passphrase", "new-passphrase"},//"walletpassphrasechange",                     
            {"from-address", "upgrade", "approve"},//"approvefrom",                                
            {"type=upgrade", "name", "open=false", "{\"protocol-version\":100xx}"},//"create",                                     
            {"from-address", "type=upgrade", "name", "open=false", "{\"protocol-version\":100xx}"},//"createfrom",                                 
            {"(upgrades=*)"},//"listupgrades",                               
            null,//"clearmempool",
            {"tasks"},//"pause",                                     
            {"tasks"},//"resume",                              
            {"hash|height"}//"setlastblock"
            
    };
    ///////////////////////////////////////////////////////////////////////////////////
    /**
     * @endcode
     */
    
    /**
     * @brief Output of api name character corresponding to api id
     * @param mid api id
     * @return api name
     * @throws HdacException
     */
    public static String getMethodName(int mid) throws HdacException {
    	return getMethodNameByMId(mid);
    }
    
    /**
     * @brief Outputs parameter description corresponding to api id
     * @param mid api id
     * @return Parameter Description
     * @throws HdacException
     */
    public static String[] getParamArray(int mid) throws HdacException {
    	return getParamArrayByMId(mid);
    }
    //
    
    ///Dev api
    /**
     * @brief Output of api name character corresponding to api id
     * @param mid api id
     * @return api name
     * @throws HdacException
     */
    public static String getMethodNameByMId(int mid) throws HdacException {
    	if(mid >= MAX_INDEX || mid < 0) throw new HdacException(0);
    	return Methods[mid];
    }
    
    /**
     * @brief Outputs parameter description corresponding to api name
     * @param mname api name
     * @return Parameter Description
     * @throws HdacException
     * 
     */
    public static String[] getParamArrayByMName(String mname) throws HdacException {
    	if(mname==null||mname.isEmpty()) return null;
    	
    	for(int i = 0; i < MAX_INDEX; i++) {
    		if(mname.equals(Methods[i])) return Params[i];
    	}
    	throw new HdacException(0);
    }
    
    /**
     * @brief The number of parameters corresponding to api id
     * @param mid api id
     * @return The number of parameters
     */
    public static int sizeOfParamsByMId(int mid){
    	String[] params;
		try {
			params = getParamArrayByMId(mid);
			if(params!=null) return params.length;
		} catch (HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	return 0;
    }
    
    /**
     * @brief Outputs parameter description corresponding to api id
     * @param mid api id
     * @return Parameter description
     * @throws HdacException
     */
    public static String[] getParamArrayByMId(int mid) throws HdacException {
    	if(mid >= MAX_INDEX || mid < 0) throw new HdacException(0);
    	return Params[mid];
    }
    //

}
