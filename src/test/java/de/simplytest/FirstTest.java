package de.simplytest;


import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class FirstTest extends BaseTest {
    String url= "https://www.netflix.com/signup/planform/";

    @Test
        void firstTestImplementation(){
        p.navigate(url);
        assertThat(p).hasTitle("Netflix");
        p.locator("#onetrust-reject-all-handler").click();
        p.locator("[data-uia=\"continue-button\"]").click();
        p.locator("[data-uia=\"cta-plan-selection\"]").click();
        assertThat(p.locator(".stepIndicator").first()).containsText("2");
        p.keyboard().press("PageDown");
        p.getByLabel("Select Language").selectOption("Deutsch");
        assertThat(p.locator("[data-uia=\"stepTitle\"]")).containsText("Einrichtung Ihres Kontos abschließen");
        p.locator("[data-uia=\"cta-continue-registration\"]").click();
    }
}
