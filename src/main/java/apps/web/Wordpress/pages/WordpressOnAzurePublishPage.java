package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public List<String> postIsNotExistOnAzurePage() {
        return getTextFromListOfElements(publishedPostsOnPage);
    }

    public List<String> postIsExistOnAzurePage() {
        return getTextFromListOfElements(publishedPostsOnPage);
    }


}



