/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author itexps
 */
public class LogoutPage {

    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[contains(text(),'SIGN-OFF')]")
    private WebElement logoutPage;

    public void logoutPage() throws Exception {
        try {
            logoutPage.click();
// Logout logout = new Logout(driver);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

