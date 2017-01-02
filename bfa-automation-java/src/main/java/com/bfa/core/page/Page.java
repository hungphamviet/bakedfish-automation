package com.bfa.core.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.bfa.core.util.BfaConfig.getConfigValueAsInteger;
import static com.bfa.core.util.LocatorUtils.getLocatorByKey;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by ninhdoan on 12/24/16.
 */
public class Page {

   public static final String DEFAULT_WAIT_TIME_IN_SECONDS_KEY = "webdriver.defaultWaitTimeInSeconds";

   public static final String ATTRIBUTE_HREF = "href";

   protected final WebDriver webDriver;

   public Page(WebDriver webDriver) {
      this.webDriver = webDriver;
   }

   public WebElement waitToGetVisibleWebElement(String webElementLocatorKey, long waitTimeInSeconds) {
      WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, waitTimeInSeconds);
      WebElement visibleWebElement = webDriverWait.until(visibilityOfElementLocated(getLocatorByKey(webElementLocatorKey)));
      return visibleWebElement;
   }

   public WebElement waitToGetVisibleWebElement(String webElementLocatorKey) {
      return waitToGetVisibleWebElement(webElementLocatorKey, getConfigValueAsInteger(DEFAULT_WAIT_TIME_IN_SECONDS_KEY));
   }

   public void close() {
      webDriver.close();
      webDriver.quit();
   }
}
