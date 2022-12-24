package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    Deque<Mail> inbox = null;
    Deque<Mail> trash = null;
    Stack<Mail> temp = null;
    int inboxSize =0;
    //Inbox: Stores mails.  It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails.
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new LinkedList<Mail>() ;
        trash = new LinkedList<Mail>() ;
        temp = new Stack<Mail>();
    }

    public void receiveMail(Date date, String sender, String message){
        Mail newMail = new Mail(date,sender,message);
        if(inboxSize<=inboxCapacity){
            inbox.push(newMail);
            inboxSize++;
        }else{
            Mail curMail = inbox.pollLast();
            inboxSize--;
            trash.push(curMail);
            inbox.push(newMail);
            inboxSize++;
        }
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order.
        // This means that the date of a new mail is greater than equal to the dates of mails received already.

    }

    public void deleteMail(String message){
        if (inbox.isEmpty()!=true) {
            Mail cur = inbox.pollFirst();
            while (inbox.isEmpty() != true && !cur.message.contains(message)) {
                temp.push(cur);
                cur = inbox.pollFirst();
            }
            if (cur.message.contains(message)) {
                trash.push(cur);
                inboxSize--;
                while (temp.isEmpty() != true) {
                    Mail m = temp.pop();
                    inbox.push(m);
                }
            }
        }
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null

        // Else, return the message of the latest mail present in the inbox
        if (inbox.size()==0)
            return null;
        else {
            return inbox.getFirst().message;
        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if (inbox.isEmpty()==true)
            return null;
        else
            return inbox.getLast().message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count =0 ;
        Mail cur = inbox.pollFirst();
        while (inbox.isEmpty()!=true){
            if (cur.date.after(start) && cur.date.before(end) )
                count++;
            temp.push(cur);
            cur = inbox.pollFirst();
        }
        if (temp.isEmpty()){
            return count;
        }else{
            while (temp.isEmpty()!=true){
                cur = temp.pop();
                inbox.push(cur);
            }
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
        while (trash.isEmpty()!=true){
            trash.poll();
        }
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
