package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject {

    private final By usernameLocator = By.id("user_login");
    private final By passwordLocator = By.id("user_pass");
    private final By logInBtnLocator = By.id("wp-submit");
    private final By errorMessageText = By.xpath("//*[contains(text(),'Error')]");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openMainPage(String url){
        openUrl(url);
    }

    /**
     * Execute log in
     */
    @Step("Log in")
    public void logIn(String username, String password, int timeOutInSeconds){
        log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
        typeMethod(username, usernameLocator);
        typeMethod(password, passwordLocator);
        waitForWebElementAndClick(logInBtnLocator, timeOutInSeconds);
    }

    public String getLogInErrorMessage(){
        return findElement(errorMessageText).getText();
    }

}
