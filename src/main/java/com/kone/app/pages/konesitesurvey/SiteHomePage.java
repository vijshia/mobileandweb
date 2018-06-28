package com.kone.app.pages.konesitesurvey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.kone.app.pages.WebBasePage;
import com.kone.app.pages.outlook.OutlookURLLaunch;
import com.kone.app.pages.salesforce.MainPage;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.app.pages.salesforce.SelectOpportunityPage.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.kone.framework.utility.ExcelReader;
import com.kone.framework.utility.Log;

public class SiteHomePage extends WebBasePage{
	
	private By popup_toSelectLIS=By.xpath("//*[contains(text(),'(LIS)')]");
	private By tocheck_customerData=By.xpath("//*[text()='Customer Id SAP']/..//input");  //*[@class='form-control ng-pristine ng-untouched ng-valid ng-not-empty']
	private By tocheck_customerDatas=By.xpath("//*[text()='Customer Id SAP']/..//input[contains(@class, 'ng-not-empty')]");
	private By btn_toNavigateLIO=By.xpath("//*[contains(text(),'LIO')]");
	private By header_check=By.xpath("//*[@ng-show='opportunityId']");
	private By txt_customerContact=By.xpath("(//*[@class='btn btn-default waves-effect']/../..//input)[last()-1]");
	private By txt_street=By.xpath("//*[text()='Street']/..//input");
	private By txt_postalCode=By.xpath("//*[text()='Postal Code']/..//input");
	private By txt_city=By.xpath("//*[text()='City']/..//input");
	private By lookup_surveyor=By.id("selectSurveyorBtn");
	private By lnk_assigntoMe=By.id("searchSurveyorSelectMeBtn");
	private By lookup_plannedSType=By.id("openTypeSelectorBtn");
	public static By lnk_selectPlannedTypes=By.xpath("//*[contains(text(),'Replace')]");
	private By tocheck_selectPlannedTypes=By.xpath("//div[@ng-repeat='selectedType in selectedTypes track by $index']");
	private By btn_ok=By.id("typeSelectorOkBtn");
	private By btn_createTask=By.xpath("//*[contains(@ng-click,'createTask')]");
	private By newPopUp=By.xpath("//*[@class='modal-body ng-scope']");
	private By btn_popUpClose=By.xpath("//*[@id='closeReleaseNotesBtn']");
	
	public static String MSS_Street;
	public static String dateformat;
	
	@Step("Check if the Task has been created")
	public OutlookURLLaunch createTask(String sitecustomercontact, String sitestreet, String sitepostalcode, String sitecity, String siteselectplannedtypes) {	
		
		dateformat = new SimpleDateFormat("ddMMMhhmm_ssaa").format(Calendar.getInstance().getTime());
		MSS_Street=sitestreet+"_"+dateformat;
		
/*		for(int i=0; i<30; ) {
			String attribute=gettingAttributebyClass(tocheck_customerData);
		if(attribute.contains("ng-empty")) {
			i++;
		} else if(attribute.contains("ng-not-empty")) {
			break;
		}
	}*/
		waitForElementPresent(newPopUp, 50);
		WebElement newpopupFooter=gettingWebElement(newPopUp);
	    if(newpopupFooter.isDisplayed()) {
	    	clickonButton(btn_popUpClose);
	    }
		waitForElementPresent(tocheck_customerDatas, 30);
		clickonButton(btn_toNavigateLIO);
		waitForElementPresent(header_check, 20);
		/*String getHeaderText=gettingText(header_check);
		String[] headerSplit=getHeaderText.split(":");
		String head=headerSplit[3];
        String[] urltoSplit = URL.split("[m/?]");
        String one=urltoSplit[5].trim();
		if(getHeaderText.contains(one)) {*/
			waitForElementtobeClickable(txt_customerContact, 30);
			enteringValueinTextField(txt_customerContact, sitecustomercontact);
			enteringValueinTextField(txt_street, MSS_Street);
			enteringValueinTextField(txt_postalCode, sitepostalcode);
			enteringValueinTextField(txt_city, sitecity);
			clickonButton(lookup_surveyor);
			waitForElementPresent(lnk_assigntoMe, 20);
			clickonButton(lnk_assigntoMe);
			clickonButton(lookup_plannedSType);
			waitForElementPresent(stringtoXpathContains(siteselectplannedtypes), 10);
			clickonButton(stringtoXpathContains(siteselectplannedtypes));
			waitForElementPresent(tocheck_selectPlannedTypes, 20);
			clickonButton(btn_ok);
			scrollDownJavaScript();
			clickonButton(btn_createTask);
			waitForElementPresent(stringtoXpathContains("Successfully created task"), 30);
			Assert.assertTrue(gettingWebElement(By.xpath("//*[text()='Successfully created task']")).isDisplayed());
			
		OutlookURLLaunch outlookURLLaunch=new OutlookURLLaunch();
		Assert.assertTrue(outlookURLLaunch.isDisplayed());
		return outlookURLLaunch;
	}
	
	@Step("Check if the Select LIS or LIO Pop-up is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(popup_toSelectLIS, 50) != null;

	}
}
