package com.kone.app.tests.basetests.vbmobile;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.kone.app.screens.konesitesurvey.SiteHomeScreen;
import com.kone.app.screens.vbmobile.SurveyManagerScreen;
import com.kone.app.screens.vbmobile.SurveyScreen;
import com.kone.app.screens.vbmobile.TasksScreen;
import com.kone.app.tests.basetests.VbmobileBaseTest;
import com.kone.framework.utility.Log;


public class VbmobileSurveyTests extends VbmobileBaseTest{
    
	private SurveyManagerScreen surveyManagerScreen;
	private TasksScreen tasksScreen;
	private SurveyScreen surveyScreen;
	
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	protected String surveyName;
	protected ArrayList<String[]> surveyData;
	
	public VbmobileSurveyTests(ArrayList<String[]> surveyData) {
		
		this.surveyData = surveyData;
		surveyManagerScreen = new SurveyManagerScreen();
	}
    
    @Test(groups= {"survey"})
    void answerSurveyTest() {
    	tasksScreen = surveyManagerScreen.
    					openSideMenu().
    					openTasks();
    	surveyName = SiteHomeScreen.MSS_Street;
    	tasksScreen.downloadTaksById(surveyName);
    	surveyScreen = surveyManagerScreen.openSurvey(surveyName);
    	surveyScreen.answerSurvey(surveyData);
    	surveyScreen.completeSurvey();
    }
}