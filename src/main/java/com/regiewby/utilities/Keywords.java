package com.regiewby.utilities;

import com.aventstack.extentreports.Status;
import com.regiewby.factories.ExtentReportFactory;
import com.regiewby.factories.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.Locale;

public class Keywords {


    public void sendKey(WebElement element, String elementName, String strValue) {

        try {
            element.sendKeys(strValue);

            MyLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toUpperCase(Locale.ROOT) + " ==> "+elementName+ " ==> "+strValue);
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, elementName + "==> Enter Value as " + strValue);

        } catch (Exception e) {
            MyLogger.error(e);
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field " + elementName + " is Failed due to Exception " + e);
        }
    }

//    public void customClickByName(WebElement element, String elementName, String strValue) {
//
//        try {
//            element
//            element.click();
//            MyLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toUpperCase(Locale.ROOT) + " ==> " +elementName);
//            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, elementName + "==> Clicked Succesfully");
//
//        } catch (Exception e) {
//            MyLogger.error(e);
//            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field " + elementName + " due to Exception " + e);
//        }
//    }


    public void click(WebElement element, String elementName) {

        try {
            element.click();
            MyLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toUpperCase(Locale.ROOT) + " ==> " +elementName);
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, elementName + "==> Clicked Succesfully");

        } catch (Exception e) {
            MyLogger.error(e);
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field " + elementName + " due to Exception " + e);
        }
    }


    public void expectedTrue(Boolean expected, String elementName) {

        try {
            Assert.assertTrue(expected);

            MyLogger.info(Thread.currentThread().getStackTrace()[1].getMethodName().toUpperCase(Locale.ROOT) + " ==> " +elementName+ " Expected ==> "+expected);
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, elementName + "==> Fit the expectation, Expected : "+expected);
        } catch (Exception e) {
            MyLogger.info(e);
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL,  elementName + "didn't meet the expectation de to Exception " + e);
        }
    }


    public void expectedFalse(Boolean expected, String elementName) {
        try {
            Assert.assertFalse(expected);
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, elementName + "==> Fit the expectation, Expected : "+expected);
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL,  elementName + "didn't meet the expectation de to Exception " + e);
        }
    }


}