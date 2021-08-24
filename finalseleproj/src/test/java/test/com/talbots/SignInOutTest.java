/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com.talbots;

import com.itexps.mavendatadriven.vo.SignInVO;
import com.ms.datadriven.SignInService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import test.com.signin.SignInPage;
import test.com.signin.SignOutPage;

/**
 *
 * @author Mo Wan
 */
public class SignInOutTest {

    private static WebDriver driver;
    private static String baseUrl;

    public SignInOutTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        baseUrl = "https://www.talbots.com/login?original=%2Faccount";
        System.setProperty("webdriver.chrome.driver", "c:\\data\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void executeSignInModule() throws Exception {
        SignInPage signInpage = PageFactory.initElements(driver, SignInPage.class);
        SignInVO signIn = SignInService.loadSignInDetails();
        Thread.sleep(2000);
        signInpage.signIn(baseUrl, signIn.getUsername(), signIn.getPassword());
        Thread.sleep(2000);
        Assert.assertTrue(Boolean.TRUE);
    }

    @Test
    public void executeSignOutPage() throws Exception {
        SignOutPage signOut = PageFactory.initElements(driver, SignOutPage.class);
        Thread.sleep(2000);
        signOut.logoutPage();
        Thread.sleep(2000);
        Assert.assertTrue(Boolean.TRUE);
    }
}
