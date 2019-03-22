package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class=\"site-title\"]")
    private WebElement pageLogo;

    @FindBy(xpath = "(//*[@href = \"/for-candidates\"] )[2]")
    private WebElement forCandidatesButton;

    public boolean isPageLoaded() {
        return isElementPresent(pageLogo);
    }

    public ForCandidatesPage openForCandidatesPage() {
        forCandidatesButton.click();
        return new ForCandidatesPage(driver);
    }
}