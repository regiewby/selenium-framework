package com.regiewby.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.regiewby.factories.DriverFactory;
import com.regiewby.factories.ExtentReportFactory;
import com.regiewby.factories.ExtentReportListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerImplementation implements ITestListener {

    static ExtentReports report;
    ExtentTest test;


    public void onTestStart(ITestResult result) {
        test = report.createTest(result.getMethod().getMethodName());
        ExtentReportFactory.getInstance().setExtent(test);
    }

    public void onTestSuccess(ITestResult result) {
        ExtentReportFactory.getInstance().getExtent().log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ " is Passed.");
        ExtentReportFactory.getInstance().removeExtentObject();
    }


    public void onTestFailure(ITestResult result) {

        ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
        ExtentReportFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());

        //add screenshot for failed test.
        String base64Screenshot = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.BASE64);
        File src = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date date = new Date();
        String actualDate = format.format(date);

        String screenshotName = actualDate + ".png";
        String screenshotPath = System.getProperty("user.dir")+
                "/Reports/Screenshots/";


        File dest = new File(screenshotPath+screenshotName);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            ExtentReportFactory.getInstance().getExtent().addScreenCaptureFromBase64String(base64Screenshot,"Test case failure screenshot");
//            test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.cre("./Screenshots/"+screenshotName).build());
//            ExtentReportFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath+screenshotName, "Test case failure screenshot");
            ExtentReportFactory.getInstance().removeExtentObject();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ///////JIRA defect creation part
//        String automaticJIRAcreation = PropertiesOperations.getPropertyValueByKey("automatic_Issue_Creation_In_JIRA");
//        if(automaticJIRAcreation.trim().equalsIgnoreCase("ON")) {
//            String issueS = "Automation Test Failed - "+result.getMethod().getMethodName();
//            String issueD = "Test Data to be passed here.";
//            String issueNumber = null;
//            try {
//                issueNumber = jiraOps.createJiraIssue("QDPM", issueS, issueD, "10000", "5", "QDPM", "SIT", "5f782c4b95fe8e0069705791");
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//            try {
//                jiraOps.addAttachmentToJiraIssue(issueNumber, screenshotPath);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

    }


    public void onTestSkipped(ITestResult result) {
        ExtentReportFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
        ExtentReportFactory.getInstance().removeExtentObject();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }


    public void onTestFailedWithTimeout(ITestResult result) {

        ITestListener.super.onTestFailedWithTimeout(result);
    }


    public void onStart(ITestContext context) {
        try {
            report = ExtentReportListener.setupExtentReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        report.flush();
    }
}
