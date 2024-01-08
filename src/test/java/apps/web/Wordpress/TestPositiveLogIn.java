package apps.web.Wordpress;

import apps.web.BasicWebScenario;
import apps.web.Wordpress.pages.LoginPage;
import apps.web.Wordpress.pages.WordPressHomePage;
import data.constants.ApplicationIdentifier;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPositiveLogIn extends BasicWebScenario {

    public TestPositiveLogIn() {
        super(ApplicationIdentifier.WORDPRESS);
    }

    @BeforeMethod
    public void openLoginPage() throws IOException {
        LoginPage loginPage = new LoginPage(driver, log);
        log.info("Open page: " + getAppUrl());
        loginPage.openMainPage(getAppUrl());
    }

    @DataProvider(name = "validUserNameAndPassword")
    public Object[][] dpMethod() {
        return new Object[][]{
                {"testAccount_5", "Android2021", "test account_5"}, // Credential for ‘Administrator’ account
                {"testAccount_1", "Android2023", "test account_1"}, // Credential for ‘Subscriber’ account
                {"testAccount_2", "Android2024", "test account_2"}, // Credential for ‘Contributor’ account
                {"testAccount_3", "Android2027", "test account_3"}, // Credential for ‘Author’ account
                {"testAccount_4", "Android2030", "test account_4"}  // Credential for ‘Editor’ account
        };
    }

    @Test(dataProvider = "validUserNameAndPassword")
    public void testUserNameAndPasswordValid(String userName, String password, String expectedName) {
        LoginPage loginPage = new LoginPage(driver, log);
        WordPressHomePage wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(userName, password, loginPage.DEFAULT_WAIT_IN_SEC);
        Assert.assertEquals(expectedName, wordPressHomePage.getDisplayName());
    }

}
