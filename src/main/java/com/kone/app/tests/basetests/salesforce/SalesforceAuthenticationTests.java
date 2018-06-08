package com.kone.app.tests.basetests.salesforce;

import org.testng.annotations.Test;

import com.kone.app.screens.konesitesurvey.SiteHomeScreen;
import com.kone.app.screens.konesitesurvey.SiteLoginScreen;
import com.kone.app.screens.salesforce.LoginScreen;
import com.kone.app.screens.salesforce.MainScreen;
import com.kone.app.screens.salesforce.SelectOpportunityScreen;
import com.kone.app.tests.basetests.SalesforceBaseTest;


public class SalesforceAuthenticationTests extends SalesforceBaseTest{
    
	private LoginScreen loginScreen;
	private MainScreen mainScreen;
	private SelectOpportunityScreen searchResultScreen;
	private SiteLoginScreen siteLoginScreen;
	private SiteHomeScreen siteHomeScreen;
	
	protected String salesForceloginUser;
	protected String salesForceloginPassword;
	protected String siteloginUser;
	protected String sitePassword;
	
	public SalesforceAuthenticationTests(String salesForceUser, String salesForcepassword, String siteUsername, String sitePassword) {
		
		this.salesForceloginUser = salesForceUser;
		this.salesForceloginPassword = salesForcepassword;
		this.siteloginUser	= siteUsername;
		this.sitePassword	= sitePassword;
		
		loginScreen = new LoginScreen();
	}
	
    @Test(groups={ "web", "login_salesforce" })
    void loginSalesForce() {
    	wdriver.navigate().to("https://kone--qa.cs88.my.salesforce.com/006/o");
//    	wdriver.navigate().to("https://test.salesforce.com/");
    	wdriver.manage().window().maximize();
    	mainScreen=loginScreen.signIn(this.salesForceloginUser, this.salesForceloginPassword);
    	searchResultScreen=mainScreen.searchOpportunity();
    	siteLoginScreen=searchResultScreen.clickonOpportunity();
    	siteHomeScreen=siteLoginScreen.siteSurveySignIn(this.siteloginUser, this.sitePassword);
    	siteHomeScreen.createTask();
    }
    

//    @Test(groups={ "logout" })
//    void logoutTest() {
//    	System.out.println("START --------- Logout Test ---------");
//    	mainScreen.openSideMenu().clickLogoutMenu();
//    	System.out.println("END --------- Logout Test ---------");
//    }

}