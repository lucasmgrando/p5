package practico5.main;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MercuryToursTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  
	  System.setProperty("webdriver.gecko.driver", "C:\\Users\\lucas\\Desktop\\p5\\geckodriver.exe");
	 
	  driver = new FirefoxDriver();
	  baseUrl = "http://newtours.demoaut.com/";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMain() throws Exception {
	  driver.get(baseUrl + "/");
	  driver.findElement(By.name("userName")).clear();
	  driver.findElement(By.name("userName")).sendKeys("tutorial");
	  driver.findElement(By.name("password")).clear();
	  driver.findElement(By.name("password")).sendKeys("tutorial");
	  driver.findElement(By.name("login")).click();
    
	  if(!isElementPresent(By.linkText("SIGN-OFF"))) fail("El link SIGN-OFF no existe");
    
	  new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText("London");
	  new Select(driver.findElement(By.name("toPort"))).selectByVisibleText("Paris");
	  // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
	  driver.findElement(By.name("findFlights")).click();
	  driver.findElement(By.name("reserveFlights")).click();
	  driver.findElement(By.name("passFirst0")).clear();
	  driver.findElement(By.name("passFirst0")).sendKeys("nombre");
	  driver.findElement(By.name("passLast0")).clear();
	  driver.findElement(By.name("passLast0")).sendKeys("apellido");
	  driver.findElement(By.name("buyFlights")).click();
	  driver.findElement(By.linkText("SIGN-OFF")).click();
  }
  

  @After
  public void tearDown() throws Exception {
	  driver.quit();
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
	  try {
		  driver.findElement(by);
		  return true;
	  } catch (NoSuchElementException e) {
		  return false;
	  }
  }

  private boolean isAlertPresent() {
	  try {
		  driver.switchTo().alert();
		  return true;
	  } catch (NoAlertPresentException e) {
		  return false;
	  }
  }

  private String closeAlertAndGetItsText() {
	  try {
		  Alert alert = driver.switchTo().alert();
		  String alertText = alert.getText();
		  if (acceptNextAlert) {
			  alert.accept();
		  } else {
			  alert.dismiss();
		  }
		  return alertText;
	  } finally {
		  acceptNextAlert = true;
	  }
  }
}

