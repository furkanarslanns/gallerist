package com.furkanarslan.gallerist.excaption;

public class BaseExcaption extends RuntimeException {

   public BaseExcaption(ErrorMessage errorMessage) {
        super(errorMessage.prepareErrorMessage());
    }

}
