/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com.talbots;

import com.ms.datadriven.SignInService;
import com.ms.datadriven.vo.SignInVO;
import com.ms.finalseleproj.SignInMessages;
import java.util.List;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import test.com.signin.NotMatchSignInPage;
import test.com.signin.PasswordErrorSignInPage;
import test.com.signin.ShowHideSignInPage;
import test.com.signin.SignInPage;
import test.com.signin.UseNameAndPasswordErrorSignInPage;
import test.com.signin.UseNameErrorSignInPage;

/**
 *
 * @author Mo Wan
 */
//Running test cases in order of method names in ascending order
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
        baseUrl = "https://www.talbots.com/login?original=%2Faccount";
        infoList = SignInService.loadSignInDetails();
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\data\\chromedriver.exe");

        //driver = new ChromeDriver();
//        ChromeOptions op = new ChromeOptions();
//        op.setHeadless(true);
//        op.addArguments("window-size=1920,1080");
//        driver = new ChromeDriver(op);
//        ChromeOptions op = new ChromeOptions();
//        op.setExperimentalOption("useAutomationExtension", false);
//            op.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
//        driver = new ChromeDriver(op);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        //System.out.println("signin title --- " + driver.getTitle());
    }

    @After
    public void tearDown() {
        try {
            Thread.sleep(2000);
            driver.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(SignInTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Test
    public void executeUserNameErrorModule() throws Exception {
        signInPage = PageFactory.initElements(driver, UseNameErrorSignInPage.class);
        Thread.sleep(2000);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 1).findFirst().get();
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());
        //Thread.sleep(5000);
        Assert.assertTrue(signInPage.getUserNameErrorMsg().equals(SignInMessages.INVALID_EMAIL_MSG));
        //Thread.sleep(2000);
    }

    @Test
    public void executePassWordErrorModule() throws Exception {
        signInPage = PageFactory.initElements(driver, PasswordErrorSignInPage.class);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 2).findFirst().get();
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());
        //Thread.sleep(8000);
        Assert.assertTrue(signInPage.getPasswordErrorMsg().equals(SignInMessages.REQUIRE_FIELD_MSG));
        testRestElements(signInPage);
    }

    private void testRestElements(SignInPage page) throws Exception {
        //Thread.sleep(2000);
        Assert.assertTrue(page.isValidH4Title());
        //Thread.sleep(1000);
        Assert.assertTrue(page.isValidPText());
        //Thread.sleep(2000);
        Assert.assertTrue(page.isValidSpanLabel());
        //Thread.sleep(2000);
        Assert.assertTrue(page.isCheckBoxAppear());
        //Thread.sleep(2000);
        Assert.assertTrue(page.isPWordResetAppear());
    }

    @Test
    public void executeUserNameandPasswordErrorModule() throws Exception {
        signInPage = PageFactory.initElements(driver, UseNameAndPasswordErrorSignInPage.class);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 3).findFirst().get();
        //Thread.sleep(2000);
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());
        //Thread.sleep(2000);
        Assert.assertTrue(signInPage.getUserNameErrorMsg().equals(SignInMessages.REQUIRE_FIELD_MSG));
        //Thread.sleep(2000);
        Assert.assertTrue(signInPage.getPasswordErrorMsg().equals(SignInMessages.REQUIRE_FIELD_MSG));
        //Thread.sleep(2000);
    }

    @Test
    public void executeNotMatchModule() throws Exception {
        signInPage = PageFactory.initElements(driver, NotMatchSignInPage.class);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 4).findFirst().get();
        //Thread.sleep(2000);
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());
        //Thread.sleep(8000);
        Assert.assertTrue(signInPage.IsNotMatchMsgAppear());
        //Thread.sleep(2000);
    }

}
