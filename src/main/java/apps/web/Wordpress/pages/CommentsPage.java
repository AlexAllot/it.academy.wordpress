package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommentsPage extends BasePageObject {

    private final By titleCommentsPageLocator = new By.ByXPath("//h1[contains(text(),'Comments')]");

    public CommentsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public boolean isPageOpened() {
        WebElement titleElement = waitForElementPresent(titleCommentsPageLocator);
        return titleElement.isDisplayed();
    }

}
