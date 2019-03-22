package support.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private String browser;
    private ThreadLocal<WebDriver> webDriver;
    private static DriverFactory factoryInstance = null;

    private DriverFactory(String browser) {
        this.browser = browser;
        webDriver = localWebDriver;
    }

    public static DriverFactory getFactoryInstance(String browser) {
        if (factoryInstance == null) {
            factoryInstance = new DriverFactory(browser);
        }
        return factoryInstance;
    }

    private ThreadLocal<WebDriver> localWebDriver = ThreadLocal.withInitial(() -> new DriverSelector().getDriver(browser, getOs()));

    public WebDriver getDriver() {
        return webDriver.get();
    }

    public void tearDown() {
        if (webDriver != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }

    public void close() {
        webDriver.get().close();
    }

    private Platform getOs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return Platform.WINDOWS;
        }
        if (os.contains("mac")) {
            return Platform.MAC;
        }
        throw new RuntimeException("Unknown OS");
    }
}
