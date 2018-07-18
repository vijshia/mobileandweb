package com.kone.app.pages.konesitesurvey;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.kone.app.pages.WebBasePage;
import ru.yandex.qatools.allure.annotations.Step;
import com.kone.framework.utility.Log;

public class SiteLinkSurvey extends WebBasePage{

	private By newPopUp=By.xpath("//*[@class='modal-body ng-scope']");
	private By btn_popUpClose=By.xpath("//*[@id='closeReleaseNotesBtn']");
	private By txt_search=By.xpath("//*[@ng-model='searchString' or @placeholder='Search tasks...']");
	private By btn_search=By.xpath("//*[@ng-click='searchTasks(searchString)']");
	private By table_elements=By.xpath("//*[@ng-repeat='task in tasks']/..//div");
	private By btn_ADDtoCRM=By.xpath("//*[@class='zmdi zmdi-cloud-upload']/..");
	
	@Step("Link the Survey created in VBMobile to the Oppornity")
	public SiteLinkSurvey linkSurveytoOpportunity(String taskid) {
		
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
			
			WebDriverWait wait = new WebDriverWait(wdriver, 30);
			wait.until(ExpectedConditions.attributeContains(txt_search, "class", "ng-not-empty"));
			Log.info("waited till search field attribute as not empty");
			clickonButton(btn_search);
			List<WebElement> tableelements = gettingWebElementsfromList(table_elements);
			for(WebElement tableelement: tableelements) {
				Log.info(tableelement.getText());
			}
			clickonButton(btn_ADDtoCRM);
		
		SiteLinkSurvey sitelinksurvey=new SiteLinkSurvey();
		return sitelinksurvey;
	}
}
