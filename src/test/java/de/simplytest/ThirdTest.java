package de.simplytest;

import org.junit.jupiter.api.Test;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
class ThirdTest extends BaseTest {
    String name = "Klara";
    String nameLocator = "#name";
    String email = "KlaraKunde@dmx.de";
    String emailLocator = "#mail";
    String url = "https://autoprojekt.simplytest.de/sample-page/";
    String urlProduct = "https://autoprojekt.simplytest.de/produkt/beanie/";
    String submitButton = "[id=\"submit\"]";
    String nextInfo = "[id=\"alert1\"]";
    String anotherInfo = "[id=\"alert2\"]";
    String messageTitel = "#betreff";
    String messageForm = "#nachricht";
    String angebot = "#angebot";
    String frameworkNameDropDown = "[id=\"aktuelles_werkzeug\"]";
    String checkbox = "#dsgvo_zustimmung";
    String frameworkRadioB = "[id=\"naechstes_werkzeug\"] input[value=\"Something else...\"]";
    String frameworkName = "Something else...";
    String homeMenu = ".nav-menu li:nth-child(1)";

    @Test
    void thirdTest() {
        p.navigate(url);
        assertThat(p.locator(submitButton)).isDisabled();
        p.locator(nameLocator).fill(name);
        p.locator(emailLocator).fill(email);
        p.locator(messageTitel).fill(email);
        p.locator(messageForm).fill(email);
        p.locator(frameworkNameDropDown).selectOption(frameworkName);
        p.locator(frameworkRadioB).click();
        p.locator(checkbox).click();
        p.locator(nextInfo).click();
        assertThat(p.locator(submitButton)).isEnabled();
        p.onDialog(dialog -> {
            assertEquals("Further information is not to be found here either!", dialog.message());
            dialog.accept();
        });
        p.locator(anotherInfo).click();
        p.onDialog(dialog -> {
            assertEquals("Further information is not to be found here either!", dialog.message());
            dialog.accept();
        });
        p.locator(angebot).click();
        p.navigate(urlProduct);
        p.locator(homeMenu).click();
    }
}
