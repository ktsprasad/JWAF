package auto.utility;
/*
 * Created by: Anuj Kumar
 * Email: cdac.anuj@gmail.com
 * Date: 12-May-18
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class Init {
    protected WebDriver driver = null;

    /**
     * This function is used for doing web driver setup.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.out.println("*** Setup ***");
        //if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
        System.setProperty("webdriver.gecko.driver", "D:/git/JWAF-master/drivers/geckodriver.exe");
        //DesiredCapabilities capabilities = DesiredCapabilities.firefox();

     // Tell the Java bindings to use Marionette.
     // This will not be necessary in the future,
     // when Selenium will auto-detect what remote end
     // it is talking to.
     //capabilities.setCapability("marionette", true);

     //WebDriver driver = new RemoteWebDriver(capabilities); 
        
        //FirefoxProfile ffprofile = createFirefoxProfile();
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    /**
     * This function is quit the driver instance.
     */
    @AfterMethod(alwaysRun = true)
    public void teardown() {
        System.out.println("*** Teardown ***");
        if (driver != null)
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
