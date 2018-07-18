package com.kone.app.tests.factories.salesforce;

import java.util.HashMap;
import org.testng.annotations.Factory;
import com.kone.app.tests.factories.BaseFactory;
import com.kone.app.tests.testcases.salesforce.SalesforceSurveytoLinkOpportunity;


public class SalesforceSurveytoLinkOpportunityTestFactory extends BaseFactory {
		
	@Factory(dataProvider = "excelDataProvider")
    public Object[] testFactory(HashMap<String, String> exceldatacombined) {
		return new Object[] {new SalesforceSurveytoLinkOpportunity(exceldatacombined)};
    }
}
