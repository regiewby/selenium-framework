package com.regiewby.factories;

import com.regiewby.utilities.Keywords;
import com.regiewby.utilities.PropertiesOperation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class ExecuteTest extends Keywords {

    BrowserFactory bf = new BrowserFactory();

    @BeforeMethod
    public void LaunchApplication() throws Exception {
        String browser = PropertiesOperation.getPropertyValueByKey("browser");
        String url = PropertiesOperation.getPropertyValueByKey("url");

        DriverFactory.getInstance().setDriver(bf.launchBrowser(browser));
        WebDriver webDriver = DriverFactory.getInstance().getDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        webDriver.navigate().to(url);
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.getInstance().closeBrowser();
    }
}
