package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

public class WordpressOnAzurePublishPage extends BasePageObject {

    private final By publishedPostsOnPage = new By.ByXPath("//a[@target='_self']");
    public String wordpressOnAzureMainPageUrl = "https://wordpress-test-app-for-selenium.azurewebsites.net/";

    public WordpressOnAzurePublishPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openMainPage() {
        openUrl(wordpressOnAzureMainPageUrl);
    }

    public List<String> listElements(){
        List<WebElement> listOfElements = driver.findElements(publishedPostsOnPage);
        return new ArrayList<>(listOfElements
                .stream()
                .map(WebElement::getText)
                .filter(a -> a.equals("Test template13"))
                .toList());
    }

}



