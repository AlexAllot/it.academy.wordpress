package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import data.constants.TopMenuItems;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WordPressHomePage extends BasePageObject {

    public WordPressHomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private final By displayNameLocator = By.xpath("//span[@class='display-name']");

    private static final String ITEM_PATTERN = "//*[contains(@class,'wp-menu-name') and contains(text(),'%s')]";

    public void clickPostsItemOnHomePage(){
        log.info("Clicking 'Posts' item on home page ...");
        waitForWebElementAndClick(getMenuItem(ITEM_PATTERN, TopMenuItems.POSTS), DEFAULT_WAIT_IN_SEC);
    }

    public void clickMediaItemOnHomePage(){
        log.info("Clicking 'Media' item on home page ...");
        waitForWebElementAndClick(getMenuItem(ITEM_PATTERN, TopMenuItems.MEDIA), DEFAULT_WAIT_IN_SEC);
    }

    public void clickPagesItemOnHomePage(){
        log.info("Clicking 'Pages' item on home page ...");
        waitForWebElementAndClick(getMenuItem(ITEM_PATTERN, TopMenuItems.PAGES), DEFAULT_WAIT_IN_SEC);
    }

    public void clickCommentsItemOnHomePage(){
        log.info("Clicking 'Comments' item on home page ...");
        waitForWebElementAndClick(getMenuItem(ITEM_PATTERN, TopMenuItems.COMMENTS), DEFAULT_WAIT_IN_SEC);
    }

    @Step("Get username after log in")
    public String getDisplayName(){
       log.info("Getting displayed userName after log in on site");
       return findElement(displayNameLocator).getText();
    }

}
