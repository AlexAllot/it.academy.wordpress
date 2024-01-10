package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PagesPage extends BasePageObject {

    private final By titlePagesPageLocator = new By.ByXPath("//h1[contains(text(),'Pages')]");

    public PagesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public boolean isPageOpened() {
        WebElement titleElement = waitForElementPresent(titlePagesPageLocator);
        return titleElement.isDisplayed();
    }
}
