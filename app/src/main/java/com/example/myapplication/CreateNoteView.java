package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Repository.NoteDB;
import com.example.myapplication.ViewModel.DateModel;
import com.example.myapplication.ViewModel.NotesModel;
import com.example.myapplication.ViewModel.ValidateModel;
import android.widget.Toast;

public class CreateNoteView extends AppCompatActivity {
    public MyApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_note_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NoteDB noteDB = new NoteDB(getApplicationContext());
        app = (MyApp) getApplicationContext();

        findViewById(R.id.btnSaveNewNote).setOnClickListener(v -> {
            try{
                String newNoteTitle = ((EditText) findViewById(R.id.TxtNewNoteTitle)).getText().toString();
                String newNoteDescription = ((EditText) findViewById(R.id.TxtUpdateDescriptionNote)).getText().toString();
                ValidateModel validateModel = new ValidateModel();
                if(validateModel.validateFields(new String[]{newNoteTitle, newNoteDescription})){
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                DateModel date = new DateModel();
                String created_at = date.getDateNow();
                NotesModel newNote = new NotesModel(0, newNoteTitle, newNoteDescription, created_at, "", app.getUser());
                noteDB.insert(newNote);
                finish();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}