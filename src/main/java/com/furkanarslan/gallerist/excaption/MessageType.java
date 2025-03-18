package com.furkanarslan.gallerist.excaption;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004","KAYIT BULUNAMADI "),
    TOKEN_IS_EXPIRED("1005","TOKEN SÜRESİ DOLMUŞTUR"),
    USERNAME_NOT_FOUND("1006","KULLANICI BULUNAMADI"),
    GENERAL_EXCAPTION("9999","GENEL BİR HATA OLUŞTU");
    private String code;
    private String message;

   MessageType(String code, String message) {
       this.code = code;
       this.message = message;
   }

}
