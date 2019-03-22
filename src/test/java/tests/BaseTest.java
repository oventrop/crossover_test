package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.BasePage;
import support.driver.DriverFactory;

public class BaseTest {

    private DriverFactory driverFactory;
    WebDriver webDriver;

    @Parameters({ "browser" })
    @BeforeTest(alwaysRun = true)
    public void initDriver(String browser) {
        driverFactory = DriverFactory.getFactoryInstance(browser);
        webDriver = driverFactory.getDriver();
    }

    @AfterSuite
    public void tearDown() {
        driverFactory.tearDown();
    }

    @AfterTest
    public void closeBrowser() {
        driverFactory.close();
    }

    void getUrl(String url) {
        webDriver.get(url);
        webDriver.manage().window().maximize();
    }
}
