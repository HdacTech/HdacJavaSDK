package com.hdacSdk.hdacCoreApi;

/**
 * @brief Definition of exception handling for the HdacCoreApi package
 * @details Supporting for APIs call and RPC client setting exception handling 
 * @class HdacException
 * @date 2018-01-31.
 * @author Hdac Technology 
 */
public class HdacException extends Exception{
	
	private String mMsg;
	private int mErrCode;
	
	private String[] err_base_msg = {
		"Not a Method!",		
		"Parameter count does not match Parameter Value count.",
		"This method does not require any parameters.",
		"Can not exceed.",
		"this is null, next params will be unreachable.",
	};
	
	public HdacException(String msg, int errCode) {
		mMsg = msg;
		mErrCode = errCode;
	}
	
	public HdacException(String msg) {
		this(msg, -1);
	}
	
	public HdacException(int errCode) {
		this(null, errCode);		
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub		
		switch(mErrCode) {
			case 0:
				return "[Blockchain_E00" + mErrCode + "] " + mMsg + ":" + err_base_msg[0];
			case 1:
				return "[Blockchain_E00" + mErrCode + "] " + mMsg + ":" + err_base_msg[1];
			case 2:
				return "[Blockchain_E00" + mErrCode + "] " + mMsg + ":" + err_base_msg[2];
			case 3:
				return "[Blockchain_E00" + mErrCode + "] " + mMsg + ":" + err_base_msg[3];
			case 4:
				return "[Blockchain_E00" + mErrCode + "] " + mMsg + ":" + err_base_msg[4];
		}
		
		if(mMsg!=null && !mMsg.isEmpty()) return "[Blockchain_E00" + err_base_msg.length + "] " + mMsg;
		int ec = err_base_msg.length + 1;
		return "[Blockchain_E00" + ec + "] Unknown error!";
	}
	
	public String getErrorCode() {
		return "[Blockchain_E00" + mErrCode + "] ";
	}


}
