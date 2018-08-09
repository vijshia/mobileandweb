package com.kone.app.tests.testcases.vbmobile;

import java.util.ArrayList;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.kone.app.pages.vbmobile.SurveyManagerPage;
import com.kone.app.pages.vbmobile.SurveyPage;
import com.kone.app.pages.vbmobile.CreateTaskinSurveyManagerPage;
import com.kone.app.pages.vbmobile.UploadPage;
import com.kone.app.tests.testcases.VbmobileBaseTest;


public class VbmobileSurveytoLinkOpportunity extends VbmobileBaseTest{
    
	private SurveyManagerPage surveyManagerScreen;
	private CreateTaskinSurveyManagerPage CreateTaskinSurveyManagerPage;
	private SurveyPage surveyScreen;
	private UploadPage uploadPage;
		
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	protected String surveyName;
	protected ArrayList<String[]> surveyData;
	protected String Excel_ValuetoStore;
	protected String Mobile_MenutoSelect;
	
	public VbmobileSurveytoLinkOpportunity(ArrayList<String[]> surveyData) {
		
		this.surveyData = surveyData;
		surveyManagerScreen = new SurveyManagerPage();
	}
    
    @Test(groups= {"vbmobie_surveytolink"})
    @Parameters({"mobileMenutoSelect", "websiteselectplannedtype"})
    void answerSurveyTest(String mobileMenutoSelect, String websiteselectplannedtype) {
    	CreateTaskinSurveyManagerPage = surveyManagerScreen.openSideMenu(mobileMenutoSelect).surveyManager(mobileMenutoSelect);
    	surveyScreen = CreateTaskinSurveyManagerPage.CreateTaskinSurveyManager("FRB Elevator", websiteselectplannedtype);
//    	surveyScreen.answerSurvey(surveyData, mobileMenutoSelect, websiteselectplannedtype); 
    	uploadPage = surveyScreen.completeSurvey();
    	uploadPage.uploadSurvey();
    }
}