package com.hdacSdk.hdacCoreApi;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @brief Supporting for blockchain APIs as java functions
 * @details Blockchain RPC APIs can be called as individual java functions \n
 * It is a class to support blockchain APIs in java function form to process RPC response through HdacRpcClient \n
 * @class HdacCommand
 * @date 2018-01-31.
 * @author Hdac Technology 
 *
 */
public class HdacCommand {
	
	public HdacRpcClient hdacRpcClient = null;
	
	public HdacCommand(HdacRpcClient hrc) throws CommandException {
		hdacRpcClient = hrc;
	}
	
	public void setRpcHandler(RpcHandler handler) throws CommandException {
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		hdacRpcClient.setRPCHandler(handler);
	}
	
	//block chain RPC APIs
	//start
	
	/**
	 * @brief block chain api getblock
	 * @param hashOrHeight
	 * @param verbose
	 * @throws CommandException
	 */
	public void getblock(@Nonnull String hashOrHeight, @Nullable String verbose) throws CommandException {
		//{"hash|height", "(verbose=1)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(hashOrHeight==null||hashOrHeight.isEmpty()) throw new CommandException("hash|Height", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_BLOCK, hashOrHeight, verbose);
	}
	
	/**
	 * @brief block chain api getinfo
	 * @throws CommandException
	 */
    public void getinfo() throws CommandException {
    	if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
    	
    	hdacRpcClient.sendCommand(CommandUtils.GET_INFO);   	
		
	}
    
    /**
     * @brief block chain api getnewaddress
     * @throws CommandException
     */
    public void getnewaddress() throws CommandException {
    	if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
    	
    	hdacRpcClient.sendCommand(CommandUtils.GET_NEW_ADDRESS);   	
	}
     
    /**
     * @brief block chain api getaddressbalances
     * @param address
     * @param minconf
     * @param includeLocked
     * @throws CommandException
     */
    public void getaddressbalances(@Nonnull String address, @Nullable String minconf, @Nullable String includeLocked) throws CommandException {
    	//{"address", "(minconf=1)", "(includeLocked=false)"}, 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_ADDRESS_BALANCES, address, minconf, includeLocked);
	}
    
    /**
     * @brief block chain api getaddresstransaction
     * @param address
     * @param txid
     * @param verbose
     * @throws CommandException
     */
    public void getaddresstransaction(@Nonnull String address, @Nonnull String txid, @Nullable String verbose) throws CommandException {
    	//{"address", "txid",  "(verbose=false)"},  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_ADDRESS_TRANSACTION, address, txid, verbose);
	}
    
    /**
     * @brief block chain api listaddresstransactions
     * @param address
     * @param count
     * @param skip
     * @param verbose
     * @throws CommandException
     */
    public void listaddresstransactions(@Nonnull String address, @Nullable String count, @Nullable String skip, @Nullable String verbose) throws CommandException {
    	//{"address", "(count=10)", "(skip=0)", "(verbose=false)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
				
		hdacRpcClient.sendCommand(CommandUtils.LIST_ADDRESS_TRANSACTIONS, address, count, skip, verbose);
	}
    
    /**
     * @brief block chain api listwallettransactions
     * @param count
     * @param skip
     * @param includeWatchOnly
     * @param verbose
     * @throws CommandException
     */
    public void listwallettransactions(@Nullable String count, @Nullable String skip, @Nullable String includeWatchOnly, @Nullable String verbose) throws CommandException {
        //{"(count=10)", "(skip=0)", "(includeWatchOnly=false)", "(verbose=false)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_WALLET_TRANSACTIONS, count, skip, includeWatchOnly, verbose);
	}
	
    /**
     * @brief block chain api sendfrom
     * @param fromAddress
     * @param toAddress
     * @param amount
     * @param comment
     * @param commentTo
     * @throws CommandException
     */
    public void sendfrom(@Nonnull String fromAddress, @Nonnull String toAddress, @Nonnull String amount, @Nullable String comment, @Nullable String commentTo) throws CommandException {
    	//{"from-address", "to-address", "amount", "(comment)", "(comment-to)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(fromAddress==null||fromAddress.isEmpty()) throw new CommandException("fromAddress", 0);
		if(toAddress==null||toAddress.isEmpty()) throw new CommandException("toAddress", 0);
		if(amount==null||amount.isEmpty()) throw new CommandException("amount", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SEND_FROM, fromAddress, toAddress, amount, comment, commentTo);
	}
    
    /**
     * @brief block chain api sendfromaddress
     * @param fromAddress
     * @param toAddress
     * @param amount
     * @param comment
     * @param commentTo
     * @throws CommandException
     */
    public void sendfromaddress(@Nonnull String fromAddress, @Nonnull String toAddress, @Nonnull String amount, @Nullable String comment, @Nullable String commentTo) throws CommandException {
    	//{"from-address", "to-address", "amount", "(comment)", "(comment-to)"},
		sendfrom(fromAddress, toAddress, amount, comment, commentTo);
	}
    
    /**
     * @brief block chain api sendwithdata
     * @param address
     * @param amount
     * @param dataHex
     * @throws CommandException
     */
    public void sendwithdata(@Nonnull String address, @Nonnull String amount, @Nonnull String dataHex) throws CommandException {
    	//    {"address", "amount", "data-hex|object"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		if(amount==null||amount.isEmpty()) throw new CommandException("amount", 0);
		if(dataHex==null||dataHex.isEmpty()) throw new CommandException("dataHex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SEND_WITH_DATA, address, amount, dataHex);
	}
    
    /**
     * @brief block chain api sendwithmetadata
     * @param address
     * @param amount
     * @param dataHex
     * @throws CommandException
     */
    public void sendwithmetadata(@Nonnull String address, @Nonnull String amount, @Nonnull String dataHex) throws CommandException {
    	//    {"address", "amount", "data-hex|object"},		
    	sendwithdata(address, amount, dataHex);
	}
    
    /**
     * @brief block chain api sendwithdatafrom
     * @param fromAddress
     * @param toAddress
     * @param amount
     * @param dataHex
     * @throws CommandException
     */
    public void sendwithdatafrom(@Nonnull String fromAddress, @Nonnull String toAddress, @Nonnull String amount, @Nonnull String dataHex) throws CommandException {
    	//{"from-address", "to-address", "amount", "data-hex|object"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(fromAddress==null||fromAddress.isEmpty()) throw new CommandException("fromAddress", 0);
		if(toAddress==null||toAddress.isEmpty()) throw new CommandException("toAddress", 0);
		if(amount==null||amount.isEmpty()) throw new CommandException("amount", 0);
		if(dataHex==null||dataHex.isEmpty()) throw new CommandException("dataHex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SEND_WITH_DATA_FROM, fromAddress, toAddress, amount, dataHex);
	}
    
    /**
     * @brief block chain api
     * @param fromAddress
     * @param toAddress
     * @param amount
     * @param dataHex
     * @throws CommandException
     */
    public void sendwithmetadatafrom(@Nonnull String fromAddress, @Nonnull String toAddress, @Nonnull String amount, @Nonnull String dataHex) throws CommandException {
    	//{"from-address", "to-address", "amount", "data-hex|object"},		
		sendwithdatafrom(fromAddress, toAddress, amount, dataHex);
	}
    
    /**
     * @brief block chain api liststreampublishers
     * @param stream
     * @param addresses
     * @param verbose
     * @param count
     * @param start
     * @param localOrdering
     * @throws CommandException
     */
    public void liststreampublishers(@Nonnull String stream, @Nullable String addresses, @Nullable String verbose, @Nullable String count, 
    		@Nullable String start, @Nullable String localOrdering) throws CommandException {
		//    {"stream", "(addresses=*)", "(verbose=false)", "(count=MAX)", "(start=-count)", "(local-ordering=false)"},
    	if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_STREAM_PUBLISHERS, stream, addresses, verbose, count, start, localOrdering);
	}
    
    /**
     * @brief block chain api listpermissions
     * @param permissions
     * @param addresses
     * @param verbose
     * @throws CommandException
     */
    public void listpermissions(@Nullable String permissions, @Nullable String addresses, @Nullable String verbose) throws CommandException {
    	//{"(permissions=*)", "(addresses=*)", "(verbose=false)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
				
		hdacRpcClient.sendCommand(CommandUtils.LIST_PERMISSIONS, permissions, addresses, verbose);
	}
    
    /**
     * @brief block chain api sendrawtransaction
     * @param txHex
     * @throws CommandException
     */
    public void sendrawtransaction(@Nonnull String txHex) throws CommandException {
    	//{"tx-hex"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(txHex==null||txHex.isEmpty()) throw new CommandException("tx-hex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SEND_RAW_TRANSACTION, txHex);
	}
    
    /**
     * @brief block chain api importaddress
     * @param addresses
     * @param label
     * @param rescan
     * @throws CommandException
     */
    public void importaddress(@Nonnull String addresses, @Nullable String label, @Nullable String rescan) throws CommandException {
    	//{"address(es)", "(label)", "(rescan=true)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(addresses==null||addresses.isEmpty()) throw new CommandException("addresses", 0);
				
		hdacRpcClient.sendCommand(CommandUtils.IMPORT_ADDRESS, addresses, label, rescan);
	}
    
    /**
     * @brief block chain api listaddresses
     * @param addresses
     * @param verbose
     * @param count
     * @param start
     * @throws CommandException
     */
    public void listaddresses(@Nullable String addresses, @Nullable String verbose, @Nullable String count, @Nullable String start) throws CommandException {
    	//{"(addresses=*)", "(verbose=false)", "(count=MAX)", "(start=-count)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_ADDRESSES, addresses, verbose, count, start);
	}
    
    /**
     * @brief block chain api grant
     * @param addresses
     * @param permissions
     * @param nativeAmount
     * @param startBlock
     * @param endBlock
     * @param comment
     * @param commentTo
     * @throws CommandException
     */
    public void grant(@Nonnull String addresses, @Nonnull String permissions, @Nullable String nativeAmount, @Nullable String startBlock, @Nullable String endBlock, @Nullable String comment, @Nullable String commentTo) throws CommandException {
        //{"addresses", "permissions", "(native-amount=0)", "(start-block)", "(end-block)", "(comment)", "(comment-to)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(addresses==null||addresses.isEmpty()) throw new CommandException("addresses", 0);
		if(permissions==null||permissions.isEmpty()) throw new CommandException("permissions", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GRANT, addresses, permissions, nativeAmount, startBlock, endBlock, comment, commentTo);
	}
    
    /**
     * @brief block chain api revoke
     * @param addresses
     * @param permissions
     * @param nativeAmount
     * @param comment
     * @param commentTo
     * @throws CommandException
     */
    public void revoke(@Nonnull String addresses, @Nonnull String permissions, @Nullable String nativeAmount, @Nullable String comment, @Nullable String commentTo) throws CommandException {
    	//{"addresses", "permissions", "(native-amount=0)", "(comment)", "(comment-to)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(addresses==null||addresses.isEmpty()) throw new CommandException("addresses", 0);
		if(permissions==null||permissions.isEmpty()) throw new CommandException("permissions", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.REVOKE, addresses, permissions, nativeAmount, comment, commentTo);
	}
    
    /**
     * @brief block chain api gettotalbalances
     * @param minconf
     * @param includeWatchOnly
     * @param includeLocked
     * @throws CommandException
     */
    public void gettotalbalances(@Nullable String minconf, @Nullable String includeWatchOnly, @Nullable String includeLocked) throws CommandException {
    	//{"(minconf=1)", "(includeWatchOnly=false)", "(includeLocked=false)"},
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
				
		hdacRpcClient.sendCommand(CommandUtils.GET_TOTAL_BALANCES, minconf, includeWatchOnly, includeLocked);
	}
    
    /**
     * @brief block chain api listunspent
     * @param minconf
     * @param maxconf
     * @param addresses
     * @throws CommandException
     */
    public void listunspent(@Nullable String minconf, @Nullable String maxconf, @Nullable JSONArray addresses) throws CommandException {
    	//{"(minconf=1)", "(maxconf=999999)", "([\"address\", ...])"},
        if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
        
        hdacRpcClient.sendCommand(CommandUtils.LIST_UNSPENT, minconf, maxconf, (addresses!=null)?addresses.toString():null);    	
    }
    
    /**
     * @brief block chain api getblockchainparams
     * @param display_names
     * @param with_upgrades
     * @throws CommandException
     */
    public void getblockchainparams (@Nullable String display_names, @Nullable String with_upgrades) throws CommandException {
    	//(display-names=true) (with-upgrades=true)//"getblockchainparams",
    	if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
    	
    	hdacRpcClient.sendCommand(CommandUtils.GET_BLOCKCHAIN_PARAMS, display_names, with_upgrades);
	}
	
    /**
     * @brief block chain api getruntimeparams
     * @throws CommandException
     */
    public void getruntimeparams () throws CommandException {
	//null,//"getruntimeparams",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_RUNTIME_PARAMS);
	}
	
    /**
     * @brief block chain api setruntimeparam
     * @param param
     * @param value
     * @throws CommandException
     */
    public void setruntimeparam (@Nonnull String param, @Nonnull String value) throws CommandException {
	//param value//"setruntimeparam",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(param==null||param.isEmpty()) throw new CommandException("param", 0);
		if(value==null||value.isEmpty()) throw new CommandException("value", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SET_RUNTIME_PARAM, param, value);
	}
	
    /**
     * @brief block chain api help
     * @throws CommandException
     */
    public void help () throws CommandException {
	//null,//"help",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.HELP);
	}
	
    /**
     * @brief block chain api stop
     * @throws CommandException
     */
    public void stop () throws CommandException {
	//null,//"stop",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.STOP);
	}
	
    /**
     * @brief block chain api addmultisigaddress
     * @param nrequired
     * @param key
     * @throws CommandException
     */
    public void addmultisigaddress (@Nonnull String nrequired, @Nonnull JSONArray key) throws CommandException {
	//nrequired ["key", ...]//"addmultisigaddress",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(nrequired==null||nrequired.isEmpty()) throw new CommandException("nrequired", 0);
		if(key==null) throw new CommandException("key", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.ADD_MULTISIG_ADDRESS, nrequired, key.toString());
	}
	
    /**
     * @brief block chain api getaddresses
     * @param verbose
     * @throws CommandException
     */
    public void getaddresses (@Nullable String verbose) throws CommandException {
	//(verbose=false)//"getaddresses",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_ADDRESSES, verbose);
	}
	
    /**
     * @brief block chain api createkeypairs
     * @param count
     * @throws CommandException
     */
    public void createkeypairs (@Nullable String count) throws CommandException {
	//(count=1)//"createkeypairs",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.CREATE_KEYPAIRS, count);
	}
	
    /**
     * @brief block chain api createmultisig
     * @param nrequired
     * @param key
     * @throws CommandException
     */
    public void createmultisig (@Nonnull String nrequired, @Nonnull JSONArray key) throws CommandException {
	//nrequired ["key", ...]//"createmultisig",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(nrequired==null||nrequired.isEmpty()) throw new CommandException("nrequired", 0);
		if(key==null) throw new CommandException("key", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.CREATE_MULTISIG, nrequired, key.toString());
	}
	
    /**
     * @brief block chain api validateaddress
     * @param address
     * @throws CommandException
     */
    public void validateaddress (@Nonnull String address) throws CommandException {
	//address| privkey|pubkey//"validateaddress",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.VALIDATE_ADDRESS, address);
	}
	
    /**
     * @brief block chain api grantfrom
     * @param from_address
     * @param to_addresses
     * @param permissions
     * @param native_amount
     * @param start_block
     * @param end_block
     * @param comment
     * @param comment_to
     * @throws CommandException
     */
    public void grantfrom (@Nonnull String from_address, @Nonnull String to_addresses, @Nonnull String permissions, @Nullable String native_amount, 
            @Nullable String start_block, @Nullable String end_block, @Nullable String comment, @Nullable String comment_to) throws CommandException {
	//from-address to-addresses permissions (native-amount=0) (start-block) (end-block) (comment) (comment-to)//"grantfrom",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(to_addresses==null||to_addresses.isEmpty()) throw new CommandException("to_addresses", 0);
		if(permissions==null||permissions.isEmpty()) throw new CommandException("permissions", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GRANT_FROM, from_address, to_addresses, permissions, native_amount, start_block, end_block, comment, comment_to);
	}
	
    /**
     * @brief block chain api grantwithdata
     * @param addresses
     * @param permissions
     * @param data_hex
     * @param native_amount
     * @param start_block
     * @param end_block
     * @throws CommandException
     */
    public void grantwithdata (@Nonnull String addresses, @Nonnull String permissions, @Nonnull String data_hex, @Nullable String native_amount,
            @Nullable String start_block, @Nullable String end_block) throws CommandException {
	//addresses permissions data-hex|object (native-amount=0) (start-block) (end-block)//"grantwithdata",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(addresses==null||addresses.isEmpty()) throw new CommandException("addresses", 0);
		if(permissions==null||permissions.isEmpty()) throw new CommandException("permissions", 0);
		if(data_hex==null||data_hex.isEmpty()) throw new CommandException("data_hex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GRANT_WITH_DATA, addresses, permissions, data_hex, native_amount, start_block, end_block);
		
	}
    
    /**
     * @brief block chain api grantwithmetadata
     * @param addresses
     * @param permissions
     * @param data_hex
     * @param native_amount
     * @param start_block
     * @param end_block
     * @throws CommandException
     */
    public void grantwithmetadata (@Nonnull String addresses, @Nonnull String permissions, @Nonnull String data_hex, @Nullable String native_amount,
            @Nullable String start_block, @Nullable String end_block) throws CommandException {
	//addresses permissions data-hex|object (native-amount=0) (start-block) (end-block)//"grantwithdata",
		grantwithdata(addresses, permissions, data_hex, native_amount, start_block, end_block);
		
	}
	
    /**
     * @brief block chain api grantwithdatafrom
     * @param from_address
     * @param to_addresses
     * @param permissions
     * @param data_hex
     * @param native_amount
     * @param start_block
     * @param end_block
     * @throws CommandException
     */
    public void grantwithdatafrom (@Nonnull String from_address, @Nonnull String to_addresses, @Nonnull String permissions, @Nonnull String data_hex, 
            @Nullable String native_amount, @Nullable String start_block, @Nullable String end_block) throws CommandException {
	//from-address to-addresses permissions data-hex|object (native-amount=0) (start-block) (end-block)//"grantwithdatafrom",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(to_addresses==null||to_addresses.isEmpty()) throw new CommandException("to_addresses", 0);
		if(permissions==null||permissions.isEmpty()) throw new CommandException("permissions", 0);
		if(data_hex==null||data_hex.isEmpty()) throw new CommandException("data_hex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GRANT_WITH_DATA_FROM, from_address, to_addresses, permissions, data_hex, native_amount, start_block, end_block);
	}
    
    /**
     * @brief block chain api grantwithmetadatafrom
     * @param from_address
     * @param to_addresses
     * @param permissions
     * @param data_hex
     * @param native_amount
     * @param start_block
     * @param end_block
     * @throws CommandException
     */
    public void grantwithmetadatafrom (@Nonnull String from_address, @Nonnull String to_addresses, @Nonnull String permissions, @Nonnull String data_hex, 
            @Nullable String native_amount, @Nullable String start_block, @Nullable String end_block) throws CommandException {
	//from-address to-addresses permissions data-hex|object (native-amount=0) (start-block) (end-block)//"grantwithdatafrom",
		grantwithdatafrom(from_address, to_addresses, permissions, data_hex, native_amount, start_block, end_block);
	}
	
    /**
     * @brief block chain api revokefrom
     * @param from_address
     * @param to_addresses
     * @param permissions
     * @param native_amount
     * @param comment
     * @param comment_to
     * @throws CommandException
     */
    public void revokefrom (@Nonnull String from_address, @Nonnull String to_addresses, @Nonnull String permissions,
            @Nullable String native_amount, @Nullable String comment, @Nullable String comment_to) throws CommandException {
	//from-address to-addresses permissions (native-amount=0) (comment) (comment-to)//"revokefrom",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(to_addresses==null||to_addresses.isEmpty()) throw new CommandException("to_addresses", 0);
		if(permissions==null||permissions.isEmpty()) throw new CommandException("permissions", 0);
				
		hdacRpcClient.sendCommand(CommandUtils.REVOKE_FROM, from_address, to_addresses, permissions, native_amount, comment, comment_to);
	}
	
    /**
     * @brief block chain api issue
     * @param address
     * @param name
     * @param qty
     * @param units
     * @param native_amount
     * @param custom_field
     * @throws CommandException
     */
    public void issue (@Nonnull String address, @Nonnull String name, @Nonnull String qty,
    		@Nullable String units, @Nullable String native_amount, @Nullable JSONObject custom_field) throws CommandException {
	//address name|params qty (units=1) (native-amount=min-per-output) ({"custom-field-1":"x",...})//"issue",
    	String customField = null;
	if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
	if(address==null||address.isEmpty()) throw new CommandException("address", 0);
	if(name==null||name.isEmpty()) throw new CommandException("name", 0);		
	if(custom_field!=null) customField = custom_field.toString();
			
	hdacRpcClient.sendCommand(CommandUtils.ISSUE, address, name, qty, units, native_amount, customField);
    }
	
    /**
     * @brief block chain api issuefrom
     * @param from_address
     * @param to_address
     * @param name
     * @param qty
     * @param units
     * @param native_amount
     * @param custom_field
     * @throws CommandException
     */
    public void issuefrom (@Nonnull String from_address, @Nonnull String to_address, @Nonnull String name, @Nonnull String qty,
    		@Nullable String units, @Nullable String native_amount, @Nullable JSONObject custom_field) throws CommandException {
	//from-address to-address name|params qty (units=1) (native-amount=min-per-output) ({"custom-field-1":"x",...})//"issuefrom",
	if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
	if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
	if(to_address==null||to_address.isEmpty()) throw new CommandException("to_address", 0);
	if(name==null||name.isEmpty()) throw new CommandException("name", 0);
	if(qty==null||qty.isEmpty()) throw new CommandException("qty", 0);	
		
	hdacRpcClient.sendCommand(CommandUtils.ISSUE_FROM, from_address, to_address, name, qty, units, native_amount, custom_field!=null?custom_field.toString():null);
    }
	
    /**
     * @brief block chain api issuemore
     * @param address
     * @param asset
     * @param qty
     * @param native_amount
     * @param custom_field
     * @throws CommandException
     */
    public void issuemore (@Nonnull String address, @Nonnull String asset, @Nonnull String qty, @Nullable String native_amount, @Nullable JSONObject custom_field) throws CommandException {
	//address asset qty (native-amount=min-per-output) ({"custom-field-1":"x",...})//"issuemore",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		if(asset==null||asset.isEmpty()) throw new CommandException("asset", 0);	
		if(qty==null||qty.isEmpty()) throw new CommandException("qty", 0);	
		
		hdacRpcClient.sendCommand(CommandUtils.ISSUE_MORE, address, asset, qty, native_amount, custom_field.toString());
	}
	
    /**
     * @brief block chain api issuemorefrom
     * @param from_address
     * @param to_address
     * @param asset
     * @param qty
     * @param native_amount
     * @param custom_field
     * @throws CommandException
     */
    public void issuemorefrom (@Nonnull String from_address, @Nonnull String to_address, @Nonnull String asset, @Nullable String qty, @Nullable String native_amount, @Nullable JSONObject custom_field) throws CommandException {
	//from-address to-address asset qty (native-amount=min-per-output) ({"custom-field-1":"x",...})//"issuemorefrom",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(to_address==null||to_address.isEmpty()) throw new CommandException("to_address", 0);
		if(asset==null||asset.isEmpty()) throw new CommandException("asset", 0);
				
		hdacRpcClient.sendCommand(CommandUtils.ISSUE_MORE_FROM, from_address, to_address, asset, qty, native_amount, custom_field.toString());
	}
	
    /**
     * @brief block chain api listassets
     * @param assets
     * @param verbose
     * @param count
     * @param start
     * @throws CommandException
     */
    public void listassets (@Nullable String assets, @Nullable String verbose, @Nullable String count, @Nullable String start) throws CommandException {
	//(assets=*) (verbose=false) (count=MAX) (start=-count)//"listassets",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_ASSETS, assets, verbose, count, start);
	}
	
    public void getassetbalances (
    		@Nullable String account, @Nullable String minconf, @Nullable String includeWatchOnly, @Nullable String includeLocked) throws CommandException {
        //(account=&quot;&quot;) (minconf=1) (includeWatchOnly=false) (includeLocked=false)//"getassetbalances",
	if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 0);
		
	hdacRpcClient.sendCommand(CommandUtils.GET_ASSET_BALANCES, account, minconf, includeWatchOnly, includeLocked);
    }
	
    /**
     * @brief block chain api getmultibalances
     * @param addresses
     * @param assets
     * @param minconf
     * @param includeWatchOnly
     * @param includeLocked
     * @throws CommandException
     */
    public void getmultibalances (
    		@Nullable String addresses, @Nullable String assets, @Nullable String minconf, @Nullable String includeWatchOnly, @Nullable String includeLocked) throws CommandException {
	//(addresses=*)</br>(assets=*)</br>(minconf=1) (includeWatchOnly=false) (includeLocked=false)//"getmultibalances",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_MULTIBALANCES, addresses, assets, minconf, includeWatchOnly, includeLocked);
	}
	
    /**
     * @brief block chain api getwallettransaction
     * @param txid
     * @param includeWatchOnly
     * @param verbose
     * @throws CommandException
     */
    public void getwallettransaction (@Nonnull String txid, @Nullable String includeWatchOnly, @Nullable String verbose) throws CommandException {
	//txid (includeWatchOnly=false) (verbose=false)//"getwallettransaction",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_WALLET_TRANSACTION, txid, includeWatchOnly, verbose);
	}
	
    /**
     * @brief block chain api send
     * @param address
     * @param amount
     * @param comment
     * @param comment_to
     * @throws CommandException
     */
	public void send (@Nonnull String address, @Nonnull String amount, @Nullable String comment, @Nullable String comment_to) throws CommandException {
	//address amount (comment) (comment-to)//"send",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		if(amount==null||amount.isEmpty()) throw new CommandException("amount", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SEND, address, amount, comment, comment_to);
	}
	
	/**
	 * @brief block chain apisendtoaddress
	 * @param address
	 * @param amount
	 * @param comment
	 * @param comment_to
	 * @throws CommandException
	 */
	public void sendtoaddress (@Nonnull String address, @Nonnull String amount, @Nullable String comment, @Nullable String comment_to) throws CommandException {
	//address amount (comment) (comment-to)//"send",		
		send(address, amount, comment, comment_to);
	}
	
	/**
	 * @brief block chain api sendasset
	 * @param address
	 * @param asset
	 * @param qty
	 * @param native_amount
	 * @param comment
	 * @param comment_to
	 * @throws CommandException
	 */
	public void sendasset (@Nonnull String address, @Nonnull String asset, @Nonnull String qty, @Nullable String native_amount, @Nullable String comment, @Nullable String comment_to) throws CommandException {
	//address asset qty (native-amount=min-per-output) (comment) (comment-to)//"sendasset",   
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		if(asset==null||asset.isEmpty()) throw new CommandException("asset", 0);
		if(qty==null||qty.isEmpty()) throw new CommandException("qty", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SEND_ASSET, address, asset, qty, native_amount, comment, comment_to);
	}
	
	/**
	 * @brief block chain api sendassettoaddress
	 * @param address
	 * @param asset
	 * @param qty
	 * @param native_amount
	 * @param comment
	 * @param comment_to
	 * @throws CommandException
	 */
	public void sendassettoaddress (@Nonnull String address, @Nonnull String asset, @Nonnull String qty, @Nullable String native_amount, @Nullable String comment, @Nullable String comment_to) throws CommandException {
	//address asset qty (native-amount=min-per-output) (comment) (comment-to)//"sendasset",   
		sendasset(address, asset, qty, native_amount, comment, comment_to);
	}
	
	/**
	 * @brief block chain api sendassetfrom
	 * @param from_address
	 * @param to_address
	 * @param asset
	 * @param qty
	 * @param native_amount
	 * @param comment
	 * @param comment_to
	 * @throws CommandException
	 */
	public void sendassetfrom (@Nonnull String from_address, @Nonnull String to_address, @Nonnull String asset, @Nonnull String qty, @Nullable String native_amount, @Nullable String comment, @Nullable String comment_to) throws CommandException {
	//from-address to-address asset qty (native-amount=min-per-output) (comment) (comment-to)//"sendassetfrom",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(to_address==null||to_address.isEmpty()) throw new CommandException("to_address", 0);
		if(asset==null||asset.isEmpty()) throw new CommandException("asset", 0);
		if(qty==null||qty.isEmpty()) throw new CommandException("qty", 0);
				
		hdacRpcClient.sendCommand(CommandUtils.SEND_ASSET_FROM, from_address, to_address, asset, qty, native_amount, comment, comment_to);
	}
	
	/**
	 * @brief block chain api appendrawexchange
	 * @param tx_hex
	 * @param txid
	 * @param vout
	 * @param asset
	 * @throws CommandException
	 */
	public void appendrawexchange (@Nonnull String tx_hex, @Nonnull String txid, @Nonnull String vout, JSONObject asset) throws CommandException {
	//tx-hex txid vout {"asset":qty, ...}//"appendrawexchange",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		if(asset==null) throw new CommandException("asset", 0);
				
		hdacRpcClient.sendCommand(CommandUtils.APPEND_RAW_EXCHANGE, tx_hex, txid, vout, asset.toString());		
	}
	
	/**
	 * @brief block chain api completerawexchange
	 * @param tx_hex
	 * @param txid
	 * @param vout
	 * @param asset
	 * @param data_hex
	 * @throws CommandException
	 */
	public void completerawexchange (
			@Nonnull String tx_hex, @Nonnull String txid, @Nonnull String vout, @Nonnull JSONObject asset, @Nullable String data_hex) throws CommandException {
	//tx-hex txid vout {"asset":qty, ...} (data-hex|object)//"completerawexchange",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		if(vout==null||vout.isEmpty()) throw new CommandException("vout", 0);
		if(asset==null) throw new CommandException("asset", 0);
				
		hdacRpcClient.sendCommand(CommandUtils.COMPLETE_RAW_EXCHANGE, tx_hex, txid, vout, asset.toString(), data_hex);
	}
	
	/**
	 * @brief block chain api createrawexchange
	 * @param tx_hex
	 * @param vout
	 * @param asset
	 * @throws CommandException
	 */
	public void createrawexchange (@Nonnull String tx_hex, @Nonnull String vout, JSONObject asset) throws CommandException {
	//txid vout {"asset":qty, ...}//"createrawexchange",    
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
		if(vout==null||vout.isEmpty()) throw new CommandException("vout", 0);
		if(asset==null) throw new CommandException("asset", 0);
				
		hdacRpcClient.sendCommand(CommandUtils.CREATE_RAW_EXCHANGE, tx_hex, vout, asset.toString());
	}
	
	/**
	 * @brief block chain api decoderawexchange
	 * @param tx_hex
	 * @param verbose
	 * @throws CommandException
	 */
	public void decoderawexchange (@Nonnull String tx_hex, @Nullable String verbose) throws CommandException {
	//tx-hex (verbose=false)//"decoderawexchange", 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
		if(verbose==null||verbose.isEmpty()) throw new CommandException("verbose", 0);
						
		hdacRpcClient.sendCommand(CommandUtils.DECODE_RAW_EXCHANGE, tx_hex, verbose);
	}
	
	/**
	 * @brief block chain api disablerawtransaction
	 * @param tx_hex
	 * @throws CommandException
	 */
	public void disablerawtransaction (@Nonnull String tx_hex) throws CommandException {
	//tx-hex//"disablerawtransaction",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
						
		hdacRpcClient.sendCommand(CommandUtils.DISABLE_RAW_TRANSACTION, tx_hex);
	}
	
	/**
	 * @brief block chain api preparelockunspent
	 * @param asset
	 * @param lock
	 * @throws CommandException
	 */
	public void preparelockunspent (@Nonnull JSONObject asset, @Nullable String lock) throws CommandException {
	//{"asset":qty, ...} (lock=true)//"preparelockunspent",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(asset==null) throw new CommandException("asset", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.PREPARE_LOCK_UNSPENT, asset.toString(), lock);
	}
	
	/**
	 * @brief block chain api preparelockunspentfrom
	 * @param from_address
	 * @param asset
	 * @param lock
	 * @throws CommandException
	 */
	public void preparelockunspentfrom (@Nonnull String from_address, @Nonnull JSONObject asset, @Nullable String lock) throws CommandException {
	//from-address {"asset":qty, ...} (lock=true)//"preparelockunspentfrom",   
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(asset==null) throw new CommandException("asset", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.PREPARE_LOCK_UNSPENT_FROM, from_address, asset.toString(), lock);
	}
	
	/**
	 * @brief block chain api create
	 * @param type
	 * @param name
	 * @param open
	 * @param custom_field_1
	 * @throws CommandException
	 */
	public void create (@Nonnull String type, @Nonnull String name, @Nonnull String open, @Nullable JSONObject custom_field_1) throws CommandException {
	//type=stream name open ({"custom-field-1":"x",...})//"create",        
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(type==null||type.isEmpty()) throw new CommandException("type", 0);
		if(name==null||name.isEmpty()) throw new CommandException("name", 0);
		if(open==null||open.isEmpty()) throw new CommandException("open", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.CREATE_T_STREAM, type, name, open, custom_field_1!=null?custom_field_1.toString():null);
	}
	
	/**
	 * @brief block chain api createfrom
	 * @param from_address
	 * @param type
	 * @param name
	 * @param open
	 * @param custom_field_1
	 * @throws CommandException
	 */
	public void createfrom (@Nonnull String from_address, @Nonnull String type, @Nonnull String name, @Nonnull String open, @Nullable JSONObject custom_field_1) throws CommandException {
	//from-address type=stream name open ({"custom-field-1":"x",...})//"createfrom",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(type==null||type.isEmpty()) throw new CommandException("type", 0);
		if(name==null||name.isEmpty()) throw new CommandException("name", 0);
		if(open==null||open.isEmpty()) throw new CommandException("open", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.CREATE_FROM_T_STREAM, from_address, type, name, open, custom_field_1!=null?custom_field_1.toString():null);
	}
	
	/**
	 * @brief block chain api liststreams
	 * @param streams
	 * @param verbose
	 * @param count
	 * @param start
	 * @throws CommandException
	 */
	public void liststreams (@Nullable String streams, @Nullable String verbose, @Nullable String count, @Nullable String start) throws CommandException {
	//(streams=*) (verbose=false)</br>(count=MAX) (start=-count)//"liststreams",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_STREAMS, streams, verbose, count, start);
	}
	
	/**
	 * @brief block chain api publish
	 * @param stream
	 * @param key
	 * @param data_hex
	 * @throws CommandException
	 */
	public void publish (@Nonnull String stream, @Nonnull String key, @Nonnull String data_hex) throws CommandException {
	//stream key data-hex//"publish", 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		if(key==null||key.isEmpty()) throw new CommandException("key", 0);
		if(data_hex==null||data_hex.isEmpty()) throw new CommandException("data_hex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.PUBLISH, stream, key, data_hex);
	}
	
	/**
	 * @brief block chain api publishfrom
	 * @param from_address
	 * @param stream
	 * @param key
	 * @param data_hex
	 * @throws CommandException
	 */
	public void publishfrom (@Nonnull String from_address, @Nonnull String stream, @Nonnull String key, @Nonnull String data_hex) throws CommandException {
	//from-address stream key data-hex//"publishfrom",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		if(key==null||key.isEmpty()) throw new CommandException("key", 0);
		if(data_hex==null||data_hex.isEmpty()) throw new CommandException("data_hex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.PUBLISH_FROM, from_address, stream, key, data_hex);
	}
	
	/**
	 * @brief block chain api subscribe
	 * @param asset
	 * @param rescan
	 * @throws CommandException
	 */
	public void subscribe (@Nonnull String asset, @Nullable String rescan) throws CommandException {
	//asset(s)|stream(s) (rescan=true)//"subscribe", 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(asset==null||asset.isEmpty()) throw new CommandException("asset", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SUBSCRIBE, asset, rescan);
	}
	
	/**
	 * @brief block chain api unsubscribe
	 * @param asset
	 * @throws CommandException
	 */
	public void unsubscribe (@Nonnull String asset) throws CommandException {
	//asset(s)|stream(s)//"unsubscribe",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(asset==null||asset.isEmpty()) throw new CommandException("asset", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.UNSUBSCRIBE, asset);
	}
	
	/**
	 * @brief block chain api getassettransaction
	 * @param asset
	 * @param txid
	 * @param verbose
	 * @throws CommandException
	 */
	public void getassettransaction (@Nonnull String asset, @Nonnull String txid, @Nullable String verbose) throws CommandException {
	//asset txid (verbose=false)//"getassettransaction", 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(asset==null||asset.isEmpty()) throw new CommandException("asset", 0);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_ASSET_TRANSACTION, asset, txid, verbose);
	}
	
	/**
	 * @brief block chain api listassettransactions
	 * @param asset
	 * @param verbose
	 * @param count
	 * @param start
	 * @param local_ordering
	 * @throws CommandException
	 */
	public void listassettransactions (@Nonnull String asset, @Nullable String verbose, @Nullable String count, @Nullable String start, @Nullable String local_ordering) throws CommandException {
	//asset     (verbose=false) (count=10) (start=-count) (local-ordering=false)//"listassettransactions", 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(asset==null||asset.isEmpty()) throw new CommandException("asset", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_ASSET_TRANSACTIONS, asset, verbose, count, start, local_ordering);
	}
	
	/**
	 * @brief block chain api getstreamitem
	 * @param stream
	 * @param txid
	 * @param verbose
	 * @throws CommandException
	 */
	public void getstreamitem (@Nonnull String stream, @Nonnull String txid, @Nullable String verbose) throws CommandException {
	//stream txid (verbose=false)//"getstreamitem",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);		
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_STREAMITEM, stream, txid, verbose);
	}
	
	/**
	 * @brief block chain api gettxoutdata
	 * @param txid
	 * @param vout
	 * @param count_bytes
	 * @param start_byte
	 * @throws CommandException
	 */
	public void gettxoutdata (@Nonnull String txid, @Nonnull String vout, @Nullable String count_bytes, @Nullable String start_byte) throws CommandException {
	//txid vout (count-bytes=INT_MAX) (start-byte=0)//"gettxoutdata",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		if(vout==null||vout.isEmpty()) throw new CommandException("vout", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_TXOUT_DATA, txid, vout, count_bytes, start_byte);
	}
	
	/**
	 * @brief block chain api liststreamblockitems
	 * @param stream
	 * @param blocks
	 * @param verbose
	 * @param count
	 * @param start
	 * @throws CommandException
	 */
	public void liststreamblockitems (@Nonnull String stream, @Nonnull String blocks, @Nullable String verbose, @Nullable String count, @Nullable String start) throws CommandException {
	//stream     blocks</br>(verbose=false) (count=MAX) (start=-count)//"liststreamblockitems", 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		if(blocks==null||blocks.isEmpty()) throw new CommandException("blocks", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_STREAM_BLOCK_ITEMS, stream, blocks, verbose, count, start);
	}
	
	/**
	 * @brief block chain api liststreamkeyitems
	 * @param stream
	 * @param key
	 * @param verbose
	 * @param count
	 * @param start
	 * @param local_ordering
	 * @throws CommandException
	 */
	public void liststreamkeyitems (@Nonnull String stream, @Nonnull String key, @Nullable String verbose, @Nullable String count, @Nullable String start, @Nullable String local_ordering) throws CommandException {
	//stream key (verbose=false) (count=10) (start=-count) (local-ordering=false)//"liststreamkeyitems",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		if(key==null||key.isEmpty()) throw new CommandException("key", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_STREAM_KEY_ITEMS, stream, key, verbose, count, start, local_ordering);
	}
	
	/**
	 * @brief block chain api liststreamkeys
	 * @param stream
	 * @param key
	 * @param verbose
	 * @param count
	 * @param start
	 * @param local_ordering
	 * @throws CommandException
	 */
	public void liststreamkeys (@Nonnull String stream, @Nullable String key, @Nullable String verbose, @Nullable String count, @Nullable String start, @Nullable String local_ordering) throws CommandException {
	//stream (keys=*) (verbose=false) (count=MAX) (start=-count) (local-ordering=false)//"liststreamkeys",  
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_STREAM_KEYS, stream, key, verbose, count, start, local_ordering);
	}
	
	/**
	 * @brief block chain api liststreamitems
	 * @param stream
	 * @param verbose
	 * @param count
	 * @param start
	 * @param local_ordering
	 * @throws CommandException
	 */
	public void liststreamitems (@Nonnull String stream, @Nullable String verbose, @Nullable String count, @Nullable String start, @Nullable String local_ordering) throws CommandException {
	//stream (verbose=false) (count=10) (start=-count) (local-ordering=false)//"liststreamitems", 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_STREAM_ITEMS, stream, verbose, count, start, local_ordering);
	}
	
	/**
	 * @brief block chain api liststreampublisheritems
	 * @param stream
	 * @param address
	 * @param verbose
	 * @param count
	 * @param start
	 * @param local_ordering
	 * @throws CommandException
	 */
	public void liststreampublisheritems (@Nonnull String stream, @Nonnull String address, @Nullable String verbose, @Nullable String count, @Nullable String start, @Nullable String local_ordering) throws CommandException {
	//stream     address (verbose=false) (count=10) (start=-count) (local-ordering=false)//"liststreampublisheritems", 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(stream==null||stream.isEmpty()) throw new CommandException("stream", 0);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_STREAM_PUBLISHER_ITEMS, stream, address, verbose, count, start, local_ordering);
	}
	
	/**
	 * @brief block chain api combineunspent
	 * @param addresses
	 * @param maxconf
	 * @param maxcombines
	 * @param mininputs
	 * @param maxinputs
	 * @param maxtime
	 * @throws CommandException
	 */
	public void combineunspent (@Nullable String addresses, @Nullable String maxconf, @Nullable String maxcombines, @Nullable String mininputs, @Nullable String maxinputs, @Nullable String maxtime) throws CommandException {
	//(addresses=*) (minconf=1) (maxcombines=100) (mininputs=2) (maxinputs=100) (maxtime=15)//"combineunspent",                             
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.COMBINE_UNSPENT, addresses, maxconf, maxcombines, mininputs, maxinputs, maxtime);
	}
	
	/**
	 * @brief block chain api listlockunspent
	 * @throws CommandException
	 */
	public void listlockunspent () throws CommandException {
	//null,//"listlockunspent",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_LOCK_UNSPENT);
	}
	
	/**
	 * @brief block chain api lockunspent
	 * @param unlock
	 * @param txid_vout
	 * @throws CommandException
	 */
	public void lockunspent (@Nonnull String unlock, @Nullable JSONArray txid_vout) throws CommandException {
	//unlock ([{"txid":"id","vout":n},...])//"lockunspent",                                
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(unlock==null||unlock.isEmpty()) throw new CommandException("unlock", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LOCK_UNSPENT, unlock, (txid_vout!=null)?txid_vout.toString():null);
	}
	
	/**
	 * @brief block chain api appendrawchange
	 * @param tx_hex
	 * @param address
	 * @param native_fee
	 * @throws CommandException
	 */
	public void appendrawchange (@Nonnull String tx_hex, @Nonnull String address, @Nullable String native_fee) throws CommandException {
	//tx-hex address (native-fee)//"appendrawchange",                            
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.APPEND_RAW_CHANGE, tx_hex, address, native_fee);
	}
	
	/**
	 * @brief block chain api appendrawdata
	 * @param tx_hex
	 * @param data_hex
	 * @throws CommandException
	 */
	public void appendrawdata (@Nonnull String tx_hex, @Nonnull String data_hex) throws CommandException {
	//tx-hex data-hex|object//"appendrawdata",         
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
		if(data_hex==null||data_hex.isEmpty()) throw new CommandException("data_hex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.APPEND_RAW_DATA, tx_hex, data_hex);
	}
	
	/**
	 * @brief block chain api appendrawmetadata
	 * @param tx_hex
	 * @param data_hex
	 * @throws CommandException
	 */
	public void appendrawmetadata (@Nonnull String tx_hex, @Nonnull String data_hex) throws CommandException {
	//tx-hex data-hex|object//"appendrawdata",         
		appendrawdata(tx_hex, data_hex);
	}
	
	/**
	 * @brief block chain api appendrawtransaction
	 * @param tx_hex
	 * @param txid_vout
	 * @param address
	 * @param data
	 * @param action
	 * @throws CommandException
	 */
	public void appendrawtransaction (@Nonnull String tx_hex, @Nonnull JSONArray txid_vout, @Nullable JSONObject address, @Nullable JSONArray data, @Nullable String action) throws CommandException {
	//tx-hex [{"txid":"id","vout":n},...] ({"address":amount,...})</br>(data=[]) (action="")//"appendrawtransaction",                       
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
		if(txid_vout==null) throw new CommandException("txid, vout", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.APPEND_RAW_TRANSACTION, tx_hex, (txid_vout!=null)?txid_vout.toString():null, (address!=null)?address.toString():null, (data!=null)?data.toString():null, action);
	}
	
	/**
	 * @brief block chain api createrawtransaction
	 * @param txid_vout
	 * @param address
	 * @param data
	 * @param action
	 * @throws CommandException
	 */
	public void createrawtransaction (@Nonnull JSONArray txid_vout, @Nonnull JSONObject address, @Nullable JSONArray data, @Nullable String action) throws CommandException {
	//[{"txid":"id","vout":n},...] {"address":amount,...}</br>(data=[]) (action="")//"createrawtransaction",                       
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(txid_vout==null) throw new CommandException("txid, vout", 0);
		if(address==null) throw new CommandException("address", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.CREATE_RAW_TRANSACTION, txid_vout.toString(), address.toString(), (data!=null)?data.toString():null, action);
	}
	
	/**
	 * @brief block chain api createrawsendfrom
	 * @param from_address
	 * @param to_address
	 * @param data
	 * @param action
	 * @throws CommandException
	 */
	public void createrawsendfrom (@Nonnull String from_address, @Nonnull JSONObject to_address, @Nullable JSONArray data, @Nullable String action) throws CommandException {
	//from-address {"to-address":amount,...}</br>(data=[]) (action="")//"createrawsendfrom",                          
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(to_address==null) throw new CommandException("to_address", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.CREATE_RAW_SEND_FROM, from_address, to_address.toString(), (data!=null)?data.toString():null, action);
	}
	
	/**
	 * @brief block chain api decoderawtransaction
	 * @param tx_hex
	 * @throws CommandException
	 */
	public void decoderawtransaction (@Nonnull String tx_hex) throws CommandException {
	//tx-hex//"decoderawtransaction",                       
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.DECODE_RAW_TRANSACTION, tx_hex);
	}
	
	/**
	 * @brief block chain api signrawtransaction
	 * @param tx_hex
	 * @param parent_output
	 * @param private_key
	 * @param sighashtype
	 * @throws CommandException
	 */
	public void signrawtransaction (@Nonnull String tx_hex, @Nullable JSONArray parent_output, @Nullable JSONArray private_key, @Nullable String sighashtype) throws CommandException {
	//tx-hex ([{parent-output},...]) (["private-key",...]) (sighashtype=ALL)//"signrawtransaction",                         
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tx_hex==null||tx_hex.isEmpty()) throw new CommandException("tx_hex", 0);		
		
		hdacRpcClient.sendCommand(CommandUtils.SIGN_RAW_TRANSACTION, tx_hex, (parent_output!=null)?parent_output.toString():null, (private_key!=null)?private_key.toString():null, sighashtype);
	}
	
	/**
	 * @brief block chain api addnode
	 * @param ip
	 * @param command
	 * @throws CommandException
	 */
	public void addnode (@Nonnull String ip, @Nonnull String command) throws CommandException {
	//ip(:port) command//"addnode",                                    
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(ip==null||ip.isEmpty()) throw new CommandException("ip", 0);
		if(command==null||command.isEmpty()) throw new CommandException("command", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.ADD_NODE, ip, command);
	}
	
	/**
	 * @brief block chain api getaddednodeinfo
	 * @param verbose
	 * @param ip
	 * @throws CommandException
	 */
	public void getaddednodeinfo (@Nonnull String verbose, @Nullable String ip) throws CommandException {
	//verbose (ip(:port))//"getaddednodeinfo",                           
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(verbose==null||verbose.isEmpty()) throw new CommandException("verbose", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_ADDED_NODE_INFO, verbose, ip);
	}
	
	/**
	 * @brief block chain api getnetworkinfo
	 * @throws CommandException
	 */
	public void getnetworkinfo () throws CommandException {
	//null,//"getnetworkinfo",                             
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_NETWORK_INFO);
	}
	
	/**
	 * @brief block chain api getpeerinfo
	 * @throws CommandException
	 */
	public void getpeerinfo () throws CommandException {
	//null,//"getpeerinfo",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_PEER_INFO);
	}
	
	/**
	 * @brief block chain api ping
	 * @throws CommandException
	 */
	public void ping () throws CommandException {
	//null,//"ping"
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.PING);
	}
	
	/**
	 * @brief block chain api signmessage
	 * @param address
	 * @param message
	 * @throws CommandException
	 */
	public void signmessage (@Nonnull String address, @Nonnull String message) throws CommandException {
	//address|privkey message//"signmessage",                                
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		if(message==null||message.isEmpty()) throw new CommandException("message", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SIGNMESSAGE, address, message);
	}
	
	/**
	 * @brief block chain api verifymessage
	 * @param address
	 * @param signature
	 * @param message
	 * @throws CommandException
	 */
	public void verifymessage (@Nonnull String address, @Nonnull String signature, @Nonnull String message) throws CommandException {
	//address signature message//"verifymessage",                              
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		if(signature==null||signature.isEmpty()) throw new CommandException("signature", 0);
		if(message==null||message.isEmpty()) throw new CommandException("message", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.VERIFY_MESSAGE, address, signature, message);
	}
	
	/**
	 * @brief block chain api getblockchaininfo
	 * @throws CommandException
	 */
	public void getblockchaininfo () throws CommandException {
	//null,//"getblockchaininfo",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_BLOCKCHAIN_INFO);
	}
	
	/**
	 * @brief block chain api getblockhash
	 * @param height
	 * @throws CommandException
	 */
	public void getblockhash (@Nonnull String height) throws CommandException {
	//height//"getblockhash",                               
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(height==null||height.isEmpty()) throw new CommandException("height", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_BLOCKHASH, height);
	}
	
	/**
	 * @brief block chain api getmempoolinfo
	 * @throws CommandException
	 */
	public void getmempoolinfo () throws CommandException {
	//null,//"getmempoolinfo",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_MEMPOOL_INFO);
	}
	
	/**
	 * @brief block chain api getrawmempool
	 * @throws CommandException
	 */
	public void getrawmempool () throws CommandException {
	//null,//"getrawmempool",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_RAW_MEMPOOL);
	}
	
	/**
	 * @brief block chain api getrawtransaction
	 * @param txid
	 * @param verbose
	 * @throws CommandException
	 */
	public void getrawtransaction (@Nonnull String txid, @Nullable String verbose) throws CommandException {
	//txid (verbose=0)//"getrawtransaction",                          
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_RAW_TRANSACTION, txid, verbose);
	}
	
	/**
	 * @brief block chain api gettxout
	 * @param txid
	 * @param vout
	 * @param unconfirmed
	 * @throws CommandException
	 */
	public void gettxout (@Nonnull String txid, @Nonnull String vout, @Nullable String unconfirmed) throws CommandException {
	//txid vout (unconfirmed=false)//"gettxout",                                   
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(txid==null||txid.isEmpty()) throw new CommandException("txid", 0);
		if(vout==null||vout.isEmpty()) throw new CommandException("vout", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_TXOUT, txid, vout, unconfirmed);
	}
	
	/**
	 * @brief block chain api listblocks
	 * @param blocks
	 * @param verbose
	 * @throws CommandException
	 */
	public void listblocks (@Nonnull String blocks, @Nullable String verbose) throws CommandException {
	//blocks (verbose=false)//"listblocks",                                 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(blocks==null||blocks.isEmpty()) throw new CommandException("blocks", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_BLOCKS, blocks, verbose);
	}
	
	/**
	 * @brief block chain api backupwallet
	 * @param filename
	 * @throws CommandException
	 */
	public void backupwallet (@Nonnull String filename) throws CommandException {
	//filename//"backupwallet",                               
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(filename==null||filename.isEmpty()) throw new CommandException("filename", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.BACKUP_WALLET, filename);
	}
	
	/**
	 * @brief block chain api dumpprivkey
	 * @param address
	 * @throws CommandException
	 */
	public void dumpprivkey (@Nonnull String address) throws CommandException {
	//address//"dumpprivkey",                                
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(address==null||address.isEmpty()) throw new CommandException("address", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.DUMP_PRIVKEY, address);
	}
	
	/**
	 * @brief block chain api dumpwallet
	 * @param filename
	 * @throws CommandException
	 */
	public void dumpwallet (@Nonnull String filename) throws CommandException {
	//filename//"dumpwallet",                                 
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(filename==null||filename.isEmpty()) throw new CommandException("filename", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.DUMP_WALLET, filename);
	}
	
	/**
	 * @brief block chain api encryptwallet
	 * @param passphrase
	 * @throws CommandException
	 */
	public void encryptwallet (@Nonnull String passphrase) throws CommandException {
	//passphrase//"encryptwallet",                              
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(passphrase==null||passphrase.isEmpty()) throw new CommandException("passphrase", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.ENCRYPT_WALLET, passphrase);
	}
	
	/**
	 * @brief block chain api getwalletinfo
	 * @throws CommandException
	 */
	public void getwalletinfo () throws CommandException {
	//null,//"getwalletinfo",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.GET_WALLET_INFO);
	}
	
	/**
	 * @brief block chain api importprivkey
	 * @param privkey
	 * @param label
	 * @param rescan
	 * @throws CommandException
	 */
	public void importprivkey (@Nonnull String privkey, @Nullable String label, @Nullable String rescan) throws CommandException {
	//privkey(s) (label) (rescan=true)//"importprivkey",                              
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(privkey==null||privkey.isEmpty()) throw new CommandException("privkey", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.IMPORT_PRIVKEY, privkey, label, rescan);
	}
	
	/**
	 * @brief block chain api importwallet
	 * @param filename
	 * @throws CommandException
	 */
	public void importwallet (@Nonnull String filename) throws CommandException {
	//filename//"importwallet",                               
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(filename==null||filename.isEmpty()) throw new CommandException("filename", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.IMPORT_WALLET, filename);
	}
	
	/**
	 * @brief block chain api walletlock
	 * @throws CommandException
	 */
	public void walletlock () throws CommandException {
	//null,//"walletlock",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.WALLET_LOCK);
	}
	
	/**
	 * @brief block chain api walletpassphrase
	 * @param passphrase
	 * @param timeout
	 * @throws CommandException
	 */
	public void walletpassphrase (@Nonnull String passphrase, String timeout) throws CommandException {
	//passphrase timeout//"walletpassphrase",                           
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(passphrase==null||passphrase.isEmpty()) throw new CommandException("passphrase", 0);
		if(timeout==null||timeout.isEmpty()) throw new CommandException("timeout", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.WALLET_PASSPHRASE, passphrase, timeout);
	}
	
	/**
	 * @brief block chain api walletpassphrasechange
	 * @param old_passphrase
	 * @param new_passphrase
	 * @throws CommandException
	 */
	public void walletpassphrasechange (@Nonnull String old_passphrase, @Nonnull String new_passphrase) throws CommandException {
	//old-passphrase new-passphrase//"walletpassphrasechange",                     
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(old_passphrase==null||old_passphrase.isEmpty()) throw new CommandException("old_passphrase", 0);
		if(new_passphrase==null||new_passphrase.isEmpty()) throw new CommandException("new_passphrase", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.WALLET_PASSPHRASE_CHANGE, old_passphrase, new_passphrase);
	}
	
	/**
	 * @brief block chain api approvefrom
	 * @param from_address
	 * @param upgrade
	 * @param approve
	 * @throws CommandException
	 */
	public void approvefrom (@Nonnull String from_address, @Nonnull String upgrade, @Nonnull String approve) throws CommandException {
	//from-address upgrade approve//"approvefrom",                                
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(from_address==null||from_address.isEmpty()) throw new CommandException("from_address", 0);
		if(upgrade==null||upgrade.isEmpty()) throw new CommandException("upgrade", 0);
		if(approve==null||approve.isEmpty()) throw new CommandException("approve", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.APPROVE_FROM, from_address, upgrade, approve);
	}
	
//	public void create_upgrade (
//			String type, String name, String open, JSONObject protocol_version) throws CommandException {
//	//type=upgrade name open=false {"protocol-version":100xx}//"create",                                     
//		if(client==null) throw new CommandException("RPC Client", 4);
//	}
//	
//	public void createfrom_upgrade (
//			String from_address, String type, String name, String open, JSONObject protocol_version) throws CommandException {
//	//from-address type=upgrade name open=false {"protocol-version":100xx}//"createfrom",                                 
//		if(client==null) throw new CommandException("RPC Client", 4);
//	}
	
	/**
	 * @brief block chain api listupgrades
	 * @param upgrades
	 * @throws CommandException
	 */
	public void listupgrades (@Nullable String upgrades) throws CommandException {
	//(upgrades=*)//"listupgrades",                               
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.LIST_UPGRADES, upgrades);
	}
	
	/**
	 * @brief block chain api clearmempool
	 * @throws CommandException
	 */
	public void clearmempool () throws CommandException {
	//null,//"clearmempool",
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		
		hdacRpcClient.sendCommand(CommandUtils.CLEAR_MEMPOOL);
	}
	
	/**
	 * @brief block chain api pause
	 * @param tasks
	 * @throws CommandException
	 */
	public void pause (@Nonnull String tasks) throws CommandException {
	//tasks//"pause",                                     
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tasks==null||tasks.isEmpty()) throw new CommandException("tasks", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.PAUSE, tasks);
	}
	
	/**
	 * @brief block chain api resume
	 * @param tasks
	 * @throws CommandException
	 */
	public void resume (@Nonnull String tasks) throws CommandException {
	//tasks//"resume",   		
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(tasks==null||tasks.isEmpty()) throw new CommandException("tasks", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.RESUME, tasks);
	}
	
	/**
	 * @brief block chain api setlastblock
	 * @param hash
	 * @throws CommandException
	 */
	public void setlastblock (@Nonnull String hash) throws CommandException {
	//hash|height//"setlastblock"
		if(hdacRpcClient==null) throw new CommandException("hdacRpcClient", 4);
		if(hash==null||hash.isEmpty()) throw new CommandException("hash", 0);
		
		hdacRpcClient.sendCommand(CommandUtils.SET_LAST_BLOCK, hash);
	}
	
		
    /*
     * block chain api
	 * end	
	 */
	
	/* 
	 * Advanced node control
	 * 
	 * specified task method
	 */
	/**
	 * @brief Advanced node control
	 * @detail pause mining task method
	 * @throws CommandException
	 */
	public void pauseMining() throws CommandException {
		pause ("mining");
	}
	
	/**
	 * @brief Advanced node control
	 * @detail resume mining task method
	 * @throws CommandException
	 */
	public void resumeMining() throws CommandException {
		resume ("mining");
	}
	
	/**
	 * @brief Advanced node control
	 * @detail pause incoming task method
	 * @throws CommandException
	 */
	public void pauseIncoming() throws CommandException {
		pause ("incoming");
	}
	
	/**
	 * @brief Advanced node control
	 * @detail resume incoming task method
	 * @throws CommandException
	 */
	public void resumeIncoming() throws CommandException {
		resume ("incoming");
	}

}
