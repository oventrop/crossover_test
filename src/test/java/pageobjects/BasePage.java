package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class BasePage {

    WebDriver driver;
    Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //set implicit wait
        driver.manage().timeouts().implicitlyWait(20, SECONDS);
        actions = new Actions(driver);
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean isPageLoaded ();

    public void waitForElement(final WebElement webElement, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        ExpectedCondition elementIsPresent = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    return  webElement != null;
                } catch (NoSuchElementException e) {
                    return false;
                } catch (StaleElementReferenceException f) {
                    return false;
                }
            }
        };
        wait.until(elementIsPresent);
    }

    public boolean isElementPresent(WebElement element) {
        return isElementPresent(element, 20);
    }

    private boolean isElementPresent(WebElement element, int timeout) {
        waitForElement(element, timeout);
        return true;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void scrollPageToTheEnd(int timesToScroll) {
        for (int i = 0; i < timesToScroll; i++) {
            actions.sendKeys(Keys.PAGE_DOWN).build().perform();
        }
    }
}
