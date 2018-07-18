package com.kone.app.tests.testcases.vbmobile;

import java.util.ArrayList;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.kone.app.pages.vbmobile.SurveyManagerPage;
import com.kone.app.pages.vbmobile.SurveyPage;
import com.kone.app.pages.vbmobile.CreateTaskinSurveyManagerPage;
import com.kone.app.pages.vbmobile.RetriveSurveyIDtoLinkPage;
import com.kone.app.pages.vbmobile.UploadPage;
import com.kone.app.tests.testcases.VbmobileBaseTest;


public class VbmobileSurveytoLinkOpportunity extends VbmobileBaseTest{
    
	private SurveyManagerPage surveyManagerScreen;
	private CreateTaskinSurveyManagerPage CreateTaskinSurveyManagerPage;
	private SurveyPage surveyScreen;
	private UploadPage uploadPage;
	private RetriveSurveyIDtoLinkPage retrivesurveyIDtolinkPage;
	
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
    
    @Test(groups= {"surveytolink"})
    @Parameters({"mobileMenutoSelect"})
    void answerSurveyTest(String mobileMenutoSelect) {
    	CreateTaskinSurveyManagerPage = surveyManagerScreen.openSideMenu(mobileMenutoSelect).surveyManager(mobileMenutoSelect);
    	surveyScreen = CreateTaskinSurveyManagerPage.CreateTaskinSurveyManager("FRB Elevator");
//    	surveyScreen.answerSurvey(surveyData, mobileMenutoSelect);
    	uploadPage = surveyScreen.completeSurvey();
    	uploadPage.uploadSurvey();
    	retrivesurveyIDtolinkPage.RetriveSurveyIDtoLink();
    }
}