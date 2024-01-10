package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PostsPage extends BasePageObject {

    private final By titlePostsPageLocator = new By.ByXPath("//h1[contains(text(),'Posts')]");
    private final By addNewBtn = new By.ByXPath("//a[contains(text(),'Add New')]");
    private final By expectedTitlePost = new By.ByXPath("//a[contains(text(),'Test template13')]");

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

    public void openEditorPostsPage(){
        waitForWebElementAndClick(addNewBtn, DEFAULT_WAIT_IN_SEC);
    }


}
