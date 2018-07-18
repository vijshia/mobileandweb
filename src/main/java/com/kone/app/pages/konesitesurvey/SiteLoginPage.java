package com.kone.app.pages.konesitesurvey;

import com.kone.app.pages.WebBasePage;
import com.kone.framework.context.WebContext;
import com.kone.framework.utility.ExcelReader;
import com.kone.framework.utility.Log;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.app.pages.salesforce.LoginPage.wdriver;
import static org.testng.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SiteLoginPage extends WebBasePage {
	
	public SiteLoginPage() {
		wdriver = WebContext.wdriver;
	}	

	public static By lnk_country=By.xpath("//em[text()='Replace']"); //By.xpath("(//*[starts-with(text(),'Oman')])[last()]");
	
	private By txt_userName = By.id("username");
	private By txt_password = By.id("password");
	private By lookup_frontLine = By.id("frontline_chosen");
	private By txt_frontLine=By.xpath("(//*[@class='chosen-search'])[last()-1]/*[@type='text']");
	private By btn_login=By.id("loginBtn");
	
	//-------------------------------
	private By newPopUp=By.xpath("//*[@class='modal-body ng-scope']");
	private By btn_popUpClose=By.xpath("//*[@id='closeReleaseNotesBtn']");
	private By txt_search=By.xpath("//*[@ng-model='searchString' or @placeholder='Search tasks...']");
	private By btn_search=By.xpath("//*[@ng-click='searchTasks(searchString)']");
	private By table_elements=By.xpath("//*[@ng-repeat='task in tasks']/..//div");
	private By btn_ADDtoCRM=By.xpath("//*[@class='zmdi zmdi-cloud-upload']/..");
	//-------------------------------

	@Step("Login to Mobile Site Survey ")
	public SiteHomePage siteSurveySignIn(String username, String password, String sitefrontlinecountry, String mobileMenutoSelect) {
		
		waitForElementPresent(txt_userName, 10);
		enteringValueinTextField(txt_userName, username);
		enteringPassword(txt_password, password);
		
		// Work around, need to implement absolute fix later.
/*		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		clickonButton(lookup_frontLine);
		waitForElementPresent(txt_frontLine, 30);
		enteringValueinTextField(txt_frontLine, sitefrontlinecountry);
		clickonButton(stringtoXpathEqualssitefrontlinecountry(sitefrontlinecountry));
		/*waitForElementPresent(txt_frontLine, 10);
		String frontLine=gettingText(txt_frontLine);*/
		clickonButton(btn_login);
		
		//--------------------------------------------
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
			enteringValueinTextField(txt_search, "27114");
			
			WebDriverWait wait = new WebDriverWait(wdriver, 30);
			wait.until(ExpectedConditions.attributeContains(txt_search, "class", "ng-not-empty"));
			Log.info("waited till search field attribute as not empty");
			clickonButton(btn_search);
			waitForElementPresent(table_elements, 80);

			clickonButton(btn_ADDtoCRM);
		//--------------------------------------------
		
		SiteHomePage siteHomeScreen=new SiteHomePage();
		Assert.assertTrue(siteHomeScreen.isDisplayed(mobileMenutoSelect));
		return siteHomeScreen;
	}

	@Step("Check if the Site Survey Login screen is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(txt_userName, 15) != null;

	}
}
