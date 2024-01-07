package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {

    private final By usernameLocator = By.id("user_login");
    private final By passwordLocator = By.id("user_pass");
    private final By logInBtnLocator = By.id("wp-submit");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openMainPage(String url){
        openUrl(url);
    }

    /** Execute log in */
    public WordPressHomePage logIn(String username, String password, int timeOutInSeconds){
        log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        typeMethod(username, usernameLocator);
        typeMethod(password, passwordLocator);
        waitForWebElementAndClick(logInBtnLocator, timeOutInSeconds);
        return new WordPressHomePage(driver, log);
    }

}
