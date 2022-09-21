package platform.web.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import javax.annotation.Nullable;
import java.util.Objects;

public class BrowserDocumentReady implements ExpectedCondition<Boolean> {

    protected static JavascriptExecutor js(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    public Boolean apply(@Nullable WebDriver driver) {
        Objects.requireNonNull(driver);
        return "complete".equals(js(driver).executeScript("return document.readyState"));
    }
}