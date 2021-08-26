package test.com.signin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.talbots.util.SignInMessages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Mo Wan
 */
public class SignInPage  {

    WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = ".//*[@id=\"dwfrm_login_username\"]")
    private WebElement userName;

    @FindBy(xpath = ".//*[@id=\"dwfrm_login_password\"]")
    private WebElement password;

    @FindBy(xpath = ".//*[@id=\"dwfrm_login\"]")
    private WebElement submitBtn;

    @FindBy(xpath = ".//*[@id=\"primary\"]/div/div[2]/div/h4")
    private WebElement h4Title;
    
    @FindBy(xpath = ".//*[@id=\"primary\"]/div/div[2]/div/div/p")
    private WebElement pText;

    @FindBy(xpath = ".//*[@id=\"dwfrm_login\"]/fieldset/div[3]/div/div[1]/label/span")
    private WebElement spanLabel;
    
    @FindBy(xpath = ".//*[@id=\"dwfrm_login_rememberme\"]")
    private WebElement rCheckBox;
    
    @FindBy(xpath = ".//*[@id=\"password-reset\"]")
    private WebElement pWordReset;
    
    public void signIn(String url, String uName, String pWord)
            throws Exception {
        try {
            driver.get(url);
            userName.clear();
            userName.sendKeys(uName);
            password.sendKeys(pWord  + Keys.ENTER);
            submitBtn.click();
            //SignInPage login = new SignInPage(driver);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String getUserNameErrorMsg() {
        return "getUserNameErrorMsg in " + SignInPage.class;
    }
    
    public String getPasswordErrorMsg() {
       return "getPasswordErrorMsg in " + SignInPage.class;
    }
    
    public boolean isValidH4Title() {
        return h4Title.getText().equals(SignInMessages.RETURNING_CUSTOMERS_MSG);
    }
    
    public boolean isValidPText() {
        System.out.println("IsValidPText ==> " + pText.getText());
        return pText.getText().equals(SignInMessages.P_MSG);
    }
    
    public boolean isValidSpanLabel() {
        return spanLabel.getText().equals(SignInMessages.REMEBER_ME_MSG);
    }
    
    public boolean isCheckBoxAppear(){
        return Boolean.valueOf(rCheckBox.getAttribute(SignInMessages.VALUE_ATTRIBUTE));
    }
    
    public boolean isPWordResetAppear() {
        return pWordReset.getText().equals(SignInMessages.FORGOT_PASSWORD_MSG); 
    }
    
    public boolean IsNotMatchMsgAppear() {
        return Boolean.FALSE; 
    }
    
    public boolean IsShowHideWorking() {
        return Boolean.FALSE;
    }

}
