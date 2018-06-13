package com.kone.app.tests.basetests.vbmobile;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.kone.app.screens.vbmobile.SurveyManagerScreen;
import com.kone.app.screens.vbmobile.SurveyScreen;
import com.kone.app.screens.vbmobile.TasksScreen;
import com.kone.app.tests.basetests.VbmobileBaseTest;


public class VbmobileSurveyTests extends VbmobileBaseTest{
    
	private SurveyManagerScreen surveyManagerScreen;
	private TasksScreen tasksScreen;
	private SurveyScreen surveyScreen;
	
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	protected String taskId = "28708";  /* Temporarily set here, should be fetched from salesforce test */
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
    	tasksScreen.downloadTaksById(taskId);
    	surveyScreen = surveyManagerScreen.openSurveyById(taskId);
    	surveyScreen.answerSurvey(surveyData);
    	surveyScreen.completeSurvey();
    }
}