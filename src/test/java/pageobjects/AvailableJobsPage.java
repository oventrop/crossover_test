package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AvailableJobsPage extends BasePage {

    public AvailableJobsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()=\"SEARCH JOBS\"]")
    private WebElement submitBtn;


    @FindBy(xpath = "(//*[@type=\"text\"])[1]")
    private WebElement searchField;

    @FindBy(xpath = "//*[text() =\"RESET\"]")
    private WebElement resetBtn;


    public boolean isPageLoaded() {
        return isElementPresent(submitBtn);
    }

    public boolean isSubmitBtnDisabled() {
        return submitBtn.getAttribute("disabled").equalsIgnoreCase("true");
    }

    public void submitSearch() {
        submitBtn.click();
    }

    public void fillSearch(String s) {
        searchField.click();
        searchField.sendKeys(s);
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(By.xpath("//*[contains(@id, \"job\")]"));
    }

    public List<WebElement> getSearchResultsWithExpectedTest(String expectedText) {
        List<WebElement> allSearchResults = getSearchResults();
        List<WebElement> searchResultsWithText = new ArrayList<WebElement>();
        for (WebElement element : allSearchResults) {
            if (element.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                searchResultsWithText.add(element);
            }
        }
        return searchResultsWithText;
    }

    public boolean isSearchResultsContainExpectedText(String expectedText) {
        List<WebElement> searchResults = getSearchResults();
        int flag = 0;
        for (WebElement element : searchResults) {
            if (!element.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                flag++;
            }
        }
        return flag == 0;
    }

    public void clearSearchResults() {
        resetBtn.click();
    }
}
