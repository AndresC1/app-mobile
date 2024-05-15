package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Repository.NoteDB;
import com.example.myapplication.ViewModel.NotesModel;

public class HomeView extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MyApp app = (MyApp) getApplicationContext();
        this.validateLogin();
        Toast.makeText(this, "Welcome " + app.getUser().getEmail(), Toast.LENGTH_SHORT).show();

        NoteDB noteDB = new NoteDB(getApplicationContext());

        listView = (ListView) findViewById(R.id.NotesListView);
        CustomBaseAdapetr customBaseAdapter = new CustomBaseAdapetr(getApplicationContext(), noteDB.getAll(), this);
        listView.setAdapter(customBaseAdapter);

        findViewById(R.id.btnCreateNewNote).setOnClickListener(v -> {
            startActivity(new Intent(this, CreateNoteView.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.reloadListView();
    }

    public void reloadListView(){
        NoteDB noteDB = new NoteDB(getApplicationContext());
        NotesModel[] listData = noteDB.getAll();
        CustomBaseAdapetr customBaseAdapter = new CustomBaseAdapetr(getApplicationContext(), listData, this);
        if(listData.length == 0){
            findViewById(R.id.txtEmptyList).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.txtEmptyList).setVisibility(View.GONE);
        }
        listView.setAdapter(customBaseAdapter);
    }

    public void validateLogin(){
        MyApp app = (MyApp) getApplicationContext();
        if(app.getUser() == null){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}