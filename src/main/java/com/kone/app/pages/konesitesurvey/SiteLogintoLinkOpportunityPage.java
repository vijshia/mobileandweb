package com.kone.app.pages.konesitesurvey;

import com.kone.app.pages.WebBasePage;
import com.kone.framework.context.WebContext;
import ru.yandex.qatools.allure.annotations.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SiteLogintoLinkOpportunityPage extends WebBasePage {
	
	public SiteLogintoLinkOpportunityPage() {
		wdriver = WebContext.wdriver;
	}	
	
	private By txt_userName = By.id("username");
	private By txt_password = By.id("password");
	private By lookup_frontLine = By.id("frontline_chosen");
	private By txt_frontLine=By.xpath("(//*[@class='chosen-search'])[last()-1]/*[@type='text']");
	private By btn_login=By.id("loginBtn");
	

	@Step("Login to Mobile Site Survey ")
	public SiteLinkSurvey siteSurveySignIntoLinkOpportunity(String username, String password, String sitefrontlinecountry) {
		
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

		
		SiteLinkSurvey sitelinksurvey=new SiteLinkSurvey();
		Assert.assertTrue(sitelinksurvey.isDisplayed());
		return sitelinksurvey;
	}
}
