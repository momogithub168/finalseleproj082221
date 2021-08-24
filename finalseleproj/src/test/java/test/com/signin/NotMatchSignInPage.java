package test.com.signin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.ms.finalseleproj.SignInMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 *
 * @author Mo Wan
 */
public class NotMatchSignInPage extends SignInPage {


    public NotMatchSignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//*[@id=\"dwfrm_login\"]/div")
    private WebElement notMatchErrorMsg;    
      
    @Override
    public boolean IsNotMatchMsgAppear() {
        return notMatchErrorMsg.getText().equals(SignInMessages.NOT_MATCH_MSG); 
    }
}
