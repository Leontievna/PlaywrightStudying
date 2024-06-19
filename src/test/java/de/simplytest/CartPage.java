package de.simplytest;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPage {
    String checkoutPageUrl = "https://autoprojekt.simplytest.de/checkout/";
    String CARTHASSUM = "#site-header-cart a span.amount";
    String CARTUPDATE = "button[name=\"update_cart\"]";
    String CARTUPDATEDAPPROVE = ".woocommerce-message[role=\"alert\"]";
    String QUANTITY = "input[title=\"Qty\"]";
    String PROCEEDTOCHECKOUT = ".wc-proceed-to-checkout .wp-element-button";
    String expectedSumAmount = "30,00";
    String quantity = "2";
    String cartUpdatedMessage = "\n\t\tCart updated.\t";
    Page p;

    public CartPage(Page p) {
        this.p = p;
    }

    public void submitResult() {
        p.locator(QUANTITY).fill(quantity);
        p.locator(CARTUPDATE).click();
        assertThat(p.locator(CARTUPDATEDAPPROVE)).containsText(cartUpdatedMessage);
        assertThat(p.locator(CARTHASSUM)).containsText(expectedSumAmount);
        p.locator(PROCEEDTOCHECKOUT).click();
        assertThat(p).hasURL(checkoutPageUrl);
    }
}
