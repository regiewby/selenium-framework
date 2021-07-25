package com.regiewby.factories;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportFactory {


    private ExtentReportFactory() {
    }

    private static ExtentReportFactory instance  = new ExtentReportFactory();

    public static ExtentReportFactory getInstance() {
        return instance;
    }

    ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();

    public ExtentTest getExtent() {
        return extent.get();
    }

    public void setExtent(ExtentTest extentTest) {
        extent.set(extentTest);
    }

    public void removeExtentObject() {
        extent.remove();
    }
}
