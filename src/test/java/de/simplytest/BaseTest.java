package de.simplytest;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.ByteArrayInputStream;

@ExtendWith(de.simplytest.TestWatcherExtension.class)
public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page p;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        p = context.newPage();
    }

    public void takeScreenshot() {
        try {
            if (p == null || p.isClosed()) {
                throw new IllegalStateException("Page is not initialized or already closed");
            }
            byte[] screenshot = p.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Error screenshot", "text/plain", "Failed: " + e.getMessage());
        }
    }

    public void closeResources() {
        if (context != null) {
            context.close();
        }
    }
}
