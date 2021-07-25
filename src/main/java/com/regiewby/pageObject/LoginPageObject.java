package com.regiewby.pageObject;

import com.regiewby.factories.DriverFactory;
import com.regiewby.factories.ExecuteTest;
import org.openqa.selenium.By;

public class LoginPageObject extends ExecuteTest {

    By USERNAME = By.id("user-name");
    By PASSWORD = By.id("password");
    By LOGIN_BTN = By.id("login-button");

    public void login(String username,String password) {

        sendKey(DriverFactory.getInstance().getDriver().findElement(USERNAME),"Login-Username-Field",username);
        sendKey(DriverFactory.getInstance().getDriver().findElement(PASSWORD),"Login-Password-Field",password);
        click(DriverFactory.getInstance().getDriver().findElement(LOGIN_BTN),"Login-Button");
    }
}
