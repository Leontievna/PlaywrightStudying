package de.simplytest;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;


public class TestWatcherExtension implements TestWatcher {


    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (context.getTestInstance().isPresent()
                && context.getTestInstance().get() instanceof BaseTest) {

            BaseTest test = (BaseTest) context.getTestInstance().get();
            test.takeScreenshot(); // Скриншот до закрытия контекста
            test.closeResources(); // Закрываем контекст после сбора артефактов

            Allure.addAttachment("Stack Trace", "text/plain", getStackTrace(cause));
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        if (context.getTestInstance().isPresent()
                && context.getTestInstance().get() instanceof BaseTest) {

            BaseTest test = (BaseTest) context.getTestInstance().get();
            test.closeResources(); // Закрываем контекст для успешных тестов
        }
    }
    private String getStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}