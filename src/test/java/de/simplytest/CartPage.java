package de.simplytest;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPage {
    String checkoutPageUrl = "https://autoprojekt.simplytest.de/checkout/";
    String CARTHASSUM = "#site-header-cart a span.amount";
    String CARTUPDATE = "button[name=\"update_cart\"]";
    String CARTUPDATEDAPPROVE = ".woocommerce-message[role=\"alert\"]";
    String QUANTITY = "input[title=\"Qty\"]";
    String PROCEEDTOCHECKOUT = ".wc-proceed-to-checkout .wp-element-button";
    Page p;
    public CartPage(Page p) {
        this.p = p;
    }

    @Test
    public void submitResult() {
        p.locator(QUANTITY).fill("2");
        p.locator(CARTUPDATE).click();
        assertThat(p.locator(CARTUPDATEDAPPROVE)).containsText("\n\t\tCart updated.\t");
        assertThat(p.locator(CARTHASSUM)).containsText("30,00");
        p.locator(PROCEEDTOCHECKOUT).click();
        assertThat(p).hasURL(checkoutPageUrl);
    }
}
