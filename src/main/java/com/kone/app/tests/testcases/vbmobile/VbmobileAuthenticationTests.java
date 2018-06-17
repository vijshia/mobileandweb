package com.kone.app.tests.testcases.vbmobile;

import org.testng.annotations.Test;

import com.kone.app.pages.vbmobile.LoginPage;
import com.kone.app.pages.vbmobile.SurveyManagerPage;
import com.kone.app.tests.testcases.VbmobileBaseTest;


public class VbmobileAuthenticationTests extends VbmobileBaseTest{
    
	private LoginPage loginScreen;
	private SurveyManagerPage surveyManagerScreen;
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	
	public VbmobileAuthenticationTests(String user, String password, String frontline) {
		
		this.loginUser = user;
		this.loginPassword = password;
		this.frontline = frontline;
		
		loginScreen = new LoginPage();
		surveyManagerScreen = new SurveyManagerPage();
	}
	
    @Test(groups={ "vbmobie_login" })
    void loginTest() {
    	loginScreen.signIn(this.loginUser, this.loginPassword, this.frontline);
    }
    
//    @Test(groups={ "vbmobie_logout" })
//    void logoutTest() {
//    	surveyManagerScreen.openSideMenu().logout();
//    }

}