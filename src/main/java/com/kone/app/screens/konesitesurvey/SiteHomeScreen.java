package com.kone.app.screens.konesitesurvey;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.kone.app.screens.WebBaseScreen;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.app.screens.salesforce.SelectOpportunityScreen.URL;
import com.kone.app.screens.salesforce.MainScreen;
import com.kone.framework.utility.ExcelReader;
import static com.kone.app.screens.konesitesurvey.SiteLoginScreen.excelPath;
import static com.kone.app.screens.konesitesurvey.SiteLoginScreen.excelData;

public class SiteHomeScreen extends WebBaseScreen{
	
	private By popup_toSelectLIS=By.xpath("//*[contains(text(),'(LIS)')]");
	private By tocheck_customerData=By.xpath("//*[text()='Customer Id SAP']/..//input");
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
	private By tocheck_selectPlannedTypes=By.xpath("//div[@ng-repeat='selectedType in selectedTypes track by $index']");
	private By btn_ok=By.id("typeSelectorOkBtn");
	private By btn_createTask=By.xpath("//*[contains(@ng-click,'createTask')]");
	
	private String MSS_CustomerContact;
	private String MSS_Street;
	private String MSS_PostalCode;
	private String MSS_City;
	
	@Step("Check if the Task has been created")
	public MainScreen createTask() {	
		
		ExcelReader excelReader=new ExcelReader(excelPath);
		try {
			excelData=excelReader.GetData("Web_Login_Data");
			
			MSS_CustomerContact=excelData.get("MSS_CustomerContact").get(0);
			MSS_Street=excelData.get("MSS_Street").get(0);
			MSS_PostalCode=excelData.get("MSS_PostalCode").get(0);
			MSS_City=excelData.get("MSS_City").get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<50; ) {
			String attribute=gettingAttributebyClass(tocheck_customerData);
		if(attribute.contains("ng-empty")) {
			i++;
		} else if(attribute.contains("ng-not-empty")) {
			break;
		}
	}
		clickonButton(btn_toNavigateLIO);
		waitForElementPresent(header_check, 20);
		/*String getHeaderText=gettingText(header_check);
		String[] headerSplit=getHeaderText.split(":");
		String head=headerSplit[3];
        String[] urltoSplit = URL.split("[m/?]");
        String one=urltoSplit[5].trim();
		if(getHeaderText.contains(one)) {*/
			waitForElementtobeClickable(txt_customerContact, 30);
			enteringValueinTextField(txt_customerContact, MSS_CustomerContact);
			enteringValueinTextField(txt_street, MSS_Street);
			enteringValueinTextField(txt_postalCode, MSS_PostalCode);
			enteringValueinTextField(txt_city, MSS_City);
			clickonButton(lookup_surveyor);
			waitForElementPresent(lnk_assigntoMe, 20);
			clickonButton(lnk_assigntoMe);
			clickonButton(lookup_plannedSType);
			waitForElementPresent(lnk_selectPlannedTypes, 10);
			clickonButton(lnk_selectPlannedTypes);
			waitForElementPresent(tocheck_selectPlannedTypes, 20);
			clickonButton(btn_ok);
			scrollDownJavaScript();
			clickonButton(btn_createTask);
		
		MainScreen salesForceMainScreen=new MainScreen();
		Assert.assertTrue(salesForceMainScreen.isDisplayed());
		return salesForceMainScreen;	
	}
	
	@Step("Check if the Select LIS or LIO Pop-up is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(popup_toSelectLIS, 15) != null;

	}
	

}
