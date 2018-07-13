package com.hdacSdk.hdacCoreApi;


/**
 * @brief 
 * Definitions of error messages related to the RPC API
 * @details 
 * Definitions of error messages related to api parameters
 * @class CommandException
 * @date 2018-01-31.
 * @author Hdac Technology 
 *
 */

public class CommandException extends Exception{
	
	private String mMsg;
	private int mErrCode;
	
	private String[] err_base_msg = {
		"must not be null or empty.",		
		"unacceptable param(s).",
		"is fault",
		"Can not exceed.",
		"is null"
	};
	
	public CommandException(String msg, int errCode) {
		mMsg = msg;
		mErrCode = errCode;
	}
	
	public CommandException(String msg) {
		this(msg, -1);
	}
	
	public CommandException(int errCode) {
		this(null, errCode);		
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub		
		switch(mErrCode) {
			case 0:
				return "[RPC_CMD_E00" + mErrCode + "] " + mMsg + " : " + err_base_msg[0];
			case 1:
				return "[RPC_CMD_E00" + mErrCode + "] " + mMsg + " : " + err_base_msg[1];
			case 2:
				return "[RPC_CMD_E00" + mErrCode + "] " + mMsg + " : " + err_base_msg[2];
			case 3:
				return "[RPC_CMD_E00" + mErrCode + "] " + mMsg + " : " + err_base_msg[3];
			case 4:
				return "[RPC_CMD_E00" + mErrCode + "] " + mMsg + " : " + err_base_msg[4];
		}
		
		if(mMsg!=null && !mMsg.isEmpty()) return "[TX_E00" + err_base_msg.length + "] " + mMsg;
		
		int ec = err_base_msg.length + 1;
		return "[RPC_CMD_E00" + ec + "] Unknown error!";		
	}
	
	public String getErrorCode() {
		return "[RPC_CMD_E00" + mErrCode + "] ";
	}
	

}
