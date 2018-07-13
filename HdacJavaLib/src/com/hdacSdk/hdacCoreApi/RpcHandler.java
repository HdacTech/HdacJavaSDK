package com.hdacSdk.hdacCoreApi;

import org.json.JSONObject;

/**
 * @brief HdacRPCClient의 handler
 * @details HdacRPCClient의 http response handler
 * @class RpcHandler
 * @date 2018-03-19.
 * @author Hdac Technology 
 */
public interface RpcHandler {
	public abstract void onResponse(int method, JSONObject response);		// return response Json data	
	public abstract void onError(int method, String err_msg, int code);		// for error message & code.	
	
	// finally call the function when http/https done
	public default void done(int method) {
		
	}	
}
