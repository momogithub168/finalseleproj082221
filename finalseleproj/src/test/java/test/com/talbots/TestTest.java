/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com.talbots;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author jmowa
 */
public class TestTest {
    private WebDriver driver;
    private String baseUrl;
   
    public TestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:\\data\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions(); 
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
        driver = new ChromeDriver(options);
        
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
  public void testT55() throws Exception {
    driver.get("https://www.talbots.com/login?original=%2Faccount");
   
    driver.findElement(By.id("dwfrm_login_username")).click();
    driver.findElement(By.id("dwfrm_login_username")).clear();
    driver.findElement(By.id("dwfrm_login_username")).sendKeys("abc@abc.com");
//    driver.findElement(By.id("dwfrm_login_password")).click();
//    driver.findElement(By.id("dwfrm_login_password")).clear();
//    driver.findElement(By.id("dwfrm_login_password")).sendKeys("abc");
    driver.findElement(By.name("dwfrm_login_login")).click();
    //driver.findElement(By.xpath("//form[@id='dwfrm_login']/div")).click();
    //System.out.println(driver.findElement(By.xpath("//*[@id=\"dwfrm_login\"]/div[@class='error-form']")).getText());
    System.out.println(driver.findElement(By.xpath("//*[@id=\"dwfrm_login_password-error\"]")).getText());      
  }
}
