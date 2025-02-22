package com.AccountTransaction.demo.model;

import java.time.LocalDateTime;

public class CustomException {
    private LocalDateTime timestamp;
    private String message;
    private String detail;
    private String errorCode;

    public CustomException(String message,String detail,String errorCode){
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.detail = detail;
        this.errorCode = errorCode;
    }

    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }

    public String getMessage(){
        return this.message;
    }
    public String getDetail(){
        return this.detail;
    }
    public String getErrorCode(){
        return this.errorCode;
    }

}
