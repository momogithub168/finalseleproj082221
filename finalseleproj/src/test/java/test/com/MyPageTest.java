/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author jmowa
 */
public class MyPageTest {

    private static WebDriver driver;
    private String baseUrl;

    public MyPageTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "c:\\data\\chromedriver.exe");
        //driver = new ChromeDriver();
        ChromeOptions op = new ChromeOptions();
        op.setHeadless(true);
        op.addArguments("window-size=1920,1080");
        driver = new ChromeDriver(op);
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void executeLoginPageModule() throws Exception {
        LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
        loginpage.loginToFlighApplication("mercury", "mercury");
    }

    @Test
    public void executeLogoutPage() throws Exception {
        LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
        Thread.sleep(2000);
        //Advisable to use explicit wait to pause the driver.
        logout.logoutPage();
        Thread.sleep(2000);

    }
}
