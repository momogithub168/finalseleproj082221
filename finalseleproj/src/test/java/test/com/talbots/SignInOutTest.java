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
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
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
import test.com.signin.ShowHideSignInPage;
import test.com.signin.SignInPage;
import test.com.signin.SignOutPage;

/**
 *
 * @author Mo Wan
 */
public class SignInOutTest {

    private static WebDriver driver;
    private static String baseUrl;
    private static List<SignInVO> infoList;

    public SignInOutTest() {

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
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
        // implicit wait condition
        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
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
        SignInVO info = infoList.stream().filter(v -> v.getId() == 5).findFirst().get();
       //Thread.sleep(2000);
        signInpage.signIn(baseUrl, info.getUsername(), info.getPassword());
       //Thread.sleep(2000);
        Assert.assertTrue(Boolean.TRUE);
    }

    @Test
    public void executeSignOutPage() throws Exception {
        SignOutPage signOut = PageFactory.initElements(driver, SignOutPage.class);
        signOut.logoutPage();
       // Thread.sleep(2000);
        Assert.assertTrue(Boolean.TRUE);
    }

    @Test
    public void executeShowHideModule() throws Exception {
        SignInPage signInPage = PageFactory.initElements(driver, ShowHideSignInPage.class);
        SignInVO info = infoList.stream().filter(v -> v.getId() == 6).findFirst().get();
        signInPage.signIn(baseUrl, info.getUsername(), info.getPassword());
        //take image
        //File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(scrFile, new File("./image.png"));
        Assert.assertTrue(signInPage.IsShowHideWorking());
    }
}
