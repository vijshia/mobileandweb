package com.kone.framework.appium;

import java.io.IOException;
import java.net.ServerSocket;

import com.kone.framework.utility.Log;
import com.kone.framework.utility.PropertiesLoader;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


public class AppiumServer {
	
	private static AppiumDriverLocalService service;
	private static AppiumServiceBuilder builder;	
	private static int port = 4723;
	public static AppiumServer sever = new AppiumServer();
	
	public AppiumServer() {		
	}

	public boolean isServerRunning() {
		
		boolean serverIsRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			serverIsRunning = true;
		} finally {
			serverSocket = null;
		}
		return serverIsRunning;
	}
	
	static {
		
		port = Integer.parseInt(PropertiesLoader.instance.
				getProperty("appium.server.port", "4723"));
		
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress(PropertiesLoader.instance.
				getProperty("appium.server.url", "127.0.0.1"));
		builder.usingPort(port);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,
				PropertiesLoader.instance.
				getProperty("appium.server.loglevel", "error"));
		//builder.withLogFile(new File("log/appium.log"));
		
		service = AppiumDriverLocalService.buildService(builder);
	}
	
	public void startServer() {
		
		if(!isServerRunning()) {
			Log.info("Starting Appium server at port " + port);
			service.start();
			
			// Wait for server starting
			for(int i=1; i < 60; i++) {
				if(isServerRunning()) {
					Log.info("Appium server is started at port " + port);
					break;
				}else {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} // wait for 3 seconds
				}				
			}
			
			if(!isServerRunning()) {
				Log.error("Failed to start Appium server at port " + port);
			}			
		
		} else {
			Log.warn("Appium server is already running at port " + port);
		}
	}
	
	public void stopServer() {
		
		Log.info("Stopping Appium server at port " + port);
		service.stop();
		
		if(isServerRunning()) {
			Log.error("Failed to stop Appium server at port " + port);
		}else {
			Log.info("Appium server is stoped at port " + port);
		}
	}
}
