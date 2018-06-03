package com.kone.app.tests.basetests.salesforce;

import org.testng.annotations.Test;

import com.kone.app.screens.salesforce.LoginScreen;
import com.kone.app.tests.basetests.SalesforceBaseTest;


public class SalesforceAuthenticationTests extends SalesforceBaseTest{
    
	private LoginScreen loginScreen;
	
	public SalesforceAuthenticationTests(String user, String password) {
		
		this.loginUser = user;
		this.loginPassword = password;
		
		loginScreen = new LoginScreen();
	}
	
    @Test(groups={ "web", "login_salesforce" })
    void loginTest() {
    	wdriver.navigate().to("https://test.salesforce.com/");
    	wdriver.manage().window().maximize();
    	loginScreen.signIn(this.loginUser, this.loginPassword);
    }
    
//    @Test(groups={ "logout" })
//    void logoutTest() {
//    	System.out.println("START --------- Logout Test ---------");
//    	mainScreen.openSideMenu().clickLogoutMenu();
//    	System.out.println("END --------- Logout Test ---------");
//    }

}