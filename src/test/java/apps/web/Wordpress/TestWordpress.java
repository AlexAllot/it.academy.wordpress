package apps.web.Wordpress;

import apps.web.BasicWebScenario;
import apps.web.Wordpress.pages.LoginPage;
import apps.web.Wordpress.pages.PostsPage;
import apps.web.Wordpress.pages.WordPressHomePage;
import data.constants.ApplicationIdentifier;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestWordpress extends BasicWebScenario {

    public TestWordpress() {
        super(ApplicationIdentifier.WORDPRESS);
    }

    @BeforeMethod
    public void openLoginPage() throws IOException {
        LoginPage loginPage = new LoginPage(driver, log);
        log.info("Open page: " + getAppUrl());
        loginPage.openMainPage(getAppUrl());
    }

    @Test
    @Parameters({ "username", "password" })
    public void testPostsElementIsPresent(String username, String password){
        LoginPage loginPage = new LoginPage(driver, log);
        PostsPage postsPage = new PostsPage(driver, log);
        WordPressHomePage wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickPostsItemOnHomePage();
        log.info("Verifying that title item on 'Posts' page is exist");
        Assert.assertTrue(postsPage.isElementExist(), "Title 'Posts' is not exist on the page");
    }



}
