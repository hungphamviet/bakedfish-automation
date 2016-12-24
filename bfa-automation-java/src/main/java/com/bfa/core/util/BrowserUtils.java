package com.bfa.core.util;

import com.bfa.core.BrowserType;
import com.bfa.core.exception.BfaRuntimeException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by ninhdoan on 12/24/16.
 */
public class BrowserUtils {

   private static final String FIREFOX_WEB_DRIVER_KEY = "webdriver.gecko.driver";

   private static final String CHROME_WEB_DRIVER_KEY = "webdriver.chrome.driver";

   public static final WebDriver openBrowser(BrowserType browserType) {
      switch (browserType) {
         case FIREFOX:
            return openFirefox();
         case CHROME:
            return openChrome();
         case IE:
            return openIe();
         case SAFARI:
            return openSafari();
         default:
            throw new BfaRuntimeException("Not supported browser type: " + browserType);
      }
   }

   private static final WebDriver openFirefox() {
      String firefoxWebDriverValue = BfaConfig.getConfigValueAsString(FIREFOX_WEB_DRIVER_KEY);
      System.setProperty(FIREFOX_WEB_DRIVER_KEY, firefoxWebDriverValue);
      WebDriver webDriver = new FirefoxDriver();
      return webDriver;
   }

   private static final WebDriver openChrome() {
      String chromeWebDriverValue = BfaConfig.getConfigValueAsString(BrowserUtils.CHROME_WEB_DRIVER_KEY);
      System.setProperty(BrowserUtils.CHROME_WEB_DRIVER_KEY, chromeWebDriverValue);
      WebDriver webDriver = new ChromeDriver();
      return webDriver;
   }

   private static final WebDriver openIe() {
      throw new BfaRuntimeException("Not supported");
   }

   private static final WebDriver openSafari() {
      throw new BfaRuntimeException("Not supported");
   }

}
