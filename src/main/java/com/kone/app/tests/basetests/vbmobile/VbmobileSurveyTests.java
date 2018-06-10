package com.kone.app.tests.basetests.vbmobile;

import org.testng.annotations.Test;

import com.kone.app.screens.vbmobile.LoginScreen;
import com.kone.app.screens.vbmobile.SurveyManagerScreen;
import com.kone.app.screens.vbmobile.SurveyScreen;
import com.kone.app.screens.vbmobile.SideMenuScreen;
import com.kone.app.screens.vbmobile.TasksScreen;
import com.kone.app.tests.basetests.VbmobileBaseTest;


public class VbmobileSurveyTests extends VbmobileBaseTest{
    
	private SurveyManagerScreen surveyManagerScreen;
	private TasksScreen tasksScreen;
	private SurveyScreen surveyScreen;
	
	protected String loginUser;
	protected String loginPassword;
	protected String frontline;
	protected String taskId = "28708";  /* Temporarily set here, should be fetched from input excel */
	
	public VbmobileSurveyTests(String user, String password, String frontline) {
		
		this.loginUser = user;
		this.loginPassword = password;
		this.frontline = frontline;
		
		surveyManagerScreen = new SurveyManagerScreen();
	}
    
    @Test(groups= {"survey"})
    void answerSurveyTest() {
    	tasksScreen = surveyManagerScreen.
    					openSideMenu().
    					openTasks();
    	tasksScreen.downloadTaksById(taskId);
    	surveyScreen = surveyManagerScreen.openSurveyById(taskId);
    	
    	// 1. Frontline Data OMA
    	surveyScreen.openSection("Frontline Data OMA");
    	
    	surveyScreen.answerFromDropdown("Branch Number", "San Antonio");
    	
    	// 2. Elevator Data
    	surveyScreen.openSection("Elevator Data");
    	
    	surveyScreen.answerWithNumber("Group size", 13);
    	
    	// 3. Building Entrance
    	surveyScreen.openSection("Building Entrance");
    	
    	surveyScreen.answerRadioButtonQuestion("Parking place for unloading", "Yes");
    	surveyScreen.answerRadioButtonQuestion("Is there minimum 10 m2 within 10 m of the buiding", "No");
    	surveyScreen.answerRadioButtonQuestion("Is there minimum 10m2 within 10m inside the building", "Yes");
    	surveyScreen.answerRadioButtonQuestion("Is there extra effort needed", "No");
    	surveyScreen.answerFromDropdown("Building segmentation", "Hotel");
    	surveyScreen.answerRadioButtonQuestion("Does the building have intercom?", "Yes");    	
    	surveyScreen.answerMultipleChoices("Does the building have access control or PIN-code system?",
    			                            new String[]{"No electric locks","PIN-code"});
    	
    	// 4.1 Car - Layout
    	surveyScreen.openSection("Car - Layout");
    	
    	surveyScreen.answerWithNumber("Unit Identification", 15);
    	surveyScreen.answerWithNumber("Car load", 1500);
    	surveyScreen.answerWithNumber("Person", 10);
    	surveyScreen.answerWithNumber("(BB) Car width", 1500);
    	surveyScreen.answerWithNumber("(DD) Car depth", 2000);
    	surveyScreen.answerWithNumber("(CH) Car height", 1500);
    	surveyScreen.answerWithNumber("(LL) Door A width", 1800);
    	surveyScreen.answerFromDropdown("Elevator usage", "Vehicle");
    	
    	// 5.1 Landing - Layout
    	surveyScreen.openSection("Landing - Layout");
    	
    	surveyScreen.answerWithNumber("Shaft wall thickness, A side", 300);
    	surveyScreen.answerWithNumber("Shaft wall thickness, B side", 310);
    	surveyScreen.answerWithNumber("Shaft wall thickness, C side", 320);
    	surveyScreen.answerWithNumber("Shaft wall thickness, D side", 330);
    	
    	// 5.3 Landing - Doors
    	surveyScreen.openSection("Landing - Doors");
    	
    	surveyScreen.answerRadioButtonQuestion("A-side Door type", "Manual");

    	// 6.1 Car Top
    	surveyScreen.openSection("Car Top - Overhead");
    	
    	surveyScreen.answerFromDropdown("Shaft (B-side)", "Leca brick");
    	surveyScreen.answerFromDropdown("Shaft (C-side)", "Dry wall");
    	surveyScreen.answerFromDropdown("Shaft (D-side)", "Hollow bricks");
    	surveyScreen.answerRadioButtonQuestion("New Hook required", "No");
    	surveyScreen.answerFromDropdown("Location for new controller, upper.", "MR");
    	surveyScreen.answerRadioButtonQuestion("Preferred machine location", "D-side");
    	
    	// 7.1 Machine Room - Layout
    	surveyScreen.openSection("Machine Room - Layout");
    	
    	surveyScreen.answerFromDropdownWithSearch("Manufacturing year", "2009");
    	surveyScreen.answerFromDropdown("Subcategory", "Passenger hydro PH");
    	surveyScreen.answerFromDropdown("Route to machine room door", "Ladder");
    	surveyScreen.answerRadioButtonQuestion("Machine room door type", "Trapdoor");
    	surveyScreen.answerWithNumber("Machine room door height", 2000);
    	surveyScreen.answerWithNumber("Machine room door width", 1200);
    	surveyScreen.answerRadioButtonQuestion("Machine room safety balustrades", "Yes");
    	surveyScreen.answerRadioButtonQuestion("Machine room slab strengthening", "Yes");
    	surveyScreen.answerRadioButtonQuestion("Sufficient CE/load marked", "Yes");
    	surveyScreen.answerWithNumber("Current machine room free height", 2200);
    	surveyScreen.answerRadioButtonQuestion("Current machine bed material", "Beam");
    	surveyScreen.answerWithNumber("Current machine bed height", 1200);
    	surveyScreen.answerWithNumber("Needed wiring distance from control", 15);
    	surveyScreen.answerWithNumber("Needed wiring distance from external", 50);
    	surveyScreen.answerWithNumber("Riser fuses", 2000);
    	
    	// 7.2 Machinery
    	surveyScreen.openSection("Machinery");
    	
    	surveyScreen.answerFromDropdown("Roping suspension", "X:1");
    	surveyScreen.answerWithNumber("Speed", 30);
    	surveyScreen.answerWithNumber("Close / Create holes", 150);
    	
    	// 8 Under the Car - Pit
    	surveyScreen.openSection("Under the Car - Pit");
    	
    	surveyScreen.answerFromDropdown("Location for new controller, lower", "BOTTOM");
    	surveyScreen.answerRadioButtonQuestion("Water marks in pit", "Yes");
    	
    	// 9 Other comments
    	surveyScreen.openSection("Other comments");
    	
    	surveyScreen.answerWithText("Frontline Data Comment", "Not bad");
    	surveyScreen.answerWithText("Building Entrance Comment", "Very good");
    	surveyScreen.answerWithText("Car inside comments", "Well maintained");
    	surveyScreen.answerWithText("Landing comments", "Very good");
    	surveyScreen.answerWithText("Machine room comments", "Very good");
    	surveyScreen.answerWithText("Shaft comments", "Very good");
    	surveyScreen.answerWithText("Pit Comment", "Very good");
    	surveyScreen.answerWithText("Other comments", "No comment");
    	
    	surveyScreen.completeSurvey();
    }
}