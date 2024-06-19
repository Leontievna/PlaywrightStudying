package de.simplytest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FourthTest {
    static Browser browser;
    static Playwright pw;
    Page p = browser.newPage();
    String url = "https://demoproject.simplytest.de/";
    String checkoutPageUrl = "https://autoprojekt.simplytest.de/checkout/";
    String name = "Myname";
    String surname = "Mysurname";
    String location = "Germany";
    String city = "Bonn";
    String address = "Wilhelmstraße 27";
    String postcode = "53111";
    String phone = "033366542984";
    String email = "email@inbox.de";
    CheckoutPage checkoutPage = new CheckoutPage(p);
    ShopPage shopPage = new ShopPage(p);
    CartPage cartPage = new CartPage(p);

    @BeforeAll
    public static void beforeAll() {
        Playwright pw = Playwright.create();
        //Browser browser = pw.chromium().launch();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
    }


    @Test
    public void fivesOnlineShop() {
        shopPage.openPage();
        shopPage.buyAlbum();
        shopPage.checkCart();
        cartPage.submitResult();
        checkoutPage.fillTheForms2(name, surname, location, address, postcode, city, phone, email);
    }
}
