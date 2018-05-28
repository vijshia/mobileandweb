package com.kone.framework.appium;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

import com.kone.framework.properties.PropertiesLoader;

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
		
		port = Integer.parseInt(PropertiesLoader.instance.getProperty("appium.port", "4723"));
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress(PropertiesLoader.instance.getProperty("appium.server.url", "127.0.0.1"));
		builder.usingPort(port);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		builder.withLogFile(new File("log/appium.log"));
		service = AppiumDriverLocalService.buildService(builder);
	}
	
	public void startServer() throws InterruptedException {
		
		if(!isServerRunning()) {
			System.out.println("Starting Appium server at port " + port);
			service.start();
			
			// Wait for server starting
			for(int i=1; i < 60; i++) {
				if(isServerRunning()) {
					System.out.println("Appium server is started at port " + port);
					break;
				}else {
					Thread.sleep(3000); // wait for 3 seconds
				}				
			}
			
			if(!isServerRunning()) {
				System.out.println("Failed to start Appium server at port " + port);
			}			
		
		} else {
			System.out.println("Appium server is already running at port " + port);
		}
	}
	
	public void stopServer() {
		
		System.out.println("Stopping Appium server at port " + port);
		service.stop();
		
		if(isServerRunning()) {
			System.out.println("Failed to stop Appium server at port " + port);
		}else {
			System.out.println("Appium server is stoped at port " + port);
		}
	}
}
