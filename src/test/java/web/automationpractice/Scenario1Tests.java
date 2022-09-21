package web.automationpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import platform.web.constants.Urls;
import platform.web.screens.HomeScreen;
import platform.web.screens.ProductScreen;
import web.CommonWebTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Scenario 1: Users are able to search for an item using the search bar.
 */
public class Scenario1Tests extends CommonWebTest {

    public static final String[] VALID_SEARCH_TEXTS = {"skirt", "printed", "shirt", "dress"};
    public static final String[] INVALID_SEARCH_TEXTS = {"1234", "xkml1232", "!#@#$%#%"};

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver.get(Urls.URL_HOME_PAGE);
    }

    @Test(description = "Verify that the users are able to search for an item using the search bar with valid set of data.")
    public void searchBarWithValidTestDataTest() {
        String searchText = Arrays.asList(VALID_SEARCH_TEXTS).get(new Random().nextInt(VALID_SEARCH_TEXTS.length));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomeScreen.SEARCH_BOX)));
        driver.findElement(By.xpath(HomeScreen.SEARCH_BOX)).sendKeys(searchText);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomeScreen.SEARCH_BUTTON)));
        driver.findElement(By.xpath(HomeScreen.SEARCH_BUTTON)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductScreen.SEARCHED_TEXT_LABEL)));
        String actualTextLabel = driver.findElement(By.xpath(ProductScreen.SEARCHED_TEXT_LABEL)).getText().toLowerCase();
        verify.assertTrue(actualTextLabel.contains(searchText), "Verify the search label should match with the searched term");

        List<WebElement> productElements = driver.findElements(By.xpath(ProductScreen.SEARCHED_PRODUCTS));
        if (productElements.size() != 0) {
            for (WebElement productElement : productElements) {
                if (productElement.getText().toLowerCase().contains(searchText)) {
                    verify.assertTrue(true, "Verify the product displayed contains the searched term.");
                } else {
                    scroll(productElement);

                    //Performing the mouse hover action on the product element.
                    action.moveToElement(productElement).perform();

                    wait.until(ExpectedConditions.visibilityOfElementLocated(ProductScreen.PRODUCT_QUICK_VIEW_LINK));
                    driver.findElement(ProductScreen.PRODUCT_QUICK_VIEW_LINK).click();

                    //Waiting for the product quick view iframe modal visibility.
                    wait.until(ExpectedConditions.visibilityOfElementLocated(ProductScreen.PRODUCT_QUICK_VIEW_IFRAME));
                    driver.switchTo().frame(driver.findElement(ProductScreen.PRODUCT_QUICK_VIEW_IFRAME));
                    wait.until(ExpectedConditions.visibilityOfElementLocated(ProductScreen.PRODUCT_SHORT_DESCRIPTION_TEXT));
                    String actualProductDescriptionContent = driver.findElement(ProductScreen.PRODUCT_SHORT_DESCRIPTION_TEXT).getText().toLowerCase();
                    verify.assertTrue(actualProductDescriptionContent.contains(searchText), "Verify the product displayed contains the searched term.");

                    //Switching back to the default content.
                    driver.switchTo().defaultContent();
                    wait.until(ExpectedConditions.elementToBeClickable(ProductScreen.CLOSE_PRODUCT_QUICK_VIEW_MODAL));
                    driver.findElement(ProductScreen.CLOSE_PRODUCT_QUICK_VIEW_MODAL).click();
                }
            }
        }
    }

    @Test(description = "Verify that the users are able to search for an item using the search bar with invalid set of data.")
    public void searchBarWithInvalidTestDataTest() {
        String searchText = Arrays.asList(INVALID_SEARCH_TEXTS).get(new Random().nextInt(INVALID_SEARCH_TEXTS.length));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomeScreen.SEARCH_BOX)));
        driver.findElement(By.xpath(HomeScreen.SEARCH_BOX)).sendKeys(searchText);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomeScreen.SEARCH_BUTTON)));
        driver.findElement(By.xpath(HomeScreen.SEARCH_BUTTON)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductScreen.SEARCHED_NO_RESULT_LABEL)));
        String actualTextLabel = driver.findElement(By.xpath(ProductScreen.SEARCHED_NO_RESULT_LABEL)).getText();
        verify.assertEquals(actualTextLabel, "No results were found for your search \"" + searchText + "\"", "Verify the search label should match with the searched term, and no results displayed");
    }

    @Test(description = "Verify that the users are able to search for an item using the search bar with nested html data.")
    public void searchBarWithClickJackingTestDataTest() {
        String searchText = "<html><p>Hello</p></html>";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomeScreen.SEARCH_BOX)));
        driver.findElement(By.xpath(HomeScreen.SEARCH_BOX)).sendKeys(searchText);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HomeScreen.SEARCH_BUTTON)));
        driver.findElement(By.xpath(HomeScreen.SEARCH_BUTTON)).click();

        verify.assertEquals(driver.getTitle(), "Error 406 - Not Acceptable", "Verify the title 'Error 406 - Not Acceptable'");
    }
}