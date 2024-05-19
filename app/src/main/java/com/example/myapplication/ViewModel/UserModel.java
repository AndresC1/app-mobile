package com.example.myapplication.ViewModel;

public class UserModel {
    private int id;
    private String email;
    private String password;
    private String confirmPassword;

    public UserModel(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserModel(int id, String email, String password, String confirmPassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String validateInformation(){
        ValidateModel validateModel = new ValidateModel();
        if(!validateModel.validateEmail(email)){
            return "Invalid email";
        }
        if(!validateModel.validatePassword(password)){
            return "Password must be at least 8 characters";
        }
        if (!validateModel.validatePasswordDiferentEmail(email, password)){
            return "Password must be different from email";
        }
        if (!validateModel.validatePasswordMatch(password, confirmPassword)){
            return "Passwords do not match";
        }
        if (!validateModel.validateCharacters(password)){
            return "Password must contain at least one letter, one number and one special character";
        }
        return null;
    }

    public String validateChangePassword(String newPassword, String confirmNewPassword){
        ValidateModel validateModel = new ValidateModel();
        if(!validateModel.validatePassword(newPassword)){
            return "Password must be at least 8 characters";
        }
        if (!validateModel.validatePasswordMatch(newPassword, confirmNewPassword)){
            return "Passwords do not match";
        }
        if (!validateModel.validateCharacters(newPassword)){
            return "Password must contain at least one letter, one number and one special character";
        }
        if(validateModel.validatePasswordMatch(password, newPassword)){
            return "New password must be different from the current password";
        }
        return null;
    }
}
