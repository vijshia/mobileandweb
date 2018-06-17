package com.kone.app.tests.testcases.vbmobile;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.kone.app.pages.konesitesurvey.SiteHomePage;
import com.kone.app.pages.vbmobile.SurveyManagerPage;
import com.kone.app.pages.vbmobile.SurveyPage;
import com.kone.app.pages.vbmobile.TasksPage;
import com.kone.app.tests.testcases.VbmobileBaseTest;
import com.kone.framework.utility.Log;


public class VbmobileSurveyTests extends VbmobileBaseTest{
    
	private SurveyManagerPage surveyManagerScreen;
	private TasksPage tasksScreen;
	private SurveyPage surveyScreen;
	
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	protected String surveyName;
	protected ArrayList<String[]> surveyData;
	
	public VbmobileSurveyTests(ArrayList<String[]> surveyData) {
		
		this.surveyData = surveyData;
		surveyManagerScreen = new SurveyManagerPage();
	}
    
    @Test(groups= {"survey"})
    void answerSurveyTest() {
    	tasksScreen = surveyManagerScreen.
    					openSideMenu().
    					openTasks();
    	surveyName = SiteHomePage.MSS_Street;
    	tasksScreen.downloadTaksById(surveyName);
    	surveyScreen = surveyManagerScreen.openSurvey(surveyName);
    	surveyScreen.answerSurvey(surveyData);
    	surveyScreen.completeSurvey();
    }
}