package com.kone.app.tests.testcases.salesforce;

import java.util.HashMap;
import java.util.Map.Entry;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.kone.app.pages.konesitesurvey.SiteHomePage;
import com.kone.app.pages.konesitesurvey.SiteLoginPage;
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
/*	private OutlookLoginPage outlookloginScreen;
	private OutlookHomePage outlookHomeScreen;
	private OutlookURLLaunch outlookURLLaunch;*/
	
	protected String salesForceloginUser;
	protected String salesForceloginPassword;
	protected String siteloginUser;
	protected String sitePassword;
	protected String salesForceOpportunityName;
	protected String siteFrontlineCountry;
	protected String siteCustomerContact;
	protected String siteStreet;
	protected String sitePostalCode;
	protected String siteCity;
	protected String siteSelectPlannedTypes;
	protected String siteSelectSurveyType;
	protected String Excel_ValuetoStore;
	protected String salesForceenvironment;
	
//	public SalesforceAuthenticationTests(String salesForceUser, String salesForcepassword, String siteUsername, String sitePassword) {
	public SalesforceAuthenticationTests(HashMap<String, String> exceldatacombined) {
		
		for (Entry<String, String> entry : exceldatacombined.entrySet()) {
			String key = entry.getKey();
			Excel_ValuetoStore = entry.getValue();

				if (key.equals("SF_UserName")) {
					salesForceloginUser = Excel_ValuetoStore;
				} else if (key.equals("SF_Password")) {
					salesForceloginPassword = Excel_ValuetoStore;				
				}else if (key.equals("Site_UserName")) {
					siteloginUser = Excel_ValuetoStore;				
				}else if (key.equals("Site_Password")) {
					sitePassword = Excel_ValuetoStore;				
				}else if (key.equals("SF_Opportunity_Name")) {
					salesForceOpportunityName = Excel_ValuetoStore;				
				}else if (key.equals("MSS_Frontline_Country")) {
					siteFrontlineCountry = Excel_ValuetoStore;				
				}else if (key.equals("MSS_CustomerContact")) {
					siteCustomerContact = Excel_ValuetoStore;				
				}else if (key.equals("MSS_Street")) {
					siteStreet = Excel_ValuetoStore;				
				}else if (key.equals("MSS_PostalCode")) {
					sitePostalCode = Excel_ValuetoStore;				
				}else if (key.equals("MSS_City")) {
					siteCity = Excel_ValuetoStore;				
				}else if (key.equals("SF_Environment")) {
					salesForceenvironment = Excel_ValuetoStore;				
				}/*else if (key.equals("MSS_SelectPlannedTypes")) {
					siteSelectPlannedTypes = Excel_ValuetoStore;				
				}else if (key.equals("MSS_SurveyType")) {
					siteSelectSurveyType = Excel_ValuetoStore;				
				}*/
		}
		
		loginScreen = new LoginPage();
//		outlookloginScreen=new OutlookLoginScreen();
	}
	
    @Test(groups={ "web", "login_salesforce" })
    @Parameters({"mobileMenutoSelect", "websurveytype", "websiteselectplannedtype"})
    void loginSalesForce(String mobileMenutoSelect, String websurveytype, String websiteselectplannedtype) {
    	if(salesForceenvironment.equals("QA")) {
    		wdriver.navigate().to("https://kone--qa.cs85.my.salesforce.com/006/o");
    	} else if(salesForceenvironment.equals("FULL")) {
    		wdriver.navigate().to("https://test.salesforce.com/");
    	}
    	wdriver.manage().window().maximize();
    	mainScreen=loginScreen.signIn(this.salesForceenvironment, this.salesForceloginUser, this.salesForceloginPassword);
    	searchResultScreen=mainScreen.searchOpportunity(this.salesForceOpportunityName);
    	siteLoginScreen=searchResultScreen.clickonOpportunity(this.salesForceOpportunityName, mobileMenutoSelect);
    	siteHomeScreen=siteLoginScreen.siteSurveySignIn(this.siteloginUser, this.sitePassword, this.siteFrontlineCountry, mobileMenutoSelect);
    	/*outlookURLLaunch=*/siteHomeScreen.createTask(websurveytype, this.siteCustomerContact, this.siteStreet, this.sitePostalCode, this.siteCity, websiteselectplannedtype);
//    	outlookloginScreen=outlookURLLaunch.launchOutLookURL();
//    	outlookHomeScreen=outlookloginScreen.emailLogin(this.salesForceloginUser, this.sitePassword);
//    	outlookHomeScreen.getTaskID();
    }
		
/*		this.salesForceloginUser = salesForceUser;
		this.salesForceloginPassword = salesForcepassword;
		this.siteloginUser	= siteUsername;
		this.sitePassword	= sitePassword;
		
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
    }*/
    
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