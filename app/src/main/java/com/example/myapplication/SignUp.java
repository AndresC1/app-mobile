package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Repository.UserDB;
import com.example.myapplication.ViewModel.UserModel;
import com.example.myapplication.ViewModel.ValidateModel;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btnSignUp).setOnClickListener(v -> {
            try{
                String email = ((EditText) findViewById(R.id.txtEmailNewUser)).getText().toString();
                String password = ((EditText) findViewById(R.id.txtPasswordNewUser)).getText().toString();
                String confirmPassword = ((EditText) findViewById(R.id.txtConfirmPasswordNewUser)).getText().toString();
                ValidateModel validateModel = new ValidateModel();
                String[] fields = {email, password, confirmPassword};
                if(validateModel.validateFields(fields)){
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserModel newUser = new UserModel(0, email, password, confirmPassword);
                String infoValidation = newUser.validateInformation();
                if(infoValidation != null){
                    Toast.makeText(getApplicationContext(), infoValidation, Toast.LENGTH_SHORT).show();
                    return;
                }
                UserDB userDB = new UserDB(getApplicationContext());
                userDB.insert(newUser);
                Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                finish();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}