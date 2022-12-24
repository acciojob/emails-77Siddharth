package com.driver;

import java.util.Date;

public class Mail {
    Date date = null;
    String senderId = null;
    String message = null;

    Mail(Date date,String senderId,String message){
        this.date = date;
        this.senderId = senderId;
        this.message = message;
    }
}
