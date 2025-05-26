package de.simplytest;

import org.junit.jupiter.api.Test;

class FourthTest extends BaseTest {
    String name = "Myname";
    String surname = "Mysurname";
    String location = "Germany";
    String city = "Bonn";
    String address = "Wilhelmstraße 27";
    String postcode = "53111";
    String phone = "033366542984";
    String email = "email@inbox.de";

    @Test
    void fourthOnlineShop() {
        CheckoutPage checkoutPage = new CheckoutPage(p);
        ShopPage shopPage = new ShopPage(p);
        CartPage cartPage = new CartPage(p);
        shopPage.openPage();
        shopPage.buyAlbum();
        shopPage.checkCart();
        cartPage.submitResult();
        checkoutPage.fillTheForms2(name, surname, location, address, postcode, city, phone, email);
    }
}
