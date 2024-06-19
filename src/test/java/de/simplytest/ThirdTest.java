package de.simplytest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ThirdTest {
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
    public void fourthDotOneTest() {
        Playwright pw = Playwright.create();
        Browser chrome = pw.chromium().launch();
        Page p = chrome.newPage();
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
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot2-1.png")).setFullPage(true));
        p.locator(anotherInfo).click();
        p.onDialog(dialog -> {
            assertEquals("Further information is not to be found here either!", dialog.message());
            dialog.accept();
        });
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot2-2.png")).setFullPage(true));
        p.locator(angebot).click();
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot2.png")).setFullPage(true));
        p.navigate(urlProduct);
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot2-3.png")).setFullPage(true));
        p.locator(homeMenu).click();
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot2-4.png")).setFullPage(true));
    }
}
