package apps.web.Wordpress;

import apps.web.Wordpress.pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestWordpress extends TestUtilities {



    @Test
    @Parameters({ "username", "password" })
    public void testOpenMediaPageSuccessful(String username, String password){
        mediaPage = new MediaPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickMediaItemOnHomePage();
        log.info("Verifying that 'Media' page is opened ...");
        Assert.assertTrue(mediaPage.isPageOpened(), "'Media' page wasn't opened");
    }

    @Test
    @Parameters({ "username", "password" })
    public void testOpenPagesPageSuccessful(String username, String password){
        pagesPage = new PagesPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickPagesItemOnHomePage();
        log.info("Verifying that 'Pages' page is opened ...");
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page wasn't opened");
    }

    @Test
    @Parameters({ "username", "password" })
    public void testOpenCommentsPageSuccessful(String username, String password){
        commentsPage = new CommentsPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickCommentsItemOnHomePage();
        log.info("Verifying that 'Comments' page is opened ...");
        Assert.assertTrue(commentsPage.isPageOpened(), "'Comments' page wasn't opened");
    }



}
