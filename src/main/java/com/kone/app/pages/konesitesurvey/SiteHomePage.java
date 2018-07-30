package com.kone.app.pages.konesitesurvey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.kone.app.pages.WebBasePage;
import com.kone.app.pages.outlook.OutlookURLLaunch;
import com.kone.app.pages.salesforce.MainPage;
import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;

import ru.yandex.qatools.allure.annotations.Step;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SiteHomePage extends WebBasePage{
	
	public SiteHomePage(){
		wdriver = WebContext.wdriver;
	}	
	
	private By popup_toSelectLIS=By.xpath("//*[contains(text(),'(LIS)')]");
	private By header_SiteSurveyLink=By.xpath("//*[starts-with(text(),'Create Surveying Task(s) for account:') and contains(text(),'Opportunity:')]");
//	private By tocheck_customerData=By.xpath("//*[text()='Customer Id SAP']/..//input");  //*[@class='form-control ng-pristine ng-untouched ng-valid ng-not-empty']
	private By tocheck_customerDatas=By.xpath("//*[text()='Customer Id SAP']/..//input[contains(@class, 'ng-not-empty')]");
/*	private By btn_search=By.xpath("//*[@ng-click='searchEquipment()']");
	private By spinner=By.id("loading-bar-spinner");*/
	private By btn_toNavigateLIO=By.xpath("//*[contains(text(),'LIO')]");
	private By checkbox_LISEquipments=By.xpath("//*[starts-with(@ng-click,'selectEquipments') and @type='checkbox']");
	private By btn_LISOk=By.xpath("//*[text()=' OK']");
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

	private By txt_search=By.xpath("//*[@ng-model='searchString' or @placeholder='Search tasks...']");
	private By btn_search=By.xpath("//*[@ng-click='searchTasks(searchString)']");
	private By table_elements=By.xpath("//*[@ng-repeat='task in tasks']/..//div");
	private By btn_ADDtoCRM=By.xpath("//*[@class='zmdi zmdi-cloud-upload']/..");
	private By successApproveText=By.xpath("//*[text()='Task successfully attached.']");
	private By lnk_SiteLogout=By.xpath("//*[text()=' Logout']/..");
	
	public static String MSS_Street;
	public static String dateformat;
	
	@Step("Check if the Task has been created")
	public OutlookURLLaunch createTask(String surveytype, String sitecustomercontact, String sitestreet, String sitepostalcode, String sitecity, String siteselectplannedtypes) {	
		
		dateformat = new SimpleDateFormat("ddMMMhhmm_ssaa").format(Calendar.getInstance().getTime());
		MSS_Street=sitestreet+"_"+dateformat;
		
//		isReleaseNotePopUpDisplaying();
		
		waitForpresenceOfElementLocated(tocheck_customerDatas, 60);
		if(surveytype.equalsIgnoreCase("LIS")) {
//			waitForElementPresent(checkbox_LISEquipments, 30);
			List<WebElement> equipments=gettingWebElementsfromList(checkbox_LISEquipments);
			for(WebElement equipment: equipments) {
				equipment.click();
				break;
			}
			waitForElementtobeClickable(btn_LISOk, 30);
			clickonButton(btn_LISOk);
		} else if(surveytype.equalsIgnoreCase("LIO")) {
			clickonButton(btn_toNavigateLIO);
		}
			waitForElementPresent(header_check, 20);
			waitForElementtobeClickable(txt_customerContact, 30);
			if(gettingWebElement(txt_customerContact).getAttribute("class").contains("ng-not-empty")) {
				clearValue(txt_customerContact);
			}
			enteringValueinTextField(txt_customerContact, sitecustomercontact);
			if(gettingWebElement(txt_street).getAttribute("class").contains("ng-not-empty")) {
				clearValue(txt_street);
			}
			enteringValueinTextField(txt_street, MSS_Street);
			if(gettingWebElement(txt_postalCode).getAttribute("class").contains("ng-empty")) {
				enteringValueinTextField(txt_postalCode, sitepostalcode);
			}
			if(gettingWebElement(txt_city).getAttribute("class").contains("ng-not-empty")) {
				clearValue(txt_city);
			}
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
	
	
	@Step("Link the Survey created in VBMobile to the Opportunity")
	public void linkSurveytoOpportunity(String taskid) {
		
//		isReleaseNotePopUpDisplaying();
		
	    waitForElementPresent(txt_search, 30);
			if(gettingWebElement(txt_search).getAttribute("class").contains("ng-not-empty")) {
				clearValue(txt_search);
			}
		enteringValueinTextField(txt_search, taskid);
		waitForElementContainsAttribute(txt_search, 30, "class", "ng-not-empty");
		clickonButton(btn_search);
		waitForElementPresent(table_elements, 40);
		List<WebElement> tableelements = gettingWebElementsfromList(table_elements);
/*		Boolean condition1=false;
		Boolean condition2=false;
		int i=1;*/
			for(WebElement tableelement: tableelements) {
				String gettaskid=tableelement.getText();
				if(gettaskid.equals(taskid)) {
					Log.info("taskID: "+gettaskid+" identified in table");
					Assert.assertTrue(gettaskid.equals(taskid));
//					condition1=true;
					break;
				}/*else if(gettaskid.equals("ADD TO CRM  VIEW SURVEY")) {
					if(i==2) {
						WebDriverWait wait = new WebDriverWait(wdriver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(tableelement));
						Log.info("Element is available now to click");
						tableelement.click();
						condition2=true;
					} i++;
				}
				if(condition1 && condition2 == true) {
					Log.info("both the conditions are true");
					break;
				}*/
			}
		clickonButton(btn_ADDtoCRM);
		waitForElementPresent(successApproveText, 180);
		waitForElementNotPresent(successApproveText, 180);
		wdriver.findElement(lnk_SiteLogout).click();
		
		MainPage mainScreen = new MainPage();
		Assert.assertTrue(mainScreen.isDisplayed(), "Failed to logout SiteSurvey");
	}
	
	@Step("Release Note Pop-up displaying in Site Survey Home Screen")
	public void isReleaseNotePopUpDisplaying() {
		
		waitForElementPresent(newPopUp, 50);
		WebElement newpopupFooter=gettingWebElement(newPopUp);
		    if(newpopupFooter.isDisplayed()) {
		    	waitForElementPresent(btn_popUpClose, 60);
		    	waitForElementtobeClickable(btn_popUpClose, 60);
		    	clickonButton(btn_popUpClose);
		    }
	}   
	
	@Step("Check if the Select LIS/LIO or Create Survey header is displayed")
	public boolean isDisplayed(String mobileMenutoSelect) {
		By headertoCheck;
		if(mobileMenutoSelect.equals("Survey Manager")) {
			headertoCheck= header_SiteSurveyLink;
		} else {
			headertoCheck= popup_toSelectLIS;
		}
		return waitForElementPresent(headertoCheck, 180) != null;
	}
}
