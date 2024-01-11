package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UploadNewMediaPage extends BasePageObject {

    public UploadNewMediaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final By choseFileFieldLocator = By.xpath("//input[@id='async-upload']");
    private final By uploadButtonLocator = By.xpath("//input[@type='submit']");
    private final By uploadedFilesLocator = By.xpath("//*[@class='filename']");
    private final By deleteBtn = By.xpath("//span[@class='delete']/a");

    @Step
    public void selectFile(String filename) {
        log.info("Selecting" + filename + "  file from Files folder");
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\files\\" + filename;
        typeMethod(filePath, choseFileFieldLocator);
        log.info("File selected");
    }

    /**
     * Push Upload button
     */
    @Step
    public void pushUploadButton() {
        log.info("Clicking on upload button");
        waitForWebElementAndClick(uploadButtonLocator, DEFAULT_WAIT_IN_SEC);
    }

    /**
     * Get names of uploaded files
     */
    @Step
    public String getUploadedFilesNames() {
        String names = waitForElementPresent(uploadedFilesLocator).getText();
        log.info("Uploaded files: " + names);
        return names;
    }

    @Step
    public void moveToTrashElementAndClick() {
        element = waitForElementPresent(uploadedFilesLocator);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        waitForWebElementAndClick(deleteBtn, DEFAULT_WAIT_IN_SEC);
    }

    @Step
    public void acceptAllert() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Step
    public boolean isElementExist(String filename) {
        return waitForElementPresent(uploadedFilesLocator).getText().contains(filename);
    }
}
