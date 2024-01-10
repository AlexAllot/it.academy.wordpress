package apps.web.Wordpress;

import apps.web.BasicWebScenario;
import apps.web.Wordpress.pages.LoginPage;
import data.constants.ApplicationIdentifier;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CsvDataProvidersUtils;

import java.io.IOException;
import java.util.Map;

public class TestNegativeLogIn extends BasicWebScenario {

    public TestNegativeLogIn() {
        super(ApplicationIdentifier.WORDPRESS);
    }

    @BeforeMethod
    public void openLoginPage() throws IOException {
        LoginPage loginPage = new LoginPage(driver, log);
        log.info("Open page: " + getAppUrl());
        loginPage.openMainPage(getAppUrl());
    }

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProvidersUtils.class)
    @TmsLink(value = "ID:TC030")
    public void negativeLogInTest(Map<String, String> testData) {

        String username = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        log.info("Starting negativeLogInTest for " + description);
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        Assert.assertEquals(loginPage.getLogInErrorMessage(), expectedErrorMessage);
    }

}
