package apps.web;

import data.constants.TopMenuItems;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;

public class BasePageObject {

    protected WebDriver driver;
    protected Logger log;
    public WebElement element;
    public int DEFAULT_WAIT_IN_SEC = 10;



    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Open page with given url
     */
    protected void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Find element with given locator
     */
    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Click on element with given locator when it's presence
     */
    protected void clickMethod(By locator) {
        waitForElementPresent(locator);
        findElement(locator).click();
    }

    /**
     * Click on element with given locator when it's clickable
     */
    public void waitForWebElementAndClick(By locator, int timeOutInSeconds){
        LocalDateTime now = LocalDateTime.now();
        while (LocalDateTime.now().isBefore(now.plusSeconds(timeOutInSeconds))) {
            try {
                new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(locator)).click();
                return;
            } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Type given text into element with given locator
     */
    protected void typeMethod(String text, By locator) {
        waitForElementPresent(locator);
        findElement(locator).sendKeys(text);
    }

    /**
     * Get URL of current page from browser
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Return true or false for element
     */
    public boolean isElementExist() {
        return false;
    }

    public boolean isPageOpened(){
        return false;
    }

    protected WebElement waitForElementPresent(By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public By getMenuItem(String locator, TopMenuItems menuItems){
        String xpath = String.format(locator, menuItems.getValue());
        return By.xpath(xpath);
    }
}
