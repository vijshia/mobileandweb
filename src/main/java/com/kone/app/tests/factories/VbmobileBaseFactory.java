package com.kone.app.tests.factories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class VbmobileBaseFactory {
	
	private Properties testData = new Properties();
	
    @DataProvider(name = "accountData")
    public Object[][] accountDataProvider(ITestContext context) throws IOException {

        String dataSource = context.getCurrentXmlTest().getParameter("dataSource");
        testData.load(new FileInputStream(new File(dataSource)));

        String emails = testData.getProperty("login.user");
        String passwords = testData.getProperty("login.password");

        String[] email = emails.split(",");
        String[] password = passwords.split(",");

        Object[][] data = new Object[email.length][2];

        for (int i = 0; i < email.length; i++) {
            data[i][0] = email[i].trim();
            data[i][1] = password[i].trim();
        }

        return data;
    }

}
