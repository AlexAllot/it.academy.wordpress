package apps.web.Wordpress.pages;

import apps.web.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditorPostsPage extends BasePageObject {

    private final By addTitleLocator = new By.ByXPath("//*[@aria-label='Add title']");
    private final By saveDraftBtn = new By.ByXPath("//*[@aria-label='Save draft']");
    private final By savedBtn = new By.ByXPath("//*[@aria-label='Saved']");
    private final By viewPostsBtn = new By.ByXPath("//*[@aria-label='View Posts']");
    private final By publishBtn = new By.ByXPath("//*[@type='button' and contains(@class,'publish')]");

    private By frame = By.tagName("iframe");

    public EditorPostsPage(WebDriver driver) {
        super(driver);
    }

    public void createNewPostAndSaveToDraft() {
        switchToFrame(frame);
        addTitleToNewPostAndSaveToDraft();
        waitForElementPresent(savedBtn);
        clickMethod(viewPostsBtn);
    }

    @Override
    public boolean isPageOpened() {
        element = waitForElementPresent(addTitleLocator);
        return element.isDisplayed();
    }

    public void addTitleToNewPostAndSaveToDraft() {
        String expectedText = "Test template13";
        typeMethod(expectedText, addTitleLocator);
        switchFromFrame();
        waitForWebElementAndClick(saveDraftBtn, DEFAULT_WAIT_IN_SEC);
    }

    @Override
    public boolean isElementExist() {
        element = waitForElementPresent(savedBtn);
        return element.isDisplayed();
    }

}
