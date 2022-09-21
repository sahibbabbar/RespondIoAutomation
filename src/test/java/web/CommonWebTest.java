package web;

import utils.Verify;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import platform.web.common.BrowserDocumentReady;

import java.time.Duration;

public class CommonWebTest {
    public WebDriver driver;
    public Actions action;
    public WebDriverWait wait;

    public Verify verify;

    @BeforeClass(alwaysRun = true)
    public synchronized void beforeClass() {
        verify = new Verify();
        startBrowser();
        action = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void startBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void scroll(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void pleaseWait() {
        sleepFor(1);
        wait.pollingEvery(Duration.ofMillis(125)).until(ExpectedConditions.and(
                new BrowserDocumentReady()
        ));
    }

    public void sleepFor(int second) {
        try {
            second = second * 1000;
            Thread.sleep(second);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}