package platform.web.screens;

import org.openqa.selenium.By;

public class ProductScreen {
    public static final String SEARCHED_TEXT_LABEL = "//div[@id='center_column']//span[@class='lighter']";
    public static final String SEARCHED_NO_RESULT_LABEL = "//div[@id='center_column']//p[contains(@class,'alert')]";
    public static final String SEARCHED_PRODUCTS = "//li/div[@class='product-container']//a[@class='product-name']";
    public static final String PRODUCT_CONTAINER = "//li/div[@class='product-container']";
    public static final String BREADCRUMB = "//div[@id='center_column']//h1/span[@class='cat-name']";

    public static final By CLOSE_PRODUCT_QUICK_VIEW_MODAL = By.xpath("//a[@title='Close']");
    public static final By PRODUCT_SHORT_DESCRIPTION_TEXT = By.xpath("//div[@id='short_description_content']//p");
    public static final By PRODUCT_QUICK_VIEW_IFRAME = By.xpath("//iframe[contains(@id,'fancybox-frame')]");
    public static final By PRODUCT_QUICK_VIEW_LINK = By.xpath("//a[@class='quick-view']");
}