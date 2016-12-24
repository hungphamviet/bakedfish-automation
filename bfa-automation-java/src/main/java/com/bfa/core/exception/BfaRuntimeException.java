package com.bfa.core.exception;

/**
 * Created by ninhdoan on 12/24/16.
 */
public class BfaRuntimeException extends RuntimeException {

   public BfaRuntimeException() {
   }

   public BfaRuntimeException(String message) {
      super(message);
   }

   public BfaRuntimeException(String message, Throwable cause) {
      super(message, cause);
   }

   public BfaRuntimeException(Throwable cause) {
      super(cause);
   }

   public BfaRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
