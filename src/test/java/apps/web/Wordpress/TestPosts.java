package apps.web.Wordpress;

import apps.web.Wordpress.pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestPosts extends TestUtilities {

    //@Test
    @Parameters({"username", "password"})
    public void testOpenPostsPageSuccessful(String username, String password) {
        postsPage = new PostsPage(driver, log);
        wordPressHomePage = new WordPressHomePage(driver, log);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        wordPressHomePage.clickPostsItemOnHomePage();
        log.info("Verifying that 'Posts' page is opened ...");
        Assert.assertTrue(postsPage.isPageOpened(), "'Posts' page wasn't opened");
    }

    //@Test
    @Parameters({"username", "password"})
    public void testOpenEditPostsPageSuccessful(String username, String password) {
        postsPage = new PostsPage(driver, log);
        editorPostsPage = new EditorPostsPage(driver);
        loginPage.logIn(username, password, loginPage.DEFAULT_WAIT_IN_SEC);
        postsPage.openEditorPostsPage();
        Assert.assertTrue(editorPostsPage.isPageOpened(), "'EditPostsPage' page wasn't opened");
    }

    @Test(priority = 1)
    @Parameters({"username", "password"})
    public void testCreatePostAndSaveToDraftSuccessful(String username, String password){
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

    @Test(priority = 2)
    public void postIsNotPublishedOnAzurePage() {
        softAssert = new SoftAssert();
        wordpressOnAzureMainPage = new WordpressOnAzurePublishPage(driver, log);
        log.info("Opening 'Wordpress On Azure' publish page ...");
        wordpressOnAzureMainPage.openMainPage();
        wordpressOnAzureMainPage
                .postIsNotExistOnAzurePage()
                .forEach(expectedElement ->
                        softAssert.assertFalse(expectedElement.contains(expectedTextTitle), "Post " + expectedTextTitle + " exist on main page"));
                        softAssert.assertEquals(wordpressOnAzureMainPage.getCurrentUrl(), wordpressOnAzureMainPage.wordpressOnAzureMainPageUrl);
    }

    //@Test
    public void postIsPublishedOnAzurePage() {
        wordpressOnAzureMainPage = new WordpressOnAzurePublishPage(driver, log);
        log.info("Opening 'Wordpress On Azure' publish page ...");
        wordpressOnAzureMainPage.openMainPage();
        wordpressOnAzureMainPage
                .postIsNotExistOnAzurePage()
                .forEach(expectedElement -> Assert.assertTrue(expectedElement.contains(expectedTextTitle), "Post " + expectedTextTitle + " exist on main page"));
    }

}
