package com.kone.app.screens.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.kone.app.screens.WebBaseScreen;
import com.kone.app.screens.konesitesurvey.SiteLoginScreen;
import com.kone.framework.context.WebContext;
import ru.yandex.qatools.allure.annotations.Step;
import com.kone.framework.utility.ExcelReader;
import static com.kone.app.screens.konesitesurvey.SiteLoginScreen.excelPath;
import static com.kone.app.screens.konesitesurvey.SiteLoginScreen.excelData;

public class MainScreen extends WebBaseScreen {
	
    public static WebDriver wdriver;
    
    private By searchButton = By.id("phSearchButton");
	private By txt_Homesearchbox=By.id("phSearchInput");
	private By btn_Homesearchbox=By.id("phSearchButton");
	
	public static String SF_Opportunity_Name;
	
	public MainScreen() {
		wdriver = WebContext.wdriver;
	}
	
	@Step("Search for Existing Opportunity")
	public SelectOpportunityScreen searchOpportunity() {
		
		ExcelReader excelReader=new ExcelReader(excelPath);
		try {
			excelData=excelReader.GetData("Web_Login_Data");
			
			SF_Opportunity_Name=excelData.get("SF_Opportunity_Name").get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		enteringValueinTextField(txt_Homesearchbox, SF_Opportunity_Name);
		clickonButton(btn_Homesearchbox);
		
		SelectOpportunityScreen selectOpportunityScreen= new SelectOpportunityScreen();
		Assert.assertTrue(selectOpportunityScreen.isDisplayed());
		return selectOpportunityScreen;
	}	
	
	@Step("Check if the main page is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(searchButton, 15) != null;
	}
		
}
