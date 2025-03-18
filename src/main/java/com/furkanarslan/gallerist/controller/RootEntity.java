package com.furkanarslan.gallerist.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RootEntity<T> {
    public int status;
    public T payload;
   public String errorMessage;

   public static  <T> RootEntity<T> ok(T payload) {
   RootEntity<T> rootEntity = new RootEntity<>();
   rootEntity.setStatus(200);
   rootEntity.setPayload(payload);
   rootEntity.setErrorMessage(null);
   return rootEntity;
   }

    public static  <T> RootEntity<T> error(String errorMessage) {
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setErrorMessage(errorMessage);
        rootEntity.setStatus(500);
        rootEntity.setPayload(null);
        return rootEntity;
    }
}
