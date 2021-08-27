/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com.talbots;

import com.talbots.datadriven.SignInService;
import com.talbots.datadriven.vo.SignInVO;
import com.talbots.operation.PropertyReader;
import com.talbots.util.PropertyKeys;
import com.talbots.util.SignInMessages;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import test.com.signin.NotMatchSignInPage;
import test.com.signin.PasswordErrorSignInPage;
import test.com.signin.ResetPasswordSignInPage;
import test.com.signin.SignInPage;
import test.com.signin.UseNameAndPasswordErrorSignInPage;
import test.com.signin.UseNameErrorSignInPage;

/**
 *
 * @author Mo Wan
 */
//Running test cases in order of method names in ascending order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ResetPassordTest {

    private static WebDriver driver;
    private static String baseUrl;
    private SignInPage signInPage;
    private static List<SignInVO> infoList;

    public ResetPassordTest() {
    }

    @BeforeClass
    public static void setUpClass() {
                //load properties
        PropertyReader propReader = new PropertyReader();
        Properties prop = propReader.getObjectRepository(PropertyKeys.SIGN_IN_PROP_FILE);
        //load data driven list for test cases
        infoList = SignInService.loadSignInDetails();    
        baseUrl = prop.getProperty(PropertyKeys.SIGN_IN_URL);
        
        System.setProperty(prop.getProperty(PropertyKeys.WEB_DRIVER), prop.getProperty(PropertyKeys.CHROME_DRIVER_EXE));
   
        ChromeOptions options = new ChromeOptions();
        //options.setHeadless(true);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void tearDownClass() {
        try {
            Thread.sleep(2000);
            driver.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(ResetPassordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void signIn() {
        if (signInPage == null) {
          signInPage = PageFactory.initElements(driver, ResetPasswordSignInPage.class);  
        }
        try {
                signInPage.signIn();
        } catch (Exception ex) {
            Logger.getLogger(ResetPassordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void executeResetPassword1() throws Exception {
       signIn();
       Assert.assertTrue(((ResetPasswordSignInPage)signInPage).validateOtherElements());   
    }
    
    @Test
    public void executeResetPassword2() throws Exception {
        signIn();
        SignInVO info = infoList.stream().filter(i->i.getId()==3).findFirst().get();
        Assert.assertEquals(SignInMessages.REQUIRE_FIELD_MSG, signInPage.resetPassword("",  Boolean.FALSE));
    }
    
    @Test
    public void executeResetPassword3() throws Exception {
        signIn();
        SignInVO info = infoList.stream().filter(i->i.getId()==1).findFirst().get();
        Assert.assertEquals(SignInMessages.INVALID_EMAIL_MSG, signInPage.resetPassword(info.getUsername(), Boolean.FALSE));
    }
    
    @Test
    public void executeResetPassword4() throws Exception {
        signIn();
        SignInVO info = infoList.stream().filter(i->i.getId()==5).findFirst().get();
        Assert.assertEquals("Success", signInPage.resetPassword(info.getUsername(), Boolean.TRUE));
    }
    
    @Test
    public void executeResetPassword5() throws Exception {
        signIn();
        SignInVO info = infoList.stream().filter(i->i.getId()==2).findFirst().get();
        Assert.assertTrue(((ResetPasswordSignInPage)signInPage).isAllElementsAppeared(info.getUsername()));
    }
}
