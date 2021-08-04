package com.regiewby.pageObject;

import com.regiewby.factories.DriverFactory;
import com.regiewby.factories.ExecuteTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPageObject extends ExecuteTest {


//    By ADDTOCART_BTN = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']");
    By ADDTOCART_BTN = By.xpath("//div[@class='inventory_item_name'");
    By SHOPPINGCART_BTN = By.id("shopping_cart_container");
    By CHECKOUT_BTN = By.id("checkout");
    By CART_BADGE = By.xpath("//span[@class='shopping_cart_badge']");

    By FIRSTNAME = By.id("first-name");
    By LASTNAME = By.id("last-name");
    By POSTALCODE = By.id("postal-code");
    By CONTINUE_BTN = By.id("continue");
    By FINISH_BTN = By.id("finish");


    public void chooseWishlist(String dataBarang) {
        String[] listBarang = dataBarang.split(",");
        for (int i = 0; i < listBarang.length; i++) {
            click(DriverFactory.getInstance().getDriver().findElement(By.xpath
                    ("//div[@class='inventory_item_name' and text()='"+listBarang[i].trim()+"']/following::button[1]")
            ),"addToCart-button");
        }

        String cartCount = DriverFactory.getInstance().getDriver().findElement(CART_BADGE).getText();
        expectedTrue(cartCount.equals(String.valueOf(listBarang.length)),"cart-badge-counter");
        click(DriverFactory.getInstance().getDriver().findElement(SHOPPINGCART_BTN),"shoppingCart-button");
        click(DriverFactory.getInstance().getDriver().findElement(CHECKOUT_BTN),"checkout-button");

    }


    public void checkout() {

        List<WebElement> listElements = DriverFactory.getInstance().getDriver().findElements(ADDTOCART_BTN);

        for (int i = 0; i < listElements.size(); i++) {
            click(DriverFactory.getInstance().getDriver().findElement(ADDTOCART_BTN),"addToCart-button");
        }

        String cartCount = DriverFactory.getInstance().getDriver().findElement(CART_BADGE).getText();

        expectedTrue(cartCount.equals(String.valueOf(listElements.size())),"cart-badge-counter");

        click(DriverFactory.getInstance().getDriver().findElement(SHOPPINGCART_BTN),"shoppingCart-button");
        click(DriverFactory.getInstance().getDriver().findElement(CHECKOUT_BTN),"checkout-button");

    }


    public void dataBuyer(String firstName, String lastName, String postalCode) {

        sendKey(DriverFactory.getInstance().getDriver().findElement(FIRSTNAME),"checkout-firstName-field",firstName);
        sendKey(DriverFactory.getInstance().getDriver().findElement(LASTNAME),"checkout-lastName-field",lastName);
        sendKey(DriverFactory.getInstance().getDriver().findElement(POSTALCODE),"checkout-postalCode-field",postalCode);
        click(DriverFactory.getInstance().getDriver().findElement(CONTINUE_BTN),"checkout-continue-button");
        click(DriverFactory.getInstance().getDriver().findElement(FINISH_BTN),"checkout-continue-button");

    }


}
