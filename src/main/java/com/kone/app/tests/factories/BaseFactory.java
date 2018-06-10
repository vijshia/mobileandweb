package com.kone.app.tests.factories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class BaseFactory {
	
	private Properties testData = new Properties();
	
    @DataProvider(name = "siteSurveyAccountData")
    public Object[][] accountDataProvider(ITestContext context) throws IOException {

        String dataSource = context.getCurrentXmlTest().getParameter("dataSource");
        testData.load(new FileInputStream(new File(dataSource)));

        String emails = testData.getProperty("mobileSiteSurvey.login.user");
        String passwords = testData.getProperty("mobileSiteSurvey.login.password");
        String frontlines = testData.getProperty("mobileSiteSurvey.login.frontline");

        String[] email = emails.split(",");
        String[] password = passwords.split(",");
        String[] frontline = frontlines.split(",");

        Object[][] data = new Object[email.length][3];

        for (int i = 0; i < email.length; i++) {
            data[i][0] = email[i].trim();
            data[i][1] = password[i].trim();
            data[i][2] = frontline[i].trim();
        }

        return data;
    }
    
    @DataProvider(name = "salesforceAccountData")
    public Object[][] salesforceAccountDataProvider(ITestContext context) throws IOException {

        String dataSource = context.getCurrentXmlTest().getParameter("dataSource");
        testData.load(new FileInputStream(new File(dataSource)));

        String salesforceEmails = testData.getProperty("salesforce.login.user2");
        String salesforcepasswords = testData.getProperty("salesforce.login.password2");
        String siteUsernames = testData.getProperty("mobileSiteSurvey.login.user");
        String sitePasswords = testData.getProperty("mobileSiteSurvey.login.password");

        String[] salesforceEmail = salesforceEmails.split(",");
        String[] salesforcepassword = salesforcepasswords.split(",");
        String[] siteUsername = siteUsernames.split(",");
        String[] sitePassword = sitePasswords.split(",");
        

        Object[][] data = new Object[salesforceEmail.length][4];

        for (int i = 0; i < salesforceEmail.length; i++) {
            data[i][0] = salesforceEmail[i].trim();
            data[i][1] = salesforcepassword[i].trim();
            data[i][2] = siteUsername[i].trim();
            data[i][3] = sitePassword[i].trim();
        }

        return data;
    }

}
