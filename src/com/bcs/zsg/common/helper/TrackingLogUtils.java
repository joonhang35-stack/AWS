package com.bcs.zsg.common.helper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackingLogUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(TrackingLogUtils.class);
	private static final Logger staticLogger = LoggerFactory.getLogger(HttpUtils.class);
	private long startTime;
	private long endTime;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public TrackingLogUtils(Class className) {
		this.logger = LoggerFactory.getLogger(className);
	}
	
	public void startLogs() {
		this.startTime = System.currentTimeMillis();
	}
	
	public void endLogs(String logInfo) {
		this.endTime = System.currentTimeMillis();
		System.out.println("[Start: " + sdf.format(this.startTime) + "] [End: " + sdf.format(this.endTime) + "] " + logInfo + " [Time Spent: " + (this.endTime - this.startTime) + "]");
		logger.error("[Start: " + sdf.format(this.startTime) + "] [End: " + sdf.format(this.endTime) + "] " + logInfo + " [Time Spent: " + (this.endTime - this.startTime) + "]");
	}
	
	public static void printLogs(String logInfo) {
		Logger logger = LoggerFactory.getLogger(TrackingLogUtils.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long now = System.currentTimeMillis();
		
		String log = "[" + sdf.format(now) + "] " + logInfo;
		System.out.println(log);
		logger.error(log);
	}
	
	public Integer getCurrentDiff() {
		return Long.valueOf(System.currentTimeMillis() - startTime).intValue();
	}
	
	public static void infoLogs(String logInfo) {
		System.out.println("[INFO LOG]: " + logInfo);
		staticLogger.error("[INFO LOG]: " + logInfo);
	}
	
	public static void errorLogs(String logInfo) {
		System.out.println("[ERROR LOG]: " + logInfo);
		staticLogger.error("[ERROR LOG]: " + logInfo);
	}
}
