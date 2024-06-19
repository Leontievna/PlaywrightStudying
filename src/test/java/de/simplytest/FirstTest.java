package de.simplytest;


import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FirstTest {//
    String url= "https://www.netflix.com/signup/planform/";

    @Test
    public void oneTestImplementation(){
        Playwright pw = Playwright.create();
        Browser chrome =pw.chromium().launch();
        Page p = chrome.newPage();
        p.navigate(url);
        assertThat(p).hasTitle("Netflix");
        p.locator("#onetrust-reject-all-handler").click();
        p.locator("//*[class=\"onetrust-reject-all-handler\"]").click();
        p.locator("[data-uia=\"cta-plan-selection\"]").click();
        p.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot2.png")));
        assertThat(p.locator(".stepIndicator")).containsText("1");
        p.locator("//select[@data-ui=\"language-picker\"]/[@lang=\"de\"]");
        p.locator("div:nth-child(1) > div > label > div").click();
    }

}
