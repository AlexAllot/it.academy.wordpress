package utils;


import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ScreenshotException;


import static org.openqa.selenium.OutputType.BYTES;


public class AllureAttachmentUtil {

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] addScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(BYTES);
        } catch (ScreenshotException e) {
            return new byte[0];
        }
    }

}

