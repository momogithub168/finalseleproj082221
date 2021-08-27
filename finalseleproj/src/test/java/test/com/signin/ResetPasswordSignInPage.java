/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com.signin;


import com.talbots.util.SignInMessages;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Mo Wan
 */
public class ResetPasswordSignInPage extends SignInPage {

    public ResetPasswordSignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "dwfrm_requestpassword_email")
    private WebElement emailElm;

    @FindBy(name = "dwfrm_requestpassword_send")
    private WebElement sendBtn;

    @FindBy(linkText = "Go to the Home page")
    private WebElement backHomeLink;

    @FindBy(id = "dwfrm_requestpassword_email-error")
    private WebElement errorLabel;

    @FindBy(id = "ui-id-1")
    private WebElement resetHeadTitle;

    @FindBy(xpath = ".//*[@id=\"dialog-container\"]/p")
    private WebElement pLabel;

    @FindBy(xpath = ".//*[@id=\"PasswordResetForm\"]/fieldset/div/label")
    private WebElement emailLabel;

    @FindBy(xpath = ".//div/button/span")
    private WebElement closeBtn;

    @FindBy(xpath = ".//*[@id=\"dialog-container\"]/p[1]")
    private WebElement p1;

    @FindBy(xpath = ".//*[@id=\"dialog-container\"]/p[2]")
    private WebElement p2;

    @Override
    public String resetPassword(String email, boolean isClick) {
        emailElm.clear();
        emailElm.click();
        emailElm.sendKeys(email);

        sendBtn.click();

        if (isEmptyOrNULL(email) || !isValidEmail(email)) {
            return this.errorLabel.getText();
        } else {
            //explict wait, applys to specific step 
            WebDriverWait wait = new WebDriverWait(driver, 2);  //4 sec
            wait.until(ExpectedConditions.visibilityOf(backHomeLink));
            if(isClick) {
                backHomeLink.click();
            }
            return "Success";
        }
    }

    public boolean validateOtherElements() {
        //explict wait, applys to specific step 
        WebDriverWait wait = new WebDriverWait(driver, 4);  //4 sec
        wait.until(ExpectedConditions.visibilityOf(resetHeadTitle));

        String title_txt = resetHeadTitle.getText();
        String pLabel_txt = pLabel.getText();
        String emailLable_txt = emailLabel.getText();
        String sendBtn_txt = this.sendBtn.getText();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ResetPasswordSignInPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean clickable = isCloseBtnClicable();

        return title_txt.equals(SignInMessages.RESET_PASSWORD)
                && pLabel_txt.equals(SignInMessages.PROVIDE_ACCOUNT_EMAIL)
                && emailLable_txt.equals(SignInMessages.EMAIL_LABEL)
                && sendBtn_txt.equals(SignInMessages.SEND_BTN_TEXT)
                && clickable;
    }

    private boolean isCloseBtnClicable() {
        closeBtn.click();
        return Boolean.TRUE;
    }

    public boolean isAllElementsAppeared(String email) 
    {
        if (isEmptyOrNULL(email) && !isValidEmail(email)) {
            return false;
        }
        this.resetPassword(email, Boolean.FALSE);
        String txt1 = resetHeadTitle.getText();
        String txt2 = p1.getText();
        String txt3 = p2.getText();
        String txt4 = backHomeLink.getText();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ResetPasswordSignInPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        backHomeLink.click();
        return txt1.equals(SignInMessages.RESET_PASSWORD)
                && txt2.equals(SignInMessages.REQUEST_RESET_RECEIVED)
                && txt3.equalsIgnoreCase(SignInMessages.LONG_PARAGRAPH)
                && txt4.equals(SignInMessages.GO_TO_HOME);
       
    }

    private boolean isEmptyOrNULL(String str) {

        if (str == null || str.isEmpty()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }
}
