package apps.web.Wordpress;

import apps.web.Wordpress.pages.*;
import io.qameta.allure.Allure;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestWordpressMainPage extends TestUtilities {

    @Test
    @Parameters({ "username", "password" })
    @TmsLink(value = "ID:TC011")
    public void testOpenMediaPageSuccessful(String username, String password){
        mediaPage = new MediaPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        Allure.addAttachment("Step", "Click on Media locator on home page");
        wordPressHomePage.clickMediaItemOnHomePage();
        log.info("Verifying that 'Media' page is opened ...");
        Assert.assertTrue(mediaPage.isPageOpened(), "'Media' page wasn't opened");
    }

    @Test
    @Parameters({ "username", "password" })
    @TmsLink(value = "ID:TC012")
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
    @TmsLink(value = "ID:TC014")
    public void testOpenCommentsPageSuccessful(String username, String password){
        commentsPage = new CommentsPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickCommentsItemOnHomePage();
        log.info("Verifying that 'Comments' page is opened ...");
        Assert.assertTrue(commentsPage.isPageOpened(), "'Comments' page wasn't opened");
    }

    @Test
    @Parameters({"username", "password"})
    public void testImageUploadSuccessful(String username, String password) {
        mediaPage = new MediaPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        uploadNewMediaPage = new UploadNewMediaPage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        Allure.addAttachment("Step", "Click on Media item on home page");
        wordPressHomePage.clickMediaItemOnHomePage();
        mediaPage.openUploadMediaPage();
        uploadNewMediaPage.selectFile(filename);
        uploadNewMediaPage.pushUploadButton();
        Assert.assertTrue(uploadNewMediaPage.getUploadedFilesNames().contains(filename));
    }

    @Test(dependsOnMethods = "testImageUploadSuccessful")
    @Parameters({"username", "password"})
    public void testDeleteFileSuccessful(String username, String password) {
        mediaPage = new MediaPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        uploadNewMediaPage = new UploadNewMediaPage(driver, log);
        wordpressOnAzureMainPage = new WordpressOnAzurePublishPage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickMediaItemOnHomePage();
        uploadNewMediaPage.moveToTrashElementAndClick();
        uploadNewMediaPage.acceptAllert();
        wordpressOnAzureMainPage.goToDashboard();
        wordPressHomePage.clickMediaItemOnHomePage();
        Assert.assertFalse(uploadNewMediaPage.isElementExist(filename));
    }

}
