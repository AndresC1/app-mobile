package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Repository.NoteDB;
import com.example.myapplication.ViewModel.NotesModel;

public class HomeView extends AppCompatActivity {

    NotesModel notes[] = {
        new NotesModel(1,"Note in DB", "This is a note example 1 in DB", "2021-09-01", ""),
        new NotesModel(2,"Note in BD 2", "This is a note example 2", "2021-09-01", ""),
        new NotesModel(3,"Frutas", "Manzana, Melon, Banano", "2021-09-01", ""),
        new NotesModel(4,"Colores", "Rojo, Blanco, Azul", "2021-09-01", ""),
        new NotesModel(5,"Componentes", "Mouse, Teclado, Monitor, CPU", "2021-09-01", "")
    };
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

        NoteDB noteDB = new NoteDB(getApplicationContext());

        listView = (ListView) findViewById(R.id.NotesListView);
        CustomBaseAdapetr customBaseAdapter = new CustomBaseAdapetr(getApplicationContext(), noteDB.getAll());
        listView.setAdapter(customBaseAdapter);
    }
}