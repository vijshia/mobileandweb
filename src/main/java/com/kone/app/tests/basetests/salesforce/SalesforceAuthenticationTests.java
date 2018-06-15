package com.kone.app.tests.basetests.salesforce;

import org.testng.annotations.Test;

import com.kone.app.screens.konesitesurvey.SiteHomeScreen;
import com.kone.app.screens.konesitesurvey.SiteLoginScreen;
import com.kone.app.screens.salesforce.LoginScreen;
import com.kone.app.screens.salesforce.MainScreen;
import com.kone.app.screens.salesforce.SelectOpportunityScreen;
import com.kone.app.tests.basetests.SalesforceBaseTest;
import com.kone.app.screens.outlook.OutlookLoginScreen;
import com.kone.app.screens.outlook.OutlookHomeScreen;


public class SalesforceAuthenticationTests extends SalesforceBaseTest{
    
	private LoginScreen loginScreen;
	private MainScreen mainScreen;
	private SelectOpportunityScreen searchResultScreen;
	private SiteLoginScreen siteLoginScreen;
	private SiteHomeScreen siteHomeScreen;
	private OutlookLoginScreen outlookloginScreen;
	private OutlookHomeScreen outlookHomeScreen;
	
	protected String salesForceloginUser;
	protected String salesForceloginPassword;
	protected String siteloginUser;
	protected String sitePassword;
	/*protected String outLookloginUser;
	protected String outLookPassword;*/
	
	public SalesforceAuthenticationTests(String salesForceUser, String salesForcepassword, String siteUsername, String sitePassword) {
		
		this.salesForceloginUser = salesForceUser;
		this.salesForceloginPassword = salesForcepassword;
		this.siteloginUser	= siteUsername;
		this.sitePassword	= sitePassword;
		/*this.outLookloginUser = outlookUsername;
		this.outLookPassword = outlookPassword;*/
		
		loginScreen = new LoginScreen();
//		outlookloginScreen=new OutlookLoginScreen();
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
		
    	outlookHomeScreen=outlookloginScreen.emailLogin(this.salesForceloginUser, this.sitePassword);
    	outlookHomeScreen.getTaskID();
    }
    
/*    @Test(groups={ "web", "login_outlook" })
    void loginOutLook() {
		wdriver.navigate().to("https://outlook.office365.com");
    	wdriver.manage().window().maximize();
    	outlookHomeScreen=outlookloginScreen.emailLogin(this.salesForceloginUser, this.sitePassword);
    	outlookHomeScreen.getTaskID();
    }*/
    

//    @Test(groups={ "logout" })
//    void logoutTest() {
//    	System.out.println("START --------- Logout Test ---------");
//    	mainScreen.openSideMenu().clickLogoutMenu();
//    	System.out.println("END --------- Logout Test ---------");
//    }

}