package com.hdacSdk.hdacCoreApi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.SwingWorker;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hdac.jsonrpc.JSONRPCClient;
import com.hdac.jsonrpc.JSONRPCException;
import com.hdac.jsonrpc.JSONRPCParams;
import com.hdac.jsonrpc.JSONRPCParams.Versions;

/**
 * @brief HdacRpcClient library interface
 * @details interface of the HdacRpcClient library to call blockchain APIs
 * @class HdacRpcClient
 * @date 2018-01-31.
 * @author Hdac Technology 
 *
 */
public class HdacRpcClient {
	
	private String DEFAULT_PORT = "8822";            ///< default port, you need to check your hdac.conf
    private String DEFAULT_IP = "http://127.0.0.1";  ///< default rpc server ip, you need to check your server
    private String DEFAULT_CHAIN_NAME = "hdac";      ///< default chain name, you need to check your chian-portocol attr in params.dat
    private int DEFAULT_TIMEOUT = 3000;        
    
    private String DEFAULT_RPCUSER = "mc";           ///< default rpc user, you need to check your hdac.conf
    private String DEFAULT_RPCPW = "mc1234";         ///< default password, you need to check your hdac.conf
    
    private int mTimeout = DEFAULT_TIMEOUT;
	private String mPort = DEFAULT_PORT;
    private String mIp = DEFAULT_IP;
    private String mUrl = DEFAULT_IP + ":" + DEFAULT_PORT;

    private String mChainName = DEFAULT_CHAIN_NAME;
    private String _rpcuser = DEFAULT_RPCUSER;
    private String _rpcpassword = DEFAULT_RPCPW;

    public static final int RESPONSE_OK = 0;
    public static final int RESPONSE_NULL = 1;
    public static final int RESPONSE_ERROR_NETWORK = 2;
    public static final int RESPONSE_ERROR_UNDEFINED = 3;
    public static final int RESPONSE_ERROR_METHOD = 4;
    public static final int RESPONSE_ERROR_PARAMS = 5;
    public static final int RESPONSE_RESULT_ERROR = 6;
    
    //default HTTP version : 2.0
    private Versions HTTP_VERSION = JSONRPCParams.Versions.VERSION_2;

    private JSONObject mResponse = null;
    private JSONArray mArgvs;
    private int mMethod;   
    private RpcHandler mHandler = null;
    
	protected HdacRpcClient(String ip, String port, String user, String pw, String chainName){
        //DEFAULT_PORT;
        //DEFAULT_IP; 
    	//DEFAULT_RPCUSER;
    	//DEFAULT_RPCPW;
    	setRPCIp(ip);
    	setRPCPort(port);
    	setRPCUser(user);
    	setRPCPassword(pw);
    	setChainName(chainName);
    }
    
	// Default HdacRpcClient
    protected HdacRpcClient(RpcHandler hdr){
        //DEFAULT_PORT;
        //DEFAULT_IP; 
    	//DEFAULT_RPCUSER;
    	//DEFAULT_RPCPW;
    	setRPCHandler(hdr);
    	
    }

    protected HdacRpcClient(String ip, String port, String user, String pw, String chainName, RpcHandler hdr){
    	this(ip, port, user, pw, chainName);
        setRPCHandler(hdr);              
    }
    
    //setting RPC url 
    /**
     * @brief setting ip 
     * @param ip
     */
    public void setRPCIp(String ip) {
    	mIp = ip;
    	mUrl = mIp + ":" + mPort;
    }
    
    /**
     * @brief setting port
     * @param port
     */
    public void setRPCPort(String port) {
    	mPort = port;
    	mUrl = mIp + ":" + mPort;
    }
    
    /**
     * @brief setting url( [ip]:[port] )
     * @param url
     */
    public void setRPCUrl(String url) {
    	mUrl = url;
    }    
    //
    
    //setting RPC attributes
    /**
     * @brief setting http timeout 
     * @param timeout
     */
    public void setRPCTimeout(int timeout) {
    	mTimeout = timeout;
    }
    
    /**
     * @brief rpc user
     * @param user
     */
    public void setRPCUser(String user) { // mandatory
    	_rpcuser = user;
    }
    
    /**
     * @brief rpc password
     * @param pw
     */
    public void setRPCPassword(String pw) { // mandatory
    	_rpcpassword = pw;
    }
    
    /**
     * @brief getting chain name ( chain-protocol )
     * @return
     */
    public String getChainName() {
		return mChainName;
	}

    /**
     * @brief setting chain name ( chain-protocol )
     * @param mChainName
     */
	public void setChainName(String mChainName) {
		this.mChainName = mChainName;
	}
    //
    
    //
	/**
	 * 
	 * @param hdr
	 */
	///< re-defines by user
    public void setRPCHandler(RpcHandler hdr) { 
    	mHandler = hdr;
    }
    
    public RpcHandler getHandler() {
		return mHandler;
	}
	
    public String getRPCUrl() {
    	return mUrl;
    }
    
    public String getRPCServerIp() {
    	return mIp;
    }
	
	public String getRPCServerPort() {
		return mPort;
	}

    public void sendCommand(int method, String... argvs){
    	if(!checkNetwork()){
            mHandler.onError(mMethod, "network connection failure", RESPONSE_ERROR_NETWORK);
            return;
        }

        if(method>CommandUtils.MAX_INDEX || method<0){
            mHandler.onError(mMethod, "not exist method", RESPONSE_ERROR_UNDEFINED);
            return;
        }

        String[] params = null;
        if(argvs.length>0) {
            params = new String[CommandUtils.sizeOfParamsByMId(method)];
			
			for (int i = 0; i < argvs.length; i++) {
			    params[i] = argvs[i];
			}            
        }
        
        mMethod = method;            
        try {
			CommandUtils.getMethodName(mMethod).trim();
		} catch (HdacException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        CommandParams rpc_p = new CommandParams(mMethod);
        
        try {
			mArgvs = rpc_p.makeStrParamValues(params);
		} catch (JSONException | HdacException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseUnexecutedCommand(mMethod, e.getMessage(), RESPONSE_ERROR_PARAMS);			
			return;
		}
        
        new MakeJSONRPCTask().execute();

        return;
    }
    
    private void responseUnexecutedCommand(int method, String errMsg, int errCode) {
    	mHandler.onResponse(method, null);
    	mHandler.onError(method, errMsg, errCode);    	
		mHandler.done(method);    	
    }
    
    public void sendCommand(int method, JSONArray params){
        if(!checkNetwork()){
            mHandler.onError(mMethod, "network connection failure", RESPONSE_ERROR_NETWORK);
            return;
        }

        if(method>CommandUtils.MAX_INDEX || method<0){
            mHandler.onError(mMethod, "not exist method", RESPONSE_ERROR_UNDEFINED);
            return;
        }

		if(params.length() > CommandUtils.sizeOfParamsByMId(method)) 
			responseUnexecutedCommand(mMethod, "does not match params", RESPONSE_ERROR_PARAMS);
			
		try {
			mMethod = method;
			CommandUtils.getMethodName(mMethod).trim();
			mArgvs = params;
		} catch (HdacException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        new MakeJSONRPCTask().execute();

        return;
    }

    private boolean checkNetwork(){
    	HttpURLConnection urlConnect;
    	//need check
		try {
			urlConnect = (HttpURLConnection) new URL(mUrl).openConnection();
			if(urlConnect!=null){
	            //Toast.makeText(mContext, "서버접속에 실패하였습니다.\n다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
	            return true;
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		mHandler.onError(mMethod, null, RESPONSE_ERROR_NETWORK);
        return false;
    }

    public JSONObject getResponse(){
        return mResponse;
    }

    public int getMethod(){
        return mMethod;
    }

    public class MakeJSONRPCTask extends SwingWorker<JSONObject, JSONObject> {
    	
    	private JSONObject mResult;
    	private String mErrMsg = "";

        @Override
        protected JSONObject doInBackground() {
            if(mMethod < 0) return null;
            String strUserPass = _rpcuser + ":" + _rpcpassword;
            String strUserPass64 = Base64.encodeBase64String(strUserPass.getBytes());
            String auth = "Basic " + strUserPass64;

            JSONRPCClient client = JSONRPCClient.create(getRPCUrl(), auth.trim(), HTTP_VERSION);
            //JSONRPCHttpClient client = new JSONRPCHttpClient(URL, auth.trim(), JSONRPCParams.Versions.VERSION_2);

            client.setConnectionTimeout(mTimeout);
            client.setSoTimeout(mTimeout);
            
            JSONObject rst = null;
            try {
            	rst = client.callJSONObject(CommandUtils.getMethodName(mMethod).trim(), mChainName, mArgvs);
            } catch (JSONRPCException rpcEx) {
                rpcEx.printStackTrace();
                mErrMsg = rpcEx.getMessage();
            } catch (HdacException e) {
				e.printStackTrace();
			} finally{
				done(rst);	
			}            
            return rst;
        }
        
        private void done(JSONObject rst) {
			// TODO Auto-generated method stub
        	mResult = rst;
        	if (mResult != null) {
                try {
                	if(mErrMsg!=null&&!mErrMsg.isEmpty()) {
						if(mErrMsg.equals("Success")) {
							mHandler.onResponse(mMethod, mResult);
						}
						else {
							mHandler.onError(mMethod, mErrMsg, HdacRpcClient.RESPONSE_RESULT_ERROR);
						}
						System.out.print("MakeJSONRPCTask 0 " + mResult.toString() + "\n");
                	}
                	else if(mResult.isNull("error")) {
            			//mHandler.onError(mMethod, "Success", HdacRpcClient.RESPONSE_OK);
						mHandler.onResponse(mMethod, mResult);
                	}else {
            			mHandler.onError(mMethod, mResult.get("error").toString(), HdacRpcClient.RESPONSE_RESULT_ERROR);
						//mHandler.onResponse(mMethod, mResult);
                	}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();					
				} finally {
					mHandler.done(mMethod);
				}
                
            } else {
            	mHandler.onError(mMethod, mErrMsg, HdacRpcClient.RESPONSE_NULL);
            	//mHandler.onResponse(mMethod, null); 
            	mHandler.done(mMethod);
            }			
			
		}  
        
//		@Override
//		protected void done() {
			// TODO Auto-generated method stub
//			System.out.print( "done" + "\n" );
//			if (mResult != null) {
//                try {
//                	if(mErrMsg!=null&&!mErrMsg.isEmpty()) {
//						mHandler.onError(mMethod, mErrMsg, HdacRpcClient.RESPONSE_RESULT_ERROR);
//						mHandler.onResponse(mMethod, null);
//                	}
//                	else if(mResult.get("error")==null) {
//						mHandler.onError(mMethod, "Success", HdacRpcClient.RESPONSE_OK);
//						mHandler.onResponse(mMethod, mResult);
//                	}else {
//						mHandler.onError(mMethod, mResult.get("error").toString(), HdacRpcClient.RESPONSE_RESULT_ERROR);
//						mHandler.onResponse(mMethod, mResult);
//                	}
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}               
//                
//            } else {
//            	mHandler.onError(mMethod, mErrMsg, HdacRpcClient.RESPONSE_NULL);
//            	mHandler.onResponse(mMethod, null);                
//            }
//			
//			mHandler.done(mMethod);
//			super.done();
//		}        
        
    }	

}
