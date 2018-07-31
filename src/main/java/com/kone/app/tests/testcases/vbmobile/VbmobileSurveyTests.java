package com.kone.app.tests.testcases.vbmobile;

import java.util.ArrayList;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.kone.app.pages.konesitesurvey.SiteHomePage;
import com.kone.app.pages.vbmobile.SurveyManagerPage;
import com.kone.app.pages.vbmobile.SurveyPage;
import com.kone.app.pages.vbmobile.TasksPage;
import com.kone.app.pages.vbmobile.UploadPage;
import com.kone.app.tests.testcases.VbmobileBaseTest;



public class VbmobileSurveyTests extends VbmobileBaseTest{
    
	private SurveyManagerPage surveyManagerScreen;
	private TasksPage tasksScreen;
	private SurveyPage surveyScreen;
	private UploadPage uploadPage;
	
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	protected String surveyName;
	protected ArrayList<String[]> surveyData;
	protected String Excel_ValuetoStore;
	protected String Mobile_MenutoSelect;
	
	public VbmobileSurveyTests(ArrayList<String[]> surveyData) {
		
		this.surveyData = surveyData;
		surveyManagerScreen = new SurveyManagerPage();
	}
    
    @Test(groups= {"vbmobie_survey"})
    @Parameters({"mobileMenutoSelect"})
    void answerSurveyTest(String mobileMenutoSelect) {
    	mobileMenutoSelect = "New Task";
    	tasksScreen = surveyManagerScreen.
    					openSideMenu(mobileMenutoSelect).
    					openTasks();
    	surveyName = SiteHomePage.MSS_Street;
    	tasksScreen.downloadTaksById(surveyName);
    	surveyScreen = surveyManagerScreen.openSurvey(surveyName);
//    	surveyScreen.answerSurvey(surveyData, mobileMenutoSelect);
    	uploadPage = surveyScreen.completeSurvey();
    	uploadPage.uploadSurvey();
    }
}