package com.example.myapplication;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Repository.UserDB;
import com.example.myapplication.ViewModel.UserModel;
import com.example.myapplication.ViewModel.ValidateModel;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView txtErrorLoginUser = findViewById(R.id.txtErrorLoginUser);
        txtErrorLoginUser.setVisibility(View.GONE);
        MyApp app = (MyApp) getApplicationContext();
        if (app.getUser() != null) {
            startActivity(new Intent(this, HomeView.class));
        }

        findViewById(R.id.btnSignUp).setOnClickListener(v -> {
            startActivity(new Intent(this, SignUp.class));
        });

        findViewById(R.id.btnSignIn).setOnClickListener(v -> {
            String email = ((EditText) findViewById(R.id.txtEmailLoginUser)).getText().toString();
            String password = ((EditText) findViewById(R.id.txtPasswordLoginUser)).getText().toString();
            UserDB userDB = new UserDB(this);
            ValidateModel validateModel = new ValidateModel();
            if (validateModel.validateFields(new String[]{email, password})) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (userDB.checkUser(email, password)) {
                txtErrorLoginUser.setVisibility(View.GONE);
                app.setUser(userDB.getUser(email, password));
                ((EditText) findViewById(R.id.txtEmailLoginUser)).setText("");
                ((EditText) findViewById(R.id.txtPasswordLoginUser)).setText("");
                startActivity(new Intent(this, HomeView.class));
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                txtErrorLoginUser.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (((MyApp) getApplicationContext()).getUser() != null) {
            startActivity(new Intent(this, HomeView.class));
        }
    }
}