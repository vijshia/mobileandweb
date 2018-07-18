package com.kone.app.pages.vbmobile;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;
import com.kone.framework.utility.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class CreateTaskinSurveyManagerPage  extends PhoneBasePage{
	
	public static AppiumDriver<MobileElement> driver;
	public static String SurveyIDtoLink;
	
	private By header_SurveyManager = By.xpath("//span[text()='Survey Manager']");
	private By btn_add = By.xpath("(//*[text()='add'])[last()]");
	private By lnk_Createnewofflineblanksurvey = By.xpath("//*[text()='Create new offline blank survey (DIO/LIO)']");
	private By dd_EquipmentType = By.xpath("(//*[text()='Equipment Type'])[last()]/../..");
	private By header_PlannedSurveyType = By.xpath("//*[text()='Planned Survey Type']");
	private By btn_CreateTask = By.xpath("//*[text()='Create Task']/..");
	
	
	public CreateTaskinSurveyManagerPage() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Creating Task in Survey Manager {0}")
	public SurveyPage CreateTaskinSurveyManager(String elevatorytype) { //FRB Elevator, AMP Elevator, TRB Elevator, C4L Escalator
		
		clickonButton(btn_add);
		waitForElementPresent(lnk_Createnewofflineblanksurvey, 30);
		clickonButton(lnk_Createnewofflineblanksurvey);
		waitForElementPresent(dd_EquipmentType, 30);
		clickonButton(dd_EquipmentType);
		waitForElementPresent(stringtoXpathEquals(elevatorytype), 30);
		clickonButton(stringtoXpathEquals(elevatorytype));
		waitForElementPresent(header_PlannedSurveyType, 30);
		clickonButton(btn_CreateTask);
		
		SurveyPage surveyScreen = new SurveyPage();
		Assert.assertTrue(surveyScreen.isDisplayed(DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT), 
				"Failed to open survey ");
		return surveyScreen;		
	}
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(header_SurveyManager, timeout) != null;
	}

}
