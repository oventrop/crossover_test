package pageobjects;

import com.sun.xml.internal.rngom.parse.host.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class ForCandidatesPage extends BasePage {

    public ForCandidatesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@data-content-field=\"title\" and text()=\"For Candidates\"]")
    private WebElement forCandidatesLogo;

    @FindBy(xpath = "(//*[text()=\"SEE AVAILABLE JOBS\"]) [2]")
    private WebElement seeAvailableJobs;

    public boolean isPageLoaded() {
        return isElementPresent(forCandidatesLogo);
    }

    public boolean isAvailableJobsBtnPresent() {
        return isElementPresent(seeAvailableJobs);
    }

    public AvailableJobsPage openAvailableJobs() {
        seeAvailableJobs.click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new AvailableJobsPage(driver);
    }
}
