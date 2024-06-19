package de.simplytest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SecondTest {
    String url = "https://www.wikipedia.com";
    String input = "//*[@id=\"searchInput\"]";
    String searchButton = "button.pure-button-primary-progressive";
    String text="Hello World!";

    @Test
    public void twoTest() {
        Playwright pw = Playwright.create();
        Browser chrome = pw.chromium().launch();
        Page p = chrome.newPage();
        p.navigate(url);
        assertThat(p).hasTitle("Wikipedia");
        assertThat(p.locator(input)).isVisible();
        p.locator(input).fill(text);
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot1-1.png")));
        p.locator(searchButton).click();
        assertThat(p.locator("#firstHeading .mw-page-title-main")).containsText("Hello, World!");
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot1-2.png")));

    }
}
