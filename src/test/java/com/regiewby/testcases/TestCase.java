package com.regiewby.testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.regiewby.factories.DriverFactory;
import com.regiewby.factories.ExecuteTest;
import com.regiewby.factories.ExtentReportFactory;
import com.regiewby.factories.MyLogger;
import com.regiewby.pageObject.LoginPageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestCase extends ExecuteTest {


    LoginPageObject loginPage = new LoginPageObject();

    @Test
    public void testCase1() {

        loginPage.login("standard_user","secret_sauce");
    }



}
