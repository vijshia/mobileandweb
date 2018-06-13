package com.kone.app.screens.salesforce;

import com.kone.app.screens.WebBaseScreen;
import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.framework.context.WebContext.wdriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.kone.app.screens.konesitesurvey.SiteLoginScreen;
import static com.kone.app.screens.salesforce.MainScreen.SF_Opportunity_Name;

public class SelectOpportunityScreen extends WebBaseScreen {

	public SelectOpportunityScreen() {
		wdriver = WebContext.wdriver;
	}
	
	public static String URL;
	public static By lnk_SearchedOpportunity=By.xpath("//*[text()='Opportunities']/../../../../../../..//a[text()='Replace']");
	private By lnk_NewSiteSurvey=By.xpath("//*[text()='Request a New Site Survey']");
	
	@Step("Selecting Existing Opportunity and click on SiteSurvey Link")
	public SiteLoginScreen clickonOpportunity() {
		
		clickonButton(stringtoXpathSearchedOpportunity(SF_Opportunity_Name));
		waitForElementPresent(lnk_NewSiteSurvey, 20);
		URL=gettingCurrentURL();
		clickonButton(lnk_NewSiteSurvey);
		
		SiteLoginScreen siteLoginScreen=new SiteLoginScreen();
		Assert.assertTrue(siteLoginScreen.isDisplayed());
		return siteLoginScreen;
		
	}
	
	@Step("Check if the main page is displayed")
	public boolean isDisplayed() {
		return waitForElementPresent(stringtoXpathSearchedOpportunity(SF_Opportunity_Name), 15) != null;
	}

}
