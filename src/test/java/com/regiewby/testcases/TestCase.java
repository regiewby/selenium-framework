package com.regiewby.testcases;

import com.regiewby.factories.ExecuteTest;
import com.regiewby.factories.ExtentReportFactory;
import com.regiewby.factories.MyLogger;
import com.regiewby.pageObject.CheckoutPageObject;
import com.regiewby.pageObject.LoginPageObject;
import com.regiewby.utilities.ExcelOperations;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestCase extends ExecuteTest {


    LoginPageObject loginPage = new LoginPageObject();
    CheckoutPageObject checkout = new CheckoutPageObject();
    ExcelOperations excel;


    @Test(dataProvider = "loginData")
    public void testLogin(Object objLoginData) {
        @SuppressWarnings("unchecked")
        HashMap<String,String> testData = (HashMap<String, String>) objLoginData;
        ExtentReportFactory.getInstance().getExtent().info(testData.get("Test Case Name"));

//        MyLogger.startTestCase(Thread.currentThread().getStackTrace()[1].getMethodName());
        MyLogger.startTestCase(testData.get("Test Case Name"));
        loginPage.login(testData.get("Username"),testData.get("Password"));
    }


    @Test(dataProvider = "shoppingData")
    public void testShopping(Object objShoppingData) {
        @SuppressWarnings("unchecked")
        HashMap<String,String> testData = (HashMap<String, String>) objShoppingData;

//        MyLogger.startTestCase(Thread.currentThread().getStackTrace()[1].getMethodName());
        MyLogger.startTestCase(testData.get("Test Case Name"));
        loginPage.login(testData.get("Username"),testData.get("Password"));

        checkout.chooseWishlist(testData.get("Wish List"));
//        checkout.checkout();
        checkout.dataBuyer(testData.get("First Name"),testData.get("Last Name"),testData.get("Postal Code"));
    }


    @DataProvider (name = "loginData")
    public Object[][] dataLogin() throws Exception {
        excel = new ExcelOperations("LOGIN");
        Object[][] obj = new Object[excel.getRowCount()][1];
        for (int i = 1; i <= excel.getRowCount(); i++) {
            HashMap<String,String> mapData = excel.getTestDataInMap(i);
            obj[i-1][0] = mapData;
        }
        return obj;
    }

    @DataProvider (name = "shoppingData")
    public Object[][] dataShopping() throws Exception {
        excel = new ExcelOperations("SHOPPING");
        Object[][] obj = new Object[excel.getRowCount()][1];
        for (int i = 1; i <= excel.getRowCount(); i++) {
            HashMap<String,String> mapData = excel.getTestDataInMap(i);
            obj[i-1][0] = mapData;
        }
        return obj;
    }

}
