package web.automationpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import platform.web.constants.Urls;
import platform.web.screens.HomeScreen;
import platform.web.screens.ProductScreen;
import web.CommonWebTest;

import java.util.List;
import java.util.Random;

/**
 * Scenario 3: Users are able to view the details of any clothing item from the `POPULAR` section and add them to the cart.
 */
public class Scenario3Tests extends CommonWebTest {
    private String productName;

    @Test(description = "Verify that the users are able to view the details of any clothing item from the `POPULAR` section and add them to the cart.")
    public void testFilterResultsUnderWomenByColorAndCategory() {
        // Navigate to the homepage
        driver.get(Urls.URL_HOME_PAGE);
        scroll(By.xpath(HomeScreen.POPULAR_PRODUCTS_SECTION));

        // Select any item from the `POPULAR` section.
        addAnyPopularProductToCart();

        // Validate that the item has successfully been added to the cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ProductScreen.CART_MODAL)));
        verify.assertEquals(driver.findElement(By.xpath(ProductScreen.SELECTED_PRODUCT)).getText(), productName, "Verify the correct product successfully added to the shopping cart");
        verify.assertEquals(driver.findElement(By.xpath(ProductScreen.SELECTED_PRODUCT_HEADER)).getText().trim(), "There is 1 item in your cart.", "Verify the correct heading is displayed");
    }

    /**
     * Helper method to select any item from the `POPULAR` section and add to cart.
     */
    private void addAnyPopularProductToCart() {
        List<WebElement> products = driver.findElements(By.xpath(HomeScreen.POPULAR_PRODUCTS));
        int RANDOM_POPULAR_PRODUCT_INDEX = new Random().nextInt(products.size()) + 1;
        if (products.size() != 0) {
            String productLocator = HomeScreen.POPULAR_PRODUCTS + "[" + RANDOM_POPULAR_PRODUCT_INDEX + "]";
            productName = driver.findElement(By.xpath(productLocator + ProductScreen.PRODUCT_NAME)).getText();
            action.moveToElement(driver.findElement(By.xpath(productLocator))).perform();
            action.moveToElement(driver.findElement(By.xpath(productLocator + ProductScreen.ADD_TO_CART_LINK))).click().perform();
        }
    }
}