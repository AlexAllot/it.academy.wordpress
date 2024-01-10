package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MediaPage extends BasePageObject {

    private final By titleMediaPageLocator = new By.ByXPath("//h1[contains(text(),'Media Library')]");

    public MediaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public boolean isPageOpened() {
        WebElement titleItem = waitForElementPresent(titleMediaPageLocator);
        return titleItem.isDisplayed();
    }
}
