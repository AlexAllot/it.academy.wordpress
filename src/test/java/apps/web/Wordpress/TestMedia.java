package apps.web.Wordpress;

import apps.web.Wordpress.pages.MediaPage;
import apps.web.Wordpress.pages.UploadNewMediaPage;
import apps.web.Wordpress.pages.WordPressHomePage;
import apps.web.Wordpress.pages.WordpressOnAzurePublishPage;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestMedia extends TestUtilities {

    @Test(priority = 1)
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

    @Test(priority = 2)
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
