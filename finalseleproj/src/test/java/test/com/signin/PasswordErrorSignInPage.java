package test.com.signin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 *
 * @author Mo Wan
 */
public class PasswordErrorSignInPage extends SignInPage {


    public PasswordErrorSignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//*[@id=\"dwfrm_login_password-error\"]")
    private WebElement pWordErrorMsg;       
    
    @Override
    public String getPasswordErrorMsg() {
        return pWordErrorMsg.getText();
    }
}
