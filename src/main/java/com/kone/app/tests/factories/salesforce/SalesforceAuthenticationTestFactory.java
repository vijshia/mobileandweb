package com.kone.app.tests.factories.salesforce;

import org.testng.annotations.Factory;

import com.kone.app.tests.basetests.salesforce.SalesforceAuthenticationTests;
import com.kone.app.tests.factories.BaseFactory;


public class SalesforceAuthenticationTestFactory extends BaseFactory {
	
	@Factory(dataProvider = "salesforceAccountData")
    public Object[] testFactory(String email, String password) {
        return new Object[]{new SalesforceAuthenticationTests(email, password)};
    }

}
