package com.hdacSdk.hdacWallet;

/**
 * @brief Definition of Hdac wallet package related errors
 * @details Handling HdacWallet related error exception classes
 * @class HdacWalletException
 * @date 2018-06-01.
 * @author Hdac Technology 
 *
 */
public class HdacWalletException extends Exception {
	private String mMsg;
	private int mErrCode;
	
	public HdacWalletException(String msg, int errCode) {
		mMsg = msg;
		mErrCode = errCode;
	}	
		
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub		
		switch(mErrCode) {
			case 0:
				return "[Wallet] " + mMsg;
			case 1:
				return "[Transaction] " + mMsg;
			
		}	
		
		return "[HdacWallet Package] Unknown error! : " + mMsg;
	}
	
}
