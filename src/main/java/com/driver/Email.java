package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public boolean checkUpperCase(String s){
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    public boolean checkLowerCase(String s){
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isLowerCase(c))
                return true;
        }
        return false;
    }

    public boolean checkDigit(String s){
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(c))
                return true;
        }
        return false;
    }

    public boolean checkSpecial(String s){
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)
                    && !Character.isLetter(c)
                    && !Character.isWhitespace(c)) {
                return true;
            }
        }
        return false;
    }

    public void changePassword(String oldPassword, String newPassword){
        if(oldPassword == getPassword()
                && newPassword.length()>=8
                && checkLowerCase(newPassword)
                && checkDigit(newPassword)
                && checkUpperCase(newPassword)
                && checkSpecial(newPassword)){

            this.password = newPassword;
        }
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
    }
}
