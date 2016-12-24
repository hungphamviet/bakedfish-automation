package com.bfa.exec;

import com.bfa.core.BrowserType;
import com.bfa.core.page.GooglePage;

/**
 * Created by ninhdoan on 12/24/16.
 */
public class Main {

   public static void main(String[] args) {
      GooglePage.openGoogle(BrowserType.FIREFOX)
            .search("Selenium 3 WebDriver")
            .openSearchResultAt(1)
            .close();
   }
}
