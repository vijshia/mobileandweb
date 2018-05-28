package com.kone.app.tests.basetests.vbmobile;

import org.testng.annotations.Test;

import com.kone.app.screens.vbmobile.LoginScreen;
import com.kone.app.screens.vbmobile.MainScreen;
import com.kone.app.tests.basetests.VbmobileBaseTest;


public class VbmobileAuthenticationTests extends VbmobileBaseTest{
    
	private LoginScreen loginScreen;
	private MainScreen mainScreen;
	
	public VbmobileAuthenticationTests(String user, String password) {
		
		this.loginUser = user;
		this.loginPassword = password;
		
		loginScreen = new LoginScreen();
		mainScreen = new MainScreen();
	}
	
//    @BeforeMethod(groups = { "login" })
//    void appStateLoggedOut() {
//    	ensureLoggedOut();
//    }
	
    @Test(groups={ "login" })
    void loginTest() {
    	System.out.println("START --------- Login Test ---------");
    	mainScreen = loginScreen.signIn(this.loginUser, this.loginPassword);
    	System.out.println("END ----------- Login Test ---------");
    }
    
//    @BeforeMethod(groups = { "logout" })
//    void appStateLoggedIn() {
//    	ensureLoggedIn(this.loginEmail, this.loginPassword);
//    }
    
    @Test(groups={ "logout" })
    void logoutTest() {
    	System.out.println("START --------- Logout Test ---------");
    	mainScreen.openSideMenu().clickLogoutMenu();
    	System.out.println("END --------- Logout Test ---------");
    }

}