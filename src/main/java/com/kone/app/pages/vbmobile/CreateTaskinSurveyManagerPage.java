package com.kone.app.pages.vbmobile;

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
	public static By plannedSurveyTypeStatus = By.xpath("//*[contains(text(),'Replace')]/..");
	
	private By header_SurveyManager = By.xpath("//span[text()='Survey Manager']");
	private By btn_add = By.xpath("(//*[text()='add'])[last()]");
	private By lnk_Createnewofflineblanksurvey = By.xpath("//*[text()='Create new offline blank survey (DIO/LIO)']");
	private By dd_EquipmentType = By.xpath("(//*[text()='Equipment Type'])[last()]/../..");
	private By header_PlannedSurveyType = By.xpath("//*[text()='Planned Survey Type']");
	private By btn_CreateTask = By.xpath("//*[text()='Create Task']/..");
	private By checkbox_FRBFull=By.xpath("//*[contains(text(),'FRB Full')]/../div[@class='md-container']");
	private By checkbox_FRBBudget=By.xpath("//*[contains(text(),'FRB Budget')]/../div[@class='md-container']");	
	
	public CreateTaskinSurveyManagerPage() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	@Step("Creating Task in Survey Manager {0}")
	public SurveyPage CreateTaskinSurveyManager(String elevatorytype, String websiteselectplannedtype) { //FRB Elevator, AMP Elevator, TRB Elevator, C4L Escalator
		
		waitForElementClickable(btn_add, 30);
		clickonButton(btn_add);
		waitForElementPresent(lnk_Createnewofflineblanksurvey, 30);
		clickonButton(lnk_Createnewofflineblanksurvey);
		waitForElementPresent(dd_EquipmentType, 30);
		clickonButton(dd_EquipmentType);
		waitForElementPresent(stringtoXpathEquals(elevatorytype), 30);
		clickonButton(stringtoXpathEquals(elevatorytype));
		waitForElementPresent(header_PlannedSurveyType, 30);
			if(websiteselectplannedtype.equalsIgnoreCase("FRB Full")) {
				Log.info("PlannedSurveyType in testdata given as FRB Full");
				String isCheckboxChecked=gettingMobileElement(stringtoXpathContains(websiteselectplannedtype)).getAttribute("aria-checked");
				Log.info("PlannedSurveyType in application is: "+isCheckboxChecked);
					if(isCheckboxChecked.equals("false")) {
						clickonButton(checkbox_FRBFull);
					}
				Assert.assertTrue(isCheckboxChecked.equals("true"), "FRB Full checkbox is Checked");
			} else if(websiteselectplannedtype.equalsIgnoreCase("FRB Budget")) {
				Log.info("PlannedSurveyType in testdata given as FRB Budget");
				clickonButton(checkbox_FRBFull);
				clickonButton(checkbox_FRBBudget);
				String isCheckboxChecked=gettingMobileElement(stringtoXpathContains(websiteselectplannedtype)).getAttribute("aria-checked");
				String isCheckboxChecked_Full=gettingMobileElement(stringtoXpathContains("FRB Full")).getAttribute("aria-checked");
				Log.info("Planned Survey Type in application is: "+isCheckboxChecked+" and FRB Full is: "+isCheckboxChecked_Full);
				Assert.assertTrue(isCheckboxChecked.equals("true") && isCheckboxChecked_Full.equals("false"), "FRB Budget checkbox is Checked & FRB Full checkbox is Unchecked");
			}
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
