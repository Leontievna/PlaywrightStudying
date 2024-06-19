package de.simplytest;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ShopPage {
    Page p;
    String NAMESHOP = "#main header h1";
    String CARTHASSUM = "#site-header-cart a span.amount";
    String CARTCONTAINS = ".woocommerce-mini-cart__buttons";
    String ADDALBUM = "[data-product_sku=\"woo-album\"]";
    String url = "https://demoproject.simplytest.de/";
    String expectedPriceAmount = "15,00";
    String defaultPriceAmount = "0,00";
    String pageName = "Shop";
    String cartPageLink = "View cart";

    public ShopPage(Page p) {
        this.p = p;
    }

    public void openPage() {
        p.navigate(url);
        assertThat(p.locator(NAMESHOP)).containsText(pageName);
        assertThat(p.locator(CARTHASSUM)).containsText(defaultPriceAmount);
    }

    public void buyAlbum() {
        Response response = p.waitForResponse(resp -> "https://autoprojekt.simplytest.de/?wc-ajax=add_to_cart"
                .equals(resp.url()) && resp.status() == 200, () -> {
            p.locator(ADDALBUM).click();
        });
        assertThat(p.locator(CARTHASSUM)).containsText(expectedPriceAmount);
    }

    public void checkCart() {
        p.locator(CARTHASSUM).hover();
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot3-1.png")));
        p.locator(CARTCONTAINS).getByText(cartPageLink).click();
    }


}
