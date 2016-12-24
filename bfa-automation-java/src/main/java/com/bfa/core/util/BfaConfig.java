package com.bfa.core.util;

import com.bfa.core.exception.BfaRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ninhdoan on 12/24/16.
 */
public class BfaConfig {

   private static final String BFA_CONFIG_FILE = "bfa-config.properties";

   private static Properties bfaConfigProperties = new Properties();

   static {
      try (InputStream inputStream = BfaConfig.class.getClassLoader().getResourceAsStream(BFA_CONFIG_FILE)) {
         bfaConfigProperties.load(inputStream);
      } catch (IOException e) {
         throw new BfaRuntimeException(e);
      }
   }

   public static final String getConfigValueAsString(String configKey) {
      String configValueInSystemVariable = System.getProperty(configKey);
      return configValueInSystemVariable != null ? configValueInSystemVariable : bfaConfigProperties.getProperty(configKey);
   }

   public static final Integer getConfigValueAsInteger(String configKey) {
      String configValue = getConfigValueAsString(configKey);
      return configKey!=null ? Integer.parseInt(configValue):null;
   }
}