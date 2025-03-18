package com.furkanarslan.gallerist.handler;

import com.furkanarslan.gallerist.excaption.BaseExcaption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.*;

@ControllerAdvice
public class GlobalExcaptionHandler {
   @ExceptionHandler(value = {BaseExcaption.class})
   public ResponseEntity<ApiError<?>> handleBaseExcaption(BaseExcaption excaption, WebRequest request) {
     return ResponseEntity.badRequest().body(createApiError(excaption, request));
   }
 public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidExcaption(MethodArgumentNotValidException ex, WebRequest request) {
    Map<String,List<String> > map = new HashMap<>();

for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
   String fieldName= ((FieldError)objectError).getField();
   if (map.containsKey(fieldName)) {
  map.put(fieldName,addValue(map.get(fieldName),objectError.getDefaultMessage()));
   }
   else {
      map.put(fieldName,addValue(new ArrayList<>(),objectError.getDefaultMessage()));
   }
}
  return ResponseEntity.badRequest().body(createApiError(map, request));
 }

 private List<String> addValue(List<String> list, String newValue) {
      list.add(newValue);
      return list;
 }


   private  String getHostName() {
       try {
          return Inet4Address.getLocalHost().getHostName();
       } catch (UnknownHostException e) {
           throw new RuntimeException(e);
       }
   }

   public <E> ApiError<E> createApiError(E message,WebRequest request) {
      ApiError<E> apiError = new ApiError<>();
      apiError.setStatus(HttpStatus.BAD_REQUEST.value());

      Excaption<E> excaption = new Excaption<>();
      excaption.setMessage(message);
      excaption.setCreateTime(new Date());
      excaption.setPath(request.getDescription(false).substring(4));
      excaption.setHostname(getHostName());

      apiError.setExcaption(excaption);
      return apiError;
   }

}
