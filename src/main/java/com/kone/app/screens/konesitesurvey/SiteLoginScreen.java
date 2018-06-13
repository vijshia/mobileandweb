package com.kone.app.screens.konesitesurvey;

import com.kone.app.screens.WebBaseScreen;
import com.kone.framework.context.WebContext;
import com.kone.framework.utility.ExcelReader;
import com.kone.framework.utility.Log;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.app.screens.salesforce.LoginScreen.wdriver;
import static org.testng.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SiteLoginScreen extends WebBaseScreen {
	
	public static String excelPath=System.getProperty("user.dir") + "\\properties\\testdata\\KONEMobileSiteSurvey_TestData.xlsx";
	public static HashMap<String,List<String>> excelData;
	private String MSS_Frontline_Country;
	
	public SiteLoginScreen() {
		wdriver = WebContext.wdriver;
	}	

	private By txt_userName = By.id("username");
	private By txt_password = By.id("password");
	private By lookup_frontLine = By.id("frontline_chosen");
	private By txt_frontLine=By.xpath("(//*[@class='chosen-search'])[last()-1]/*[@type='text']");
	private By lnk_country=By.xpath("(//*[starts-with(text(),'Oman')])[last()]");
	private By btn_login=By.id("loginBtn");

	@Step("Login to Mobile Site Survey ")
	public SiteHomeScreen siteSurveySignIn(String username, String password) {
		ExcelReader excelReader=new ExcelReader(excelPath);
		try {
			excelData=excelReader.GetData("Web_Login_Data");
			MSS_Frontline_Country=excelData.get("MSS_Frontline_Country").get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		waitForElementPresent(txt_userName, 10);
		enteringValueinTextField(txt_userName, username);
		enteringValueinTextField(txt_password, password);
		clickonButton(lookup_frontLine);
		waitForElementPresent(txt_frontLine, 10);
		enteringValueinTextField(txt_frontLine, MSS_Frontline_Country);
		clickonButton(lnk_country);
//		waitForElementPresent(txt_frontLine, 10);
		String frontLine=gettingText(txt_frontLine);
		clickonButton(btn_login);
		
		SiteHomeScreen siteHomeScreen=new SiteHomeScreen();
		Assert.assertTrue(siteHomeScreen.isDisplayed());
		return siteHomeScreen;
	}

	@Step("Check if the Site Survey Login screen is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(txt_userName, 15) != null;

	}
}
