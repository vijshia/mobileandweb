package com.kone.app.tests.testcases.salesforce;

import org.testng.annotations.Test;

import com.kone.app.pages.konesitesurvey.SiteHomePage;
import com.kone.app.pages.konesitesurvey.SiteLoginPage;
import com.kone.app.pages.outlook.OutlookHomePage;
import com.kone.app.pages.outlook.OutlookLoginPage;
import com.kone.app.pages.outlook.OutlookURLLaunch;
import com.kone.app.pages.salesforce.LoginPage;
import com.kone.app.pages.salesforce.MainPage;
import com.kone.app.pages.salesforce.SelectOpportunityPage;
import com.kone.app.tests.testcases.SalesforceBaseTest;


public class SalesforceAuthenticationTests extends SalesforceBaseTest{
    
	private LoginPage loginScreen;
	private MainPage mainScreen;
	private SelectOpportunityPage searchResultScreen;
	private SiteLoginPage siteLoginScreen;
	private SiteHomePage siteHomeScreen;
	private OutlookLoginPage outlookloginScreen;
	private OutlookHomePage outlookHomeScreen;
	private OutlookURLLaunch outlookURLLaunch;
	
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
		
		loginScreen = new LoginPage();
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
    	outlookURLLaunch=siteHomeScreen.createTask();
    	outlookloginScreen=outlookURLLaunch.launchOutLookURL();
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