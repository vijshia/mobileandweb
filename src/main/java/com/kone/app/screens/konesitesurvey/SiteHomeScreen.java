package com.kone.app.screens.konesitesurvey;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.kone.app.screens.WebBaseScreen;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.app.screens.salesforce.SelectOpportunityScreen.URL;
import com.kone.app.screens.salesforce.MainScreen;

public class SiteHomeScreen extends WebBaseScreen{
	
	private By popup_toSelectLIS=By.xpath("//*[contains(text(),'(LIS)')]");
	private By btn_toNavigateLIO=By.xpath("//*[contains(text(),'LIO')]");
	private By header_check=By.xpath("//*[@ng-show='opportunityId']");
	private By txt_customerContact=By.xpath("(//*[@class='btn btn-default waves-effect']/../..//input)[last()-1]");
	private By txt_street=By.xpath("//*[text()='Street']/..//input");
	private By txt_postalCode=By.xpath("//*[text()='Postal Code']/..//input");
	private By txt_city=By.xpath("//*[text()='City']/..//input");
	private By lookup_surveyor=By.id("selectSurveyorBtn");
	private By lnk_assigntoMe=By.id("searchSurveyorSelectMeBtn");
	private By lookup_plannedSType=By.id("openTypeSelectorBtn");
	private By lnk_selectPlannedTypes=By.xpath("//*[contains(text(),'Full')]");
	private By btn_ok=By.id("typeSelectorOkBtn");
	private By btn_createTask=By.xpath("//*[contains(@ng-click,'createTask')]");
	
	@Step("Check if the Task has been created")
	public MainScreen createTask() {
		clickonButton(btn_toNavigateLIO);
		waitForElementPresent(header_check, 20);
		/*String getHeaderText=gettingText(header_check);
		String[] headerSplit=getHeaderText.split(":");
		String head=headerSplit[3];
        String[] urltoSplit = URL.split("[m/?]");
        String one=urltoSplit[5].trim();
		if(getHeaderText.contains(one)) {*/
			waitForElementtobeClickable(txt_customerContact, 30);
			enteringValueinTextField(txt_customerContact, "Test_A");
			enteringValueinTextField(txt_street, "street");
			enteringValueinTextField(txt_postalCode, "1234098765");
			enteringValueinTextField(txt_city, "chennai");
			clickonButton(lookup_surveyor);
			waitForElementPresent(lnk_assigntoMe, 20);
			clickonButton(lnk_assigntoMe);
			clickonButton(lookup_plannedSType);
			clickonButton(lnk_selectPlannedTypes);
			clickonButton(btn_ok);
			clickonButton(btn_createTask);
//		}
		
		MainScreen salesForceMainScreen=new MainScreen();
		Assert.assertTrue(salesForceMainScreen.isDisplayed());
		return salesForceMainScreen;	
	}
	
	@Step("Check if the Select LIS or LIO Pop-up is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(popup_toSelectLIS, 15) != null;

	}
	

}
