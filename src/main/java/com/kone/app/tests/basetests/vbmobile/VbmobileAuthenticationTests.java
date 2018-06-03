package com.kone.app.tests.basetests.vbmobile;

import org.testng.annotations.Test;

import com.kone.app.screens.vbmobile.LoginScreen;
import com.kone.app.screens.vbmobile.MainScreen;
import com.kone.app.tests.basetests.VbmobileBaseTest;


public class VbmobileAuthenticationTests extends VbmobileBaseTest{
    
	private LoginScreen loginScreen;
	private MainScreen mainScreen;
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	
	public VbmobileAuthenticationTests(String user, String password, String frontline) {
		
		this.loginUser = user;
		this.loginPassword = password;
		this.frontline = frontline;
		
		loginScreen = new LoginScreen();
		mainScreen = new MainScreen();
	}
	
    @Test(groups={ "login" })
    void loginTest() {
    	loginScreen.signIn(this.loginUser, this.loginPassword, this.frontline);
    }
    
    @Test(groups={ "logout" })
    void logoutTest() {
    	mainScreen.openSideMenu().clickLogoutMenu();
    }

}