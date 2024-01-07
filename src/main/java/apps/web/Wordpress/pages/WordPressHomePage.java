package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WordPressHomePage extends BasePageObject {

    public WordPressHomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final By displayNameLocator = By.xpath("//span[@class='display-name']");

    public String getDisplayName(){
       log.info("Getting displayed userName after log in on site");
       return findElement(displayNameLocator).getText();
    }
}
