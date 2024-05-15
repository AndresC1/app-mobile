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
}
