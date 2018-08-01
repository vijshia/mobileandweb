package com.kone.app.pages.konesitesurvey;

import com.kone.app.pages.WebBasePage;
import com.kone.framework.context.WebContext;
import ru.yandex.qatools.allure.annotations.Step;
import java.util.List;
import com.kone.framework.utility.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static com.kone.app.pages.konesitesurvey.SiteHomePage.header_SiteSurveyLink;

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
	private By spinner=By.xpath("//*[contains(@id,'spinner')]");
	private By applicationStartingText=By.xpath("//*[text()='Starting Application...']");
	private boolean loginSkip;


	@Step("Login to Mobile Site Survey ")
	public SiteHomePage siteSurveySignIn(String username, String password, String sitefrontlinecountry, String mobileMenutoSelect) {
		
		if(loginSkip==false) {
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
		clickonButton(btn_login);
		}
		
		SiteHomePage siteHomeScreen=new SiteHomePage();
		Assert.assertTrue(siteHomeScreen.isDisplayed(mobileMenutoSelect));
		return siteHomeScreen;
	}
	

	@Step("Check if the Site Survey Login screen is displayed")
	public boolean isDisplayed() {
		By headercheck;
		By spinnercheck=By.xpath("//*[contains(@id,'spinner') or text()='Starting Application...']");
		By headers=By.xpath("//*[starts-with(text(),'Create Surveying Task(s) for account:') and contains(text(),'Opportunity:') or @id='username']");
		waitForElementPresent(spinnercheck, 60);

		waitForElementPresent(headers, 60);
		List<WebElement> checkcount=gettingWebElementsfromList(headers);
			String elements=Integer.toString(checkcount.size());
			Log.info(elements);	
			if(checkcount.size()>1) {
				headercheck=txt_userName;
			} else {
				headercheck=header_SiteSurveyLink;
				loginSkip=true;
			} 
			String flag=Boolean.toString(loginSkip);
			Log.info(flag);
		return waitForElementPresent(headercheck, 25) != null;

	}
}
