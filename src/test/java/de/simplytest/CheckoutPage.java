package de.simplytest;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutPage {
    Page p;
    String NAMEINPUT = "#billing_first_name_field input";
    String LASTNAMEINPUT = "#billing_last_name";
    String LOCATION = "#select2-billing_country-container";
    String LOCATIONINPUT = "[class=\"select2-search__field\"]";
    String LOCATIONDD = "#select2-billing_country-results li";
    String CITY = "#billing_city";
    String STREET = "#billing_address_1";
    String ZIP = "#billing_postcode";
    String PHONE = "#billing_phone";
    String EMAIL = "#billing_email";
    String PLACEORDER = "[data-value=\"Place order\"]";
    String ORDERPAGE = "#post-7 h1";
    String checkoutPageUrl = "https://autoprojekt.simplytest.de/checkout/";
    String orderPageName = "Order received";

    public CheckoutPage(Page p) {
        this.p = p;
    }

    public CheckoutPage fillTheForms2(String name, String surname, String location, String address, String postcode, String city, String phone, String email) {
        assertThat(p).hasURL(checkoutPageUrl);
        p.locator(NAMEINPUT).fill(name);
        p.locator(LASTNAMEINPUT).fill(surname);
        p.locator(LOCATION).click();
        p.locator(LOCATIONINPUT).fill(location);
        p.locator(LOCATIONDD).click();
        p.locator(CITY).fill(city);
        p.locator(STREET).fill(address);
        p.locator(ZIP).fill(postcode);
        p.locator(PHONE).fill(phone);
        p.locator(EMAIL).fill(email);
        p.locator(PLACEORDER).click();
        assertThat(p.locator(ORDERPAGE)).containsText(orderPageName);
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot5.png")).setFullPage(true));
        return this;
    }
}
