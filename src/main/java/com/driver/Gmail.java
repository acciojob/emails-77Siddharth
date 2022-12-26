package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    List<Mail> inbox = null;
    List<Mail> trash = null;

    //Inbox: Stores mails.  It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails.
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new ArrayList<>() ;
        trash = new ArrayList<>() ;

    }

    public void receiveMail(Date date, String sender, String message){
        Mail newMail = new Mail(date,sender,message);
        if(inbox.size()<inboxCapacity){
            inbox.add(newMail);
        }else{
            Mail curMail = inbox.get(0);
            inbox.remove(0);
            trash.add(curMail);
            inbox.add(newMail);
        }
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order.
        // This means that the date of a new mail is greater than equal to the dates of mails received already.

    }

    public void deleteMail(String message){

        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for (int i=0;i<inbox.size();i++){
            Mail curMail = inbox.get(i);
            if (curMail.message.contains(message)){
                inbox.remove(i);
                trash.add(curMail);
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null

        // Else, return the message of the latest mail present in the inbox
        if (inbox.size()==0)
            return null;
        else {
            return inbox.get(inbox.size()-1).message;
        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if (inbox.isEmpty())
            return null;
        else
            return inbox.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count =0;
        for(int i=inbox.size()-1;i>=0;i--){
            Mail curMail = inbox.get(i);
            if (curMail.date.before(end) && curMail.date.after(start)){
                count++;
            }else if (curMail.date.equals(start) || curMail.date.equals(end))
                count++;
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        while (!trash.isEmpty()){
            trash.remove(0);
        }
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
