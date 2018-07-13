package com.hdacSdk.hdacCoreApi;

/**
 * @brief Parameter generation class of blockchain Permission related APIs
 * @details Permissions definition for processing APIs of blockchain permissions management
 * @class Permissions 
 * @date 2018-01-31.
 * @author Hdac Technology 
 *
 */

public class Permissions {
	
	public static String CONNECT = "connect";
	public static String SEND = "send";
	public static String RECEIVE = "receive";
	public static String CREATE = "create";
	public static String ISSUE = "issue";
	public static String MINE = "mine";
	public static String ACTIVATE = "activate";
	public static String ADMIN = "admin";
	
	private static String[] Permissions = {
			CONNECT,
			SEND,
			RECEIVE,
			CREATE,
			ISSUE,
			MINE,
			ACTIVATE,
			ADMIN
	};
	
	/**
	 * @brief Create parameters for multiple permissions
	 * @param permissions array
	 * @return String convert string array to string parameter for Permission APIs
	 */
	public static String getPermissions(String[] permissions) {
		String rst="";
		
		for(int i=0; i<permissions.length; i++) {
			if(isValidPermission(permissions[i])&&!rst.contains(permissions[i])) {
				if(!rst.isEmpty()) rst.concat(",");
				rst.concat(permissions[i]);
			}
			
		}
		
		return rst;
		
	}
	
	/**
	 * @brief Determine if permission is available
	 * @param permission
	 * @return boolean permission is available
	 */
	public static boolean isValidPermission(String permission) {
		for(int i = 0; i<Permissions.length; i++) {
			if(Permissions[i].equals(permission)) return true;
		}
		
		return false;
	}

}
