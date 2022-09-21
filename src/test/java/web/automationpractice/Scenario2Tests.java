package web.automationpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import platform.web.constants.Urls;
import platform.web.screens.FilterPanel;
import platform.web.screens.HomeScreen;
import platform.web.screens.ProductScreen;
import platform.web.screens.WomenCategoryScreen;
import web.CommonWebTest;

import java.util.List;
import java.util.Random;

/**
 * Scenario 2: Users are able to filter search results under `Women` category by `Color` and `Category`
 */
public class Scenario2Tests extends CommonWebTest {
    private int RANDOM_CATEGORY_INDEX;
    private int RANDOM_COLOR_INDEX;

    @Test(description = "Verify that the users are able to filter search results under `Women` category by `Color` and `Category`")
    public void testFilterResultsUnderWomenByColorAndCategory() {
        // Navigate to the homepage
        driver.get(Urls.URL_HOME_PAGE);
        driver.findElement(By.xpath(HomeScreen.TOP_MENU_WOMEN_LINK)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(WomenCategoryScreen.WOMEN_CATEGORY_TITLE)));
        verify.assertEquals(driver.findElement(By.xpath(ProductScreen.BREADCRUMB)).getText().trim(), "WOMEN", "Verify the breadcrumb displaying the correct value.");

        // Filter Categories and verify the breadcrumb content
        filterCategories();
        scroll(By.xpath(HomeScreen.TOP_MENU_WOMEN_LINK));
        String categoryName = driver.findElement(By.xpath(FilterPanel.CATEGORIES + "[" + RANDOM_CATEGORY_INDEX + "]/label//a")).getText().split(" ")[0].toLowerCase();
        verify.assertEquals(driver.findElement(By.xpath(ProductScreen.BREADCRUMB)).getText(), "WOMEN > CATEGORIES " + categoryName.toUpperCase(), "Verify the breadcrumb displaying the correct value.");

        // Filter Colors and verify the breadcrumb content
        filterColors();
        scroll(By.xpath(HomeScreen.TOP_MENU_WOMEN_LINK));
        Integer colorCount = Integer.parseInt(driver.findElement(By.xpath(FilterPanel.COLORS + "[" + RANDOM_COLOR_INDEX + "]/label//span")).getText().replaceAll("\\p{P}", ""));
        String colorName = driver.findElement(By.xpath(FilterPanel.COLORS + "[" + RANDOM_COLOR_INDEX + "]/label//a")).getText().split(" ")[0].toLowerCase();
        verify.assertEquals(driver.findElement(By.xpath(ProductScreen.BREADCRUMB)).getText(), "WOMEN > CATEGORIES " + categoryName.toUpperCase() + " > COLOR " + colorName.toUpperCase(), "Verify the breadcrumb displaying the correct value.");

        pleaseWait();
        Integer categoryCount = Integer.parseInt(driver.findElement(By.xpath(FilterPanel.CATEGORIES + "[" + RANDOM_CATEGORY_INDEX + "]/label//span")).getText().replaceAll("\\p{P}", ""));
        verify.assertEquals(categoryCount, colorCount, "Verify the count of both the filters is equal");

        List<WebElement> productElements = driver.findElements(By.xpath(ProductScreen.PRODUCT_CONTAINER));
        if (productElements.size() != 0) {
            for (WebElement productElement : productElements) {
                verify.assertTrue(productElement.findElement(By.xpath(".//a[contains(@href,'" + colorName + "')]")).isDisplayed(), "Verify the filtered color is same in the listed product");
            }
        }
    }

    /**
     * Getting the existing list of categories and filtering on random basis.
     */
    private void filterCategories() {
        List<WebElement> categories = driver.findElements(By.xpath(FilterPanel.CATEGORIES));
        RANDOM_CATEGORY_INDEX = new Random().nextInt(categories.size()) + 1;

        if (categories.size() != 0) {
            driver.findElement(By.xpath(FilterPanel.CATEGORIES + "[" + RANDOM_CATEGORY_INDEX + "]")).click();
            scroll(By.xpath(ProductScreen.FILTER_SECTION + "//li[1]"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductScreen.FILTER_SECTION + "//li[1]")));
        }
    }

    /**
     * Getting the existing list of colors and filtering on random basis.
     */
    private void filterColors() {
        scroll(By.xpath(FilterPanel.COLORS_FILTER_HEADING));
        pleaseWait();
        List<WebElement> colors = driver.findElements(By.xpath(FilterPanel.COLORS));

        if (colors.size() != 0) {
            RANDOM_COLOR_INDEX = new Random().nextInt(colors.size()) + 1;
            pleaseWait();
            driver.findElement(By.xpath(FilterPanel.COLORS + "[" + RANDOM_COLOR_INDEX + "]/input")).click();
            scroll(By.xpath(ProductScreen.FILTER_SECTION + "//li[2]"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductScreen.FILTER_SECTION + "//li[2]")));
        }
    }
}