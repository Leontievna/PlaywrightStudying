package de.simplytest;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPage extends BaseClass {
    String checkoutPageUrl = "https://autoprojekt.simplytest.de/checkout/";
    String CARTHASSUM = "#site-header-cart a span.amount";
    String CARTUPDATE = "button[name=\"update_cart\"]";
    String CARTUPDATEDAPPROVE = ".woocommerce-message[role=\"alert\"]";
    String QUANTITY = "[aria-label=\"Product quantity\"]";
    String PROCEEDTOCHECKOUT = ".wc-proceed-to-checkout";
    String expectedSumAmount = "30,00";
    String quantity = "2";
    String cartUpdatedMessage = "\n\t\tCart updated.\t";

    public CartPage(Page p) {
        this.p = p;
    }
    @Step("Submit result")
    public void submitResult() {
        p.locator(QUANTITY).click();
        p.keyboard().press("ArrowUp");
        p.locator(CARTUPDATE).click();
        assertThat(p.locator(CARTUPDATEDAPPROVE)).containsText(cartUpdatedMessage);
        assertThat(p.locator(CARTHASSUM)).containsText(expectedSumAmount);
        p.locator(PROCEEDTOCHECKOUT).click();
        assertThat(p).hasURL(checkoutPageUrl);
    }
}
