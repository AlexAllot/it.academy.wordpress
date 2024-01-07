package setup;

import data.constants.ApplicationIdentifier;
import data.constants.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;
    private static Properties properties;

    protected ApplicationIdentifier application;

    protected Platform platform;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, ITestContext ctx) {
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();
        driver.manage().window().maximize();
    }

    /**
     * close() - It is used to close the browser or page currently which is having the focus.
     * quit() - It is used to shut down the web driver instance or destroy the web driver instance(Close all the windows).
     */
    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.close();
            driver.quit();
            driver = null;
            log.info("Driver closed after testing!");
        }
    }

    protected String getAppUrl() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/apps/" + application.getPropertiesPath()));
        return properties.getProperty("url");
    }

}
