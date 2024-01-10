package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class PostsPage extends BasePageObject {

    private final By titlePostsPageLocator = new By.ByXPath("//h1[contains(text(),'Posts')]");
    private final By addNewBtn = new By.ByXPath("//a[contains(text(),'Add New')]");
    private final By expectedTitlePost = new By.ByXPath("//a[contains(text(),'Test template13')]");
    private final By trashBtn = new By.ByXPath("//div[contains(@class,'row-actions')]/span[3]");

    public PostsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public boolean isPageOpened() {
        WebElement titleItem = waitForElementPresent(titlePostsPageLocator);
        return titleItem.isDisplayed();
    }

    /** Find post with title "Test template13" */
    public boolean isPostExistOnPage(){
        return waitForElementPresent(expectedTitlePost).isDisplayed();
    }

    public void clickOnPost(){
        clickMethod(expectedTitlePost);
    }

    public void openEditorPostsPage(){
        waitForWebElementAndClick(addNewBtn, DEFAULT_WAIT_IN_SEC);
    }

    public void moveToTrashElementAndClick(){
        element = waitForElementPresent(expectedTitlePost);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        waitForWebElementAndClick(trashBtn, DEFAULT_WAIT_IN_SEC);
    }


}
