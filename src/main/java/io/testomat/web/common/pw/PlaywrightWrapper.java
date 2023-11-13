package io.testomat.web.common.pw;

import com.microsoft.playwright.*;
import io.qameta.allure.Step;
import lombok.Data;

import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

public class PlaywrightWrapper {

    private static final ConcurrentHashMap<Long, PWContainer> pw = new ConcurrentHashMap<>();

    public static PWContainer pw() {
        return pw.computeIfAbsent(Thread.currentThread().getId(), k -> {
            var playwright = Playwright.create();
            var browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(Configuration.headless)
                            .setTimeout(Configuration.browserToStartTimeout)
                            .setDevtools(Configuration.devTools)
                            .setSlowMo(Configuration.poolingInterval)
                            .setTracesDir(Paths.get(Configuration.tracesPath))
            );
            return new PWContainer(null, null, playwright, browser);
        });
    }

    public static void close() {
        long threadId = Thread.currentThread().getId();

        if (pw.containsKey(threadId)) {
            pw.get(threadId).getPage().close();
            pw.get(threadId).getContext().close();
            pw.get(threadId).getBrowser().close();
            pw.get(threadId).getPlaywright().close();
            pw.remove(threadId);
        }

    }

    public static void initTestContext(String testName) {
        var newContextOptions = new Browser.NewContextOptions();
        newContextOptions.baseURL = Configuration.baseUrl;

        var pw = pw();
        var browserContext = pw.getBrowser().newContext(newContextOptions);
        if (Configuration.saveTraces) {
            browserContext.tracing().start(new Tracing.StartOptions()
                    .setTitle(testName)
                    .setName(testName + ".zip")
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true)
            );
        }
        var targetPage = browserContext.newPage();

        pw.setContext(browserContext);
        pw.setPage(targetPage);
    }

    public static void closeContext(String testName) {
        var pw = pw();

        var targetContext = pw.getContext();
        if (Configuration.saveTraces) {
            targetContext.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get(Configuration.tracesPath, testName + ".zip"))
            );
        }
        pw.getPage().close();
        targetContext.close();
    }

    public static void pausePage() {
        pw().getPage().pause();
    }

    @Step("Open {url}")
    public static void open(String url) {
        pw().getPage().navigate(url);
    }


    @Step("Find {selector}")
    public static LocatorActions $(String selector) {
        return find(selector);
    }

    @Step("Find {selector}")
    public static LocatorActions find(String selector) {
        return new LocatorActions(pw().getPage().locator(selector).first());
    }

    @Step("Find {selector} with text {text}")
    public static LocatorActions find(String selector, String text) {
        return new LocatorActions(pw().getPage().locator(selector).filter(
                new Locator.FilterOptions().setHasText(text)
        ));
    }


    @Data
    public static class PWContainer {

        private BrowserContext context;
        private Page page;
        private final Playwright playwright;
        private final Browser browser;

        public PWContainer(BrowserContext browserContext, Page page, Playwright playwright, Browser browser) {
            this.context = browserContext;
            this.page = page;
            this.playwright = playwright;
            this.browser = browser;
        }

    }

    public static void saveStorageState(String filePath) {
        BrowserContext context = pw().getContext();
        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(filePath)));
    }

    public static BrowserContext createNewContextWithStorageState(String filePath) {
        Browser browser = pw().getBrowser();
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions().setStorageStatePath(Paths.get(filePath)));
        return context;
    }

}
