package apps.web.Wordpress;

import apps.web.Wordpress.pages.*;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestPosts extends TestUtilities {


    @Test(priority = 1)
    @Parameters({"username", "password"})
    @TmsLink(value = "ID:TC013")
    public void testOpenPostsPageSuccessful(String username, String password) {
        postsPage = new PostsPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickPostsItemOnHomePage();
        log.info("Verifying that 'Posts' page is opened ...");
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page wasn't opened");
    }

    @Test(priority = 2)
    @Parameters({"username", "password"})
    @TmsLink(value = "ID:TC017")
    public void testOpenEditPostsPageSuccessful(String username, String password) {
        postsPage = new PostsPage(driver, log);
        editorPostsPage = new EditorPostsPage(driver);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        postsPage.openEditorPostsPage();
        Assert.assertTrue(editorPostsPage.isPageOpened(), "'EditPostsPage' page wasn't opened");
    }

    @Test(priority = 2)
    @Parameters({"username", "password"})
    @TmsLink(value = "ID:TC017")
    public void testCreatePostAndSaveToDraftSuccessful(String username, String password) {
        postsPage = new PostsPage(driver, log);
        editorPostsPage = new EditorPostsPage(driver);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickPostsItemOnHomePage();
        log.info("Opening Editor page ...");
        postsPage.openEditorPostsPage();
        log.info("Creating new post and saved to draft ...");
        editorPostsPage.createNewPostAndSaveToDraft();
        log.info("Validation title post on 'Posts' page");
        Assert.assertTrue(postsPage.isPostExistOnPage(), "Post wasn't created!");
    }

    @Test(dependsOnMethods = "testCreatePostAndSaveToDraftSuccessful")
    @TmsLink(value = "ID:TC017")
    public void testPostIsNotPublishedOnAzurePage() {
        wordpressOnAzureMainPage = new WordpressOnAzurePublishPage(driver, log);
        log.info("Opening 'Wordpress On Azure' publish page ...");
        wordpressOnAzureMainPage.openMainPage();
        wordpressOnAzureMainPage
                .listElements()
                .forEach(expectedElement ->
                        softAssert.assertFalse(expectedElement.contains(expectedTextTitle), "Post " + expectedTextTitle + " exist on main page"));
                        softAssert.assertEquals(wordpressOnAzureMainPage.getCurrentUrl(), wordpressOnAzureMainPage.wordpressOnAzureMainPageUrl);
    }

    @Test(dependsOnMethods = "testPostIsNotPublishedOnAzurePage")
    @Parameters({"username", "password"})
    @TmsLink(value = "ID:TC018")
    public void testMovePostFromDraftToPublish(String username, String password) {
        postsPage = new PostsPage(driver, log);
        editorPostsPage = new EditorPostsPage(driver);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickPostsItemOnHomePage();
        log.info("Opening Editor page ...");
        postsPage.clickOnPost();
        editorPostsPage.clickOnPublishBtn();
        editorPostsPage.clickOnSecondPublishBtn();
        log.info("Checking post on publish page ...");
        Assert.assertTrue(postsPage.isPostExistOnPage(), "Post is not published");
    }

    @Test(alwaysRun = true, dependsOnMethods = "testMovePostFromDraftToPublish")
    @TmsLink(value = "ID:TC018")
    public void testPostIsPublishOnAzurePageThrowRestApi() {
        log.info("Checking status code and status line for published post");
        RestAssured.baseURI = "https://wordpress-test-app-for-selenium.azurewebsites.net/2024/01/10/test-template13/";
        requestSpecification = RestAssured.given();
        response = requestSpecification.get();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test(dependsOnMethods = "testMovePostFromDraftToPublish")
    @TmsLink(value = "ID:TC018")
    public void testPostIsPublishedOnAzurePage() {
        wordpressOnAzureMainPage = new WordpressOnAzurePublishPage(driver, log);
        log.info("Opening 'Wordpress On Azure' publish page ...");
        wordpressOnAzureMainPage.openMainPage();
        log.info("Checking post that post exist on main page ...");
        wordpressOnAzureMainPage
                .listElements()
                .forEach(expectedElement -> Assert.assertTrue(expectedElement.contains(expectedTextTitle), "Post " + expectedTextTitle + " is not exist on main page"));
    }

    @Test(alwaysRun = true, dependsOnMethods = "testPostIsPublishedOnAzurePage")
    @Parameters({"username", "password"})
    @TmsLink(value = "ID:TC020")
    public void testDeletePostSuccessful(String username, String password){
        postsPage = new PostsPage(driver, log);
        editorPostsPage = new EditorPostsPage(driver);
        wordPressHomePage = new WordPressHomePage(driver, log);
        wordpressOnAzureMainPage = new WordpressOnAzurePublishPage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickPostsItemOnHomePage();
        postsPage.moveToTrashElementAndClick();
        log.info("Checking post which was deleted ...");
        for (String expectedTitle : wordpressOnAzureMainPage.listElements()
             ) {
            Assert.assertFalse(expectedTitle.contains(expectedTextTitle));
        }
    }

}
