package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.AvailableJobsPage;
import pageobjects.ForCandidatesPage;
import pageobjects.MainPage;
import support.logger.Logger;

public class SampleTest extends BaseTest {

    private static final String BASE_URL = "https://app.crossover.com/x/home";
    private static final String EXPECTED_SEARCH_QUERY = "Chief";

    @Test
    public void test() {
        Logger.logConsoleMessage("Open URL:");
        getUrl(BASE_URL);

        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.isPageLoaded(), "Main page didn't open.");

        Logger.logConsoleMessage("Navigate to 'FOR CANDIDATES' page:");
        ForCandidatesPage forCandidatesPage = mainPage.openForCandidatesPage();
        Assert.assertTrue(forCandidatesPage.isPageLoaded(), "ForCandidates page didn't open.");
        Assert.assertTrue(forCandidatesPage.getPageTitle().equalsIgnoreCase("For Candidates — Crossover"), "ForCandidates page title is incorrect.");

        Logger.logConsoleMessage("Navigate to 'Available Jobs' page:");
        forCandidatesPage.scrollPageToTheEnd(15);
        Assert.assertTrue(forCandidatesPage.isAvailableJobsBtnPresent(), "Available Jobs button didn't appear");
        AvailableJobsPage availableJobsPage = forCandidatesPage.openAvailableJobs();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(availableJobsPage.isPageLoaded(), "AvailableJobs page didn't open.");
        softAssert.assertTrue(availableJobsPage.isSubmitBtnDisabled(), "Submit btn enabled.");
        softAssert.assertAll();

        Logger.logConsoleMessage("Fill search field:");
        availableJobsPage.fillSearch(EXPECTED_SEARCH_QUERY);
        availableJobsPage.submitSearch();

        Logger.logConsoleMessage("Assert that only expected results are present:");
        softAssert.assertTrue(availableJobsPage.isSearchResultsContainExpectedText(EXPECTED_SEARCH_QUERY), "Search results w/o expected text found.");

        Logger.logConsoleMessage("Clear search query:");
        availableJobsPage.clearSearchResults();

        Logger.logConsoleMessage("Verify that all search queries are available:");
        int expectedTextQuantity = availableJobsPage.getSearchResultsWithExpectedTest(EXPECTED_SEARCH_QUERY).size();
        int allResultsQuantity = availableJobsPage.getSearchResults().size();
        softAssert.assertTrue(expectedTextQuantity < allResultsQuantity, "Search results wasn't cleared.");
        softAssert.assertAll();
    }
}
