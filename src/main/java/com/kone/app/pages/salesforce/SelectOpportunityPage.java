package com.kone.app.pages.salesforce;

import com.kone.app.pages.WebBasePage;
import com.kone.app.pages.konesitesurvey.SiteLoginPage;
import com.kone.framework.context.WebContext;
import ru.yandex.qatools.allure.annotations.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SelectOpportunityPage extends WebBasePage {

	public SelectOpportunityPage() {
		wdriver = WebContext.wdriver;
	}
	
	public static String URL;
	public static By lnk_SearchedOpportunity=By.xpath("//*[text()='Opportunities']/../../../../../../..//a[text()='Replace']");
	
	private By lnk_SiteSurvey;
//	private By btn_Homesearchbox=By.id("phSearchButton");
	
	@Step("Selecting Existing Opportunity and click on SiteSurvey Link")
	public SiteLoginPage clickonOpportunity(String salesforceopportunityname, String mobileMenutoSelect) {
		
		clickonButton(stringtoXpathSearchedOpportunity(salesforceopportunityname));
		
		if(mobileMenutoSelect.equals("Survey Manager")) {
			lnk_SiteSurvey = stringtoXpathEquals("Link opportunity with existing Site Survey");
		} else {
			lnk_SiteSurvey = stringtoXpathEquals("Request a New Site Survey");
		}
		
		waitForElementPresent(lnk_SiteSurvey, 20);
		clickonButton(lnk_SiteSurvey);
		
		SiteLoginPage siteLoginScreen=new SiteLoginPage();
		Assert.assertTrue(siteLoginScreen.isDisplayed());
		return siteLoginScreen;
		
	}
	
	@Step("Check if the main page is displayed")
	public boolean isDisplayed(String salesforceopportunityname) {
		return waitForElementPresent(stringtoXpathSearchedOpportunity(salesforceopportunityname), 15) != null;
	}

}
