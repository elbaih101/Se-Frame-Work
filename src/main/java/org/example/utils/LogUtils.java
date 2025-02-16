package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

private LogUtils() {
super();
}


	public static Logger logger(){
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
	}

	public static void logInfo(String... message){
		logger().info(String.join(" ",message));
	}
	public static void logError(String... message){
		logger().error(String.join(" ",message));
	}
	public static void logWarn(String... message){
		logger().warn(String.join(" ",message));
	}
	public static void logDebug(String... message){
		logger().debug(String.join(" ",message));
	}
	public static void logFatal(String... message){
		logger().fatal(String.join(" ",message));
	}
	public static void logTrace(String... message){
		logger().trace(String.join(" ",message));
	}

}
