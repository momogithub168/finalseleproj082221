/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com.signin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Mo Wan
 */
public class SignOutPage {

    WebDriver driver;

    public SignOutPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[contains(text(),'Sign Out')]")
    private WebElement signOutPage;

    public void logoutPage() throws Exception {
        try {
            signOutPage.click();
// Logout logout = new Logout(driver);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

