package com.regiewby.factories;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.regiewby.utilities.PropertiesOperation;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener {

    public static ExtentReports extent;

    public static ExtentReports setupExtentReport() {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);

        String reportPath = System.getProperty("user.dir") +
                "/Reports/ExecutionReport_"+actualDate+".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        sparkReporter.config().setDocumentTitle("Automation Reports");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Report Name");

        extent.setSystemInfo("Executed on Environment",PropertiesOperation.getPropertyValueByKey("url"));
        extent.setSystemInfo("Executed on Browser",PropertiesOperation.getPropertyValueByKey("browser"));
        extent.setSystemInfo("Executed on OS",System.getProperty("os.name"));
        extent.setSystemInfo("Executed by User",System.getProperty("user.name"));

        return extent;
    }


}
