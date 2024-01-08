package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PostsPage extends BasePageObject {

    By titlePostsPageLocator = new By.ByXPath("//h1[contains(text(),'Posts')]");

    public PostsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public boolean isElementExist() {
        WebElement titleItem = waitForElementPresent(titlePostsPageLocator);
        return titleItem.isDisplayed();
    }
}
