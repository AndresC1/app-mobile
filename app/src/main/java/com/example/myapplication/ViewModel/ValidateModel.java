package com.example.myapplication.ViewModel;

public class ValidateModel {
    public boolean validateFields(String[] fields){
        for(String field : fields){
            if(field.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public boolean validateEmail(String email){
        return email.contains("@") && email.contains(".");
    }

    public boolean validatePassword(String password){
        return password.length() >= 8;
    }

    public boolean validatePasswordMatch(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    public boolean validatePasswordDiferentEmail(String email, String password){
        return !email.equals(password);
    }

    public boolean validateCharacters(String password){
        return password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*].*");
    }
}
