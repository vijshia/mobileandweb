package com.kone.app.tests.factories.vbmobile;

import java.util.HashMap;

import org.testng.annotations.Factory;

import com.kone.app.tests.factories.BaseFactory;
import com.kone.app.tests.testcases.vbmobile.VbmobileAuthenticationTests;

public class VbmobileAuthenticationTestFactory extends BaseFactory {
	
	@Factory(dataProvider = "excelDataProvider")
    public Object[] testFactory(HashMap<String, String> exceldatacombined) {
        return new Object[]{new VbmobileAuthenticationTests(exceldatacombined)};
    }

}
