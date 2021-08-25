/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com.signin;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author Mo Wan
 */
public class ShowHideSignInPage extends SignInPage{
    
    public ShowHideSignInPage(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(xpath = ".//*[@id=\"dwfrm_login\"]/fieldset/div[2]/div[1]/span[1]")
    private WebElement show;
    
    @FindBy(xpath = ".//*[@id=\"dwfrm_login\"]/fieldset/div[2]/div[1]/span[2]")
    private WebElement hide;
    
    @Override
    public boolean IsShowHideWorking() {
        show.click();
        String showStyle = show.getAttribute("style");
        String hideStyle = hide.getAttribute("style");
        boolean b1 = showStyle.contains("none") && hideStyle.contains("inline");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ShowHideSignInPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        hide.click();
        showStyle = show.getAttribute("style");
        hideStyle = hide.getAttribute("style"); 
        boolean b2 = showStyle.contains("block") && hideStyle.contains("none");;
        
        return b1 && b2;
    }

}
