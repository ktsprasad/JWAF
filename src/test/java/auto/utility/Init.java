package auto.utility;
/*
 * Created by: Prasad Email: ktsprasad@gmail.com Date: 26-Sep-18
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Init {
  protected WebDriver driver = null;
  // Create global variable which will be used in all method
  ExtentReports extent;
  ExtentTest logger;

  /**
   * This function is used for doing web driver setup.
   */
  @BeforeMethod(alwaysRun = true)
  public void setUp(ITestResult result) {
    System.out.println("*** Setup ***");
    String fileName = new SimpleDateFormat("yyyyMMddHHmm'.html'").format(new Date());
    ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/"+fileName);
    extent = new ExtentReports();
    extent.attachReporter(reporter);
    logger=extent.createTest("test name:" + result.getMethod().getMethodName());
    // if you didn't update the Path system variable to add the full directory path to the
    // executable as above mentioned then doing this directly through code
    System.setProperty("webdriver.gecko.driver", "D:/git/JWAF-master/drivers/geckodriver.exe");
    // DesiredCapabilities capabilities = DesiredCapabilities.firefox();

    // Tell the Java bindings to use Marionette.
    // This will not be necessary in the future,
    // when Selenium will auto-detect what remote end
    // it is talking to.
    // capabilities.setCapability("marionette", true);

    // WebDriver driver = new RemoteWebDriver(capabilities);

    // FirefoxProfile ffprofile = createFirefoxProfile();
    driver = new FirefoxDriver();
    driver.get("http://the-internet.herokuapp.com/");
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }
  

  // Actual Test which will start the application and verify the title
@Test
public void loginTest() throws IOException
{
	//System.setProperty("webdriver.chrome.driver", "/Users/mukeshotwani/Desktop/chromedriver");
	//driver=new ChromeDriver();
	//driver.get("http://www.google.com");
	System.out.println("title is "+driver.getTitle());
	//Assert.assertTrue(driver.getTitle().contains("Mukesh"));
}

  /**
   * This function is quit the driver instance.
   */
//This will run after testcase and it will capture screenshot and add in report
  @AfterMethod(alwaysRun = true)
  public void teardown(ITestResult result) throws IOException {
    logger=extent.createTest("test name:" + result.getMethod().getMethodName());
    System.out.println("*** Teardown ***");
    if (driver != null)
      driver.quit();
    if(result.getStatus()==ITestResult.FAILURE)
	{
		String temp=auto.utility.Utility.getScreenshot(driver);
		
		logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
	}
	
	extent.flush();
	driver.quit();

  }

  public FirefoxProfile createFirefoxProfile() {

    FirefoxProfile firefoxProfile = new FirefoxProfile();

    firefoxProfile = new FirefoxProfile();
    firefoxProfile.setPreference("browser.download.folderList", 2);
    firefoxProfile.setPreference("browser.download.dir", "E:\\git_projects\\download");
    firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
        "text/csv,application/pdf,application/vnd.ms-excel,application/octet-stream");
    firefoxProfile.setPreference("pdfjs.disabled", true);

    return firefoxProfile;
  }

}
