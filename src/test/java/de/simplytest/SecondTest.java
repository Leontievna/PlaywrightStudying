package de.simplytest;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class SecondTest extends BaseTest {
    String url = "https://www.wikipedia.com";
    String input = "//*[@id=\"searchInput\"]";
    String searchButton = "button.pure-button-primary-progressive";
    String text="Hello World!";

    @Test
    @Description("Second test 'Wikipedia'")
    void secondTest() {
        p.navigate(url);
        assertThat(p).hasTitle("Wikipedia");
        assertThat(p.locator(input)).isVisible();
        p.locator(input).fill(text);
        p.locator(searchButton).click();
        assertThat(p.locator("#firstHeading .mw-page-title-main")).containsText("Hello, World!");
    }
}
