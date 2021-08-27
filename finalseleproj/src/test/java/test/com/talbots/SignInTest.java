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
//Running test cases in order of method names in default order
@FixMethodOrder(MethodSorters.DEFAULT)
public class SignInTest {

    private static WebDriver driver;
    private static String baseUrl;
    private SignInPage signInPage;
    private static List<SignInVO> infoList;

    public SignInTest() {
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
        //driver = new ChromeDriver();
//        ChromeOptions op = new ChromeOptions();
//        op.setHeadless(true);
//        op.addArguments("window-size=1920,1080");
//        driver = new ChromeDriver(op);
//        ChromeOptions op = new ChromeOptions();
//        op.setExperimentalOption("useAutomationExtension", false);
//            op.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
//        driver = new ChromeDriver(op);
        //System.out.println("signin title --- " + driver.getTitle());
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
            Logger.getLogger(SignInTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void executeUserNameErrorModule() throws Exception {
        signInPage = PageFactory.initElements(driver, UseNameErrorSignInPage.class);
        Thread.sleep(2000);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 1).findFirst().get();
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());
        Assert.assertTrue(signInPage.getUserNameErrorMsg().equals(SignInMessages.INVALID_EMAIL_MSG));
    }

    @Test
    public void executePassWordErrorModule() throws Exception {
        signInPage = PageFactory.initElements(driver, PasswordErrorSignInPage.class);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 2).findFirst().get();
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());
        Assert.assertTrue(signInPage.getPasswordErrorMsg().equals(SignInMessages.REQUIRE_FIELD_MSG));
        testRestElements(signInPage);
    }

    private void testRestElements(SignInPage page) throws Exception {
        Assert.assertTrue(page.isValidH4Title());
        Assert.assertTrue(page.isValidPText());
        Assert.assertTrue(page.isValidSpanLabel());
        Assert.assertTrue(page.isCheckBoxAppear());
        Assert.assertTrue(page.isPWordResetAppear());
    }

    @Test
    public void executeUserNameandPasswordErrorModule() throws Exception {
        signInPage = PageFactory.initElements(driver, UseNameAndPasswordErrorSignInPage.class);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 3).findFirst().get();
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());

        Assert.assertTrue(signInPage.getUserNameErrorMsg().equals(SignInMessages.REQUIRE_FIELD_MSG));
        Assert.assertTrue(signInPage.getPasswordErrorMsg().equals(SignInMessages.REQUIRE_FIELD_MSG));
    }

    @Test
    public void executeNotMatchModule() throws Exception {
        signInPage = PageFactory.initElements(driver, NotMatchSignInPage.class);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 4).findFirst().get();
        
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());
        Assert.assertTrue(signInPage.IsNotMatchMsgAppear());

    }
}
