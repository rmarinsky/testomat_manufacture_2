private static final String STORAGE_STATE_FILE = "playwright/.auth/state.json";

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

    //After Successful LogIN
PlaywrightWrapper.saveStorageState(STORAGE_STATE_FILE);



        // Load the saved storage state before running the test
BrowserContext context = PlaywrightWrapper.createNewContextWithStorageState(STORAGE_STATE_FILE);

        // Set the new context with the loaded storage state in the PWContainer
PlaywrightWrapper.pw().setContext(context);
