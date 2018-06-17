package com.kone.app.pages.salesforce;

import com.kone.app.pages.WebBasePage;
import com.kone.app.pages.konesitesurvey.SiteLoginPage;
import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;
import ru.yandex.qatools.allure.annotations.Step;

import static com.kone.app.pages.salesforce.MainPage.SF_Opportunity_Name;
import static com.kone.framework.context.WebContext.wdriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SelectOpportunityPage extends WebBasePage {

	public SelectOpportunityPage() {
		wdriver = WebContext.wdriver;
	}
	
	public static String URL;
	public static By lnk_SearchedOpportunity=By.xpath("//*[text()='Opportunities']/../../../../../../..//a[text()='Replace']");
	private By lnk_NewSiteSurvey=By.xpath("//*[text()='Request a New Site Survey']");
	
	@Step("Selecting Existing Opportunity and click on SiteSurvey Link")
	public SiteLoginPage clickonOpportunity() {
		
		clickonButton(stringtoXpathSearchedOpportunity(SF_Opportunity_Name));
		waitForElementPresent(lnk_NewSiteSurvey, 20);
		URL=gettingCurrentURL();
		clickonButton(lnk_NewSiteSurvey);
		
		SiteLoginPage siteLoginScreen=new SiteLoginPage();
		Assert.assertTrue(siteLoginScreen.isDisplayed());
		return siteLoginScreen;
		
	}
	
	@Step("Check if the main page is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(stringtoXpathSearchedOpportunity(SF_Opportunity_Name), 15) != null;
	}

}
