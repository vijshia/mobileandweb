package com.kone.framework.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.kone.framework.context.TestContext;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

public class TestNGListener extends TestListenerAdapter   {
	
    @Attachment(value = "{1}", type = "image/png")
    public byte[] captureScreenshot(AndroidDriver<MobileElement> d, String desc) {
        return ((TakesScreenshot)d).getScreenshotAs(OutputType.BYTES);
    }
    
    @Override
    public void onTestFailure(ITestResult iTestResult) { 
    	AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>)TestContext.driver;
        captureScreenshot(driver, "Screenshot taken on test failure");
    }
}
