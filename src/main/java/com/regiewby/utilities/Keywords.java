package com.regiewby.utilities;

import com.aventstack.extentreports.Status;
import com.regiewby.factories.ExtentReportFactory;
import org.openqa.selenium.WebElement;

public class Keywords {


    public void sendKey(WebElement element, String elementName, String strValue) {

        try {
            element.sendKeys(strValue);
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, elementName + "==> Enter Value as " + strValue);
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field " + elementName + " is Failed due to Exception " + e);
        }
    }

    public void click(WebElement element, String elementName) {

        try {
            element.click();
            ExtentReportFactory.getInstance().getExtent().log(Status.PASS, elementName + "==> Clicked Succesfully");
        } catch (Exception e) {
            ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field " + elementName + " due to Exception " + e);
        }
    }
}