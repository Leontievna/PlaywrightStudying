package de.simplytest;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ShopPage extends BaseTest {
    String NAMESHOP = "#main header h1";
    String CARTHASSUM = "#site-header-cart a span.amount";
    String CARTCONTAINS = "a.added_to_cart";
    String ADDALBUM = "[data-product_sku=\"woo-album\"]";
    String url = "https://demoproject.simplytest.de/";
    String expectedPriceAmount = "15,00";
    String defaultPriceAmount = "0,00";
    String pageName = "Shop";
    String cartPageLink = "View cart";

    public ShopPage(Page p) {
        this.p = p;
    }
    @Step("Open page 'demoproject'")
    public void openPage() {
        p.navigate(url);
        assertThat(p.locator(NAMESHOP)).containsText(pageName);
        assertThat(p.locator(CARTHASSUM)).containsText(defaultPriceAmount);
    }
    @Step("Buy album")
    public void buyAlbum() {
        assertThat(p.locator(CARTHASSUM)).containsText(expectedPriceAmount);
    }
    @Step("Check cart")
    public void checkCart() {
        p.locator(CARTHASSUM).hover();
        p.locator(CARTCONTAINS).getByText(cartPageLink).click();
    }


}
