package com.hdacSdk.hdacCoreApi;

/**
 * @brief Class for advanced node control support
 * @details specified task method
 * @class Tasks
 * @date 2018-03-19.
 * @author Hdac Technology 
 */
public class Tasks {
	public static String INCOMING = "incoming";
	public static String MINING = "mining";
	
	private static String[] Tasks = {
			INCOMING,
			MINING
	};
	
	public static String getTasks(String[] tasks) {
		String rst="";
		if(tasks==null) return null;
		
		for(int i=0; i<tasks.length; i++) {
			if(isValidTask(tasks[i])&&!rst.contains(tasks[i])) {
				if(!rst.isEmpty()) rst.concat(",");
				rst.concat(tasks[i]);
			}			
		}
		return rst;		
	}	
	
	public static boolean isValidTask(String task) {
		if(task==null) return false;
		
		for(int i = 0; i<Tasks.length; i++) {
			if(Tasks[i].equals(task)) return true;
		}
		
		return false;
	}

}
