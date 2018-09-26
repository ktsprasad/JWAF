package auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import auto.utility.Services;
import static org.testng.Assert.assertEquals;

public class DynamicloadingsPage extends Services {

    private final static String HEADING = "Dynamically Loaded Page Elements";
    private final static String SUB_HEADING_1 = "Example 1: Element on page that is hidden";
    private final static String SUB_HEADING_2 = "Example 2: Element rendered after the fact";
    private String xpathHeading = "//h3";
    private String xpathSubHeading = "//h4";
    private String xpathBtn = "//div[@id='start']/button";
    private String xpathCheckbox = "//div[@id='checkbox']";
    private String xpathLoading = "//div[@id='loading']";
    private String xpathLinks = "//a[contains(text(),'**link**')]";
    private String xpathFinish = "//div[@id='finish']";


    public DynamicloadingsPage(WebDriver driver) {
        super(driver);
    }

    public void verifyDynamicControlsPageHeader() {
        WebElement headerEle = driver.findElement(By.xpath(xpathHeading));
        String actualHeading = headerEle.getText();
        assertEquals(actualHeading, HEADING,
                "Actual heading '" + actualHeading + "' should be same as expected '" + HEADING + "'.");
    }

    public void clickOnLink(String link) {

        String xpath = xpathLinks.replace("**link**", link);
        assertAndClick(xpath);
        waitForElement(xpathSubHeading);
        String actualHeading = driver.findElement(By.xpath(xpathSubHeading)).getText();

        if (link.contains("Example 1")) {
            Assert.assertEquals(actualHeading, SUB_HEADING_1);
        } else {
            Assert.assertEquals(actualHeading, SUB_HEADING_2);
        }

    }

    public void makeHiddenElementVisible() {

        assertElementPresentByXpath(xpathFinish);
        assertElementVisible(xpathFinish, false);
        assertAndClick(xpathBtn);
        waitForLoading();
        assertElementPresentByXpath(xpathFinish);
        assertElementVisible(xpathFinish, true);


    }

    public void renderNewElement() {

        assertElementNotPresentByXpath(xpathFinish);
        assertAndClick(xpathBtn);
        waitForLoading();
        assertElementPresentByXpath(xpathFinish);
        assertElementVisible(xpathFinish, true);


    }


    protected void waitForLoading() {
        waitForElement(xpathLoading);
        waitForElementVisible(xpathLoading);
        assertElementVisible(xpathLoading, true);
        waitForElementInVisible(xpathLoading);
        assertElementVisible(xpathLoading, false);
    }

}
