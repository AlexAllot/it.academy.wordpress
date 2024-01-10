package apps.web.Wordpress;

import apps.web.BasicWebScenario;
import apps.web.Wordpress.pages.*;
import data.constants.ApplicationIdentifier;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class TestUtilities extends BasicWebScenario {

    LoginPage loginPage;
    PostsPage postsPage;
    EditorPostsPage editorPostsPage;
    WordPressHomePage wordPressHomePage;
    MediaPage mediaPage;
    PagesPage pagesPage;
    CommentsPage commentsPage;
    WordpressOnAzurePublishPage wordpressOnAzureMainPage;
    String expectedTextTitle = "Test template13";
    SoftAssert softAssert;
    RequestSpecification requestSpecification;
    Response response;


    public TestUtilities() {
        super(ApplicationIdentifier.WORDPRESS);
    }

    @BeforeMethod
    public void openLoginPage() throws IOException {
        loginPage = new LoginPage(driver, log);
        log.info("Open page: " + getAppUrl());
        loginPage.openMainPage(getAppUrl());
    }

    @BeforeClass(alwaysRun = true)
    public void initSoftAssert() {
        softAssert = new SoftAssert();
    }

    @AfterClass(alwaysRun = true)
    public void assertAll() {
        softAssert.assertAll();
    }

}
