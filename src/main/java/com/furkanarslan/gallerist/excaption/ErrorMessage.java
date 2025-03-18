package com.furkanarslan.gallerist.excaption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private MessageType messageType;
    private String ofStatic;



     public String prepareErrorMessage() {
         StringBuilder builder = new StringBuilder();
         builder.append(messageType);
         if(this.ofStatic != null) {
             builder.append(" : "+ofStatic);
         }

return builder.toString();


     }






}
