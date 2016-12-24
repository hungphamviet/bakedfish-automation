package com.bfa.core.util;

import com.bfa.core.exception.BfaRuntimeException;
import org.openqa.selenium.By;

import static com.bfa.core.util.BfaConfig.getConfigValueAsString;

/**
 * Created by ninhdoan on 12/27/16.
 */
public class LocatorUtils {

   public static By getLocatorByKey(String locatorConfigKey) {

      String locatorConfigValue = getConfigValueAsString(locatorConfigKey);

      String[] locatorTypeAndValue = locatorConfigValue.split(":");
      String locatorType = locatorTypeAndValue[0];
      String locatorValue = locatorTypeAndValue[1];

      switch (locatorType) {
         case "id":
            return By.id(locatorValue);
         case "name":
            return By.name(locatorValue);
         case "classname":
            return By.className(locatorValue);
         case "tagname":
         case "tag":
            return By.className(locatorValue);
         case "link":
         case "linktext":
            return By.linkText(locatorValue);
         case "partiallinktext":
            return By.partialLinkText(locatorValue);
         case "cssselector":
         case "css":
            return By.cssSelector(locatorValue);
         case "xpath":
            return By.xpath(locatorValue);
         default:
            throw new BfaRuntimeException("Unknown locator type: " + locatorType + " in locator: " + locatorConfigValue);
      }
   }
}
