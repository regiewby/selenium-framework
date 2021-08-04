package com.regiewby.factories;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.net.MalformedURLException;

public class BrowserFactory {


    public WebDriver launchBrowser(String browser) throws MalformedURLException {

        WebDriver webDriver = null;

        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
//            System.setProperty("webdriver.chrome.silentOutput", "true");
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--incognito");
            webDriver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions foptions = new FirefoxOptions();
//            foptions.addArguments("-private");
//            webDriver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.firefox());
            webDriver = new FirefoxDriver(foptions);

        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            InternetExplorerOptions iOptions = new InternetExplorerOptions();
//            iOptions.addCommandSwitches("-private");
            webDriver = new InternetExplorerDriver(iOptions);
        }

        return webDriver;
    }
}
