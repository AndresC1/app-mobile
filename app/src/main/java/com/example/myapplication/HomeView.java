package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
    CustomBaseAdapetr customBaseAdapter;
    public MyApp app;

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

        app = (MyApp) getApplicationContext();
        this.validateLogin();
        Toast.makeText(this, "Welcome " + app.getUser().getEmail(), Toast.LENGTH_SHORT).show();

        NoteDB noteDB = new NoteDB(getApplicationContext());

        listView = (ListView) findViewById(R.id.NotesListView);
        customBaseAdapter = new CustomBaseAdapetr(getApplicationContext(), noteDB.getAll(app.getUser().getId()), this);
        listView.setAdapter(customBaseAdapter);
        registerForContextMenu(listView);

        findViewById(R.id.btnCreateNewNote).setOnClickListener(v -> {
            startActivity(new Intent(this, CreateNoteView.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.reloadListView();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Estás seguro de que quieres salir?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    app.setUser(null);
                    HomeView.super.onBackPressed();
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select an action");
        menu.add(0, v.getId(), 0, "Delete");
        menu.add(0, v.getId(), 0, "Edit");
    }

    @Override
    public boolean onContextItemSelected(android.view.MenuItem item) {
        try{
            if(item.getTitle() == "Delete" || item.getTitle() == "Edit") {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                String note_id = ((TextView) info.targetView.findViewById(R.id.txtIDNote)).getText().toString();
                NoteDB noteDB = new NoteDB(getApplicationContext());
                if (item.getTitle() == "Delete") {
                    noteDB.delete(Integer.parseInt(note_id));
                    this.reloadListView();
                    Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    NotesModel note = noteDB.getNoteById(Integer.parseInt(note_id));
                    customBaseAdapter.openEditNoteView(note);
                }
                return true;
            }
            return false;
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void reloadListView(){
        NoteDB noteDB = new NoteDB(getApplicationContext());
        NotesModel[] listData = noteDB.getAll(app.getUser().getId());
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