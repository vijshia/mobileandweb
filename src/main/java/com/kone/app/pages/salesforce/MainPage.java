package com.kone.app.pages.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.kone.app.pages.WebBasePage;
import com.kone.app.pages.konesitesurvey.SiteLoginPage;
import com.kone.framework.context.WebContext;
import ru.yandex.qatools.allure.annotations.Step;

public class MainPage extends WebBasePage {
	
    public static WebDriver wdriver;
    
    private By searchButton = By.id("phSearchButton");
	private By txt_Homesearchbox=By.id("phSearchInput");
	private By btn_Homesearchbox=By.id("phSearchButton");
	
	public MainPage() {
		wdriver = WebContext.wdriver;
	}
	
	@Step("Search for Existing Opportunity")
	public SelectOpportunityPage searchOpportunity(String salesforceopportunityname) {
		
		enteringValueinTextField(txt_Homesearchbox, salesforceopportunityname);
		clickonButton(btn_Homesearchbox);
		
		SelectOpportunityPage selectOpportunityScreen= new SelectOpportunityPage();
		Assert.assertTrue(selectOpportunityScreen.isDisplayed(salesforceopportunityname));
		return selectOpportunityScreen;
	}	
	
	@Step("Check if the main page is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(searchButton, 15) != null;
	}
		
}
