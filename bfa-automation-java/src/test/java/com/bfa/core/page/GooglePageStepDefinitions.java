package com.bfa.core.page;

import com.bfa.core.BrowserType;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Condition;

import java.util.List;

import static com.bfa.core.page.GooglePage.openGoogle;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ninhdoan on 1/2/17.
 */
public class GooglePageStepDefinitions {

   private static final String TEST_BROWSER = "test.browser";

   private static final BrowserType DEFAULT_TEST_BROWSER_TYPE = BrowserType.FIREFOX;

   private GooglePage googlePage;

   @Given("^.*in Google Search page$")
   public void inGooglePage() {
      BrowserType testBrowserType = getTestBrowserType();
      googlePage = openGoogle(testBrowserType);
   }

   private BrowserType getTestBrowserType() {
      String testBrowser = System.getProperty(TEST_BROWSER);
      BrowserType testBrowserType = testBrowser == null ? DEFAULT_TEST_BROWSER_TYPE : BrowserType.valueOf(testBrowser.toUpperCase());
      return testBrowserType;
   }

   @When("^.*attempt to search for \"(.*)\"")
   public void searchForKeyword(String keyword) {
      googlePage.search(keyword);
   }

   @Then("^.*should see the site \"(.*)\" in the first result page$")
   public void shouldGetSiteOfKeywordInFirstResult(String expectedSiteInFirstResultPage) {
      List<String> resultLinksAtCurrentPage = googlePage.getResultLinksAtCurrentPage();
      Condition<String> linkContainsExpectedSite = new Condition<String>(resultLink -> resultLink.contains(expectedSiteInFirstResultPage), "Link should contain expected site: " + expectedSiteInFirstResultPage);
      assertThat(resultLinksAtCurrentPage).areAtLeastOne(linkContainsExpectedSite);
   }

   @After
   public void closeGooglePage() {
      googlePage.close();
   }
}
