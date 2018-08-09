package com.kone.app.tests.factories.salesforce;

import java.util.HashMap;
import org.testng.annotations.Factory;
import com.kone.app.tests.factories.BaseFactory;
import com.kone.app.tests.testcases.salesforce.SalesforceAuthenticationTests;


public class SalesforceAuthenticationTestFactory extends BaseFactory {
	
/*	@Factory(dataProvider = "salesforceAccountData")
    public Object[] testFactory(String email, String password) {
        return new Object[]{new SalesforceAuthenticationTests(email, password)};
    }
    
    @Factory(dataProvider = "salesforceAccountData")
    public Object[] testFactory(String salesforceEmail, String salesforcepassword, String siteUsername, String sitePassword) {
        return new Object[]{new SalesforceAuthenticationTests(salesforceEmail, salesforcepassword, siteUsername, sitePassword)};
    }
    */
	
	@Factory(dataProvider = "excelDataProvider")
    public Object[] testFactory(HashMap<String, String> exceldatacombined) {
//        return new HashMap<String,List<String>>{new SalesforceAuthenticationTests(exceldatacombined)};
		return new Object[] {new SalesforceAuthenticationTests(exceldatacombined)};
    }
}
