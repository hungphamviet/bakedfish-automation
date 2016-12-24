package com.bfa.core.page;

import com.bfa.core.BrowserType;
import com.bfa.core.exception.BfaRuntimeException;
import com.bfa.core.util.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bfa.core.util.BfaConfig.getConfigValueAsString;
import static com.bfa.core.util.LocatorUtils.getLocatorByKey;

/**
 * Created by ninhdoan on 12/24/16.
 */
public class GooglePage extends Page {

   private static final String HOME_URL_KEY = "google.url";

   private static final String SEARCH_INPUT_LOCATOR_KEY = "google.searchInputLocator";

   private static final String SEARCH_RESULT_LOCATOR_KEY = "google.searchResultLocator";

   private static final String RESULT_LINK_LOCATOR_KEY = "google.resultLinkLocator";

   private WebElement searchBox;

   private GooglePage(WebDriver webDriver) {
      super(webDriver);
   }

   public static GooglePage openGoogle(BrowserType browserType) {
      WebDriver webDriver = BrowserUtils.openBrowser(browserType);
      GooglePage googlePage = new GooglePage(webDriver);
      googlePage.navigateToGoogleUrl();
      return googlePage;
   }

   private void navigateToGoogleUrl() {
      webDriver.navigate().to(getConfigValueAsString(HOME_URL_KEY));
   }

   public GooglePage search(String keyword) {
      if (searchBox == null) {
         searchBox = waitToGetVisibleSearchBox();
      }
      searchBox.sendKeys(keyword);
      searchBox.submit();
      return this;
   }

   private WebElement waitToGetVisibleSearchBox() {
      return waitToGetVisibleWebElement(SEARCH_INPUT_LOCATOR_KEY);
   }

   public Page openSearchResultAt(int resultPositionAsOneBased) {
      waitForSearchResultToBeVisible();
      List<WebElement> resultLinks = webDriver.findElements(getLocatorByKey(RESULT_LINK_LOCATOR_KEY));
      if (resultPositionAsOneBased > resultLinks.size()) {
         throw new BfaRuntimeException("There is only " + resultLinks.size() + " results, can not open the link at position: " + resultPositionAsOneBased);
      }
      WebElement firstResult = resultLinks.get(resultPositionAsOneBased - 1);
      firstResult.click();
      return new Page(webDriver);
   }

   private void waitForSearchResultToBeVisible() {
      waitToGetVisibleWebElement(SEARCH_RESULT_LOCATOR_KEY);
   }
}
