package com.kone.app.pages.konesitesurvey;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.kone.app.pages.WebBasePage;
import ru.yandex.qatools.allure.annotations.Step;

import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;

public class SiteLinkSurvey extends WebBasePage{
	
	public SiteLinkSurvey(){
		wdriver = WebContext.wdriver;
	}	

	private By newPopUp=By.xpath("//*[@class='modal-body ng-scope']");
	private By btn_popUpClose=By.xpath("//*[@id='closeReleaseNotesBtn']");
	private By txt_search=By.xpath("//*[@ng-model='searchString' or @placeholder='Search tasks...']");
	private By btn_search=By.xpath("//*[@ng-click='searchTasks(searchString)']");
	private By table_elements=By.xpath("//*[@ng-repeat='task in tasks']/..//div");
	private By btn_ADDtoCRM=By.xpath("//*[@class='zmdi zmdi-cloud-upload']/..");
	private By successApproveText=By.xpath("//*[text()='Task successfully attached.']");
	
	@Step("Link the Survey created in VBMobile to the Oppornity")
	public void linkSurveytoOpportunity(String taskid) {
		
		waitForElementPresent(newPopUp, 50);
		WebElement newpopupFooter=gettingWebElement(newPopUp);
		    if(newpopupFooter.isDisplayed()) {
		    	waitForElementPresent(btn_popUpClose, 60);
		    	waitForElementtobeClickable(btn_popUpClose, 60);
		    	clickonButton(btn_popUpClose);
		    }
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
//					condition1=true;
					break;
				}/*else if(gettaskid.equals("ADD TO CRM  VIEW SURVEY")) {
					if(i==2) {
						WebDriverWait wait = new WebDriverWait(wdriver, 30);
						wait.until(ExpectedConditions.elementToBeClickable(tableelement));
						Log.info("Element is avilable now to click");
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
	}

	@Step("Check if the SiteSurvey Home screen to Link Survey is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(newPopUp, 15) != null;
	}
}
