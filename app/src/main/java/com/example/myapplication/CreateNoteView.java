package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Repository.NoteDB;
import com.example.myapplication.ViewModel.NotesModel;
import com.example.myapplication.ViewModel.ValidateModel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNoteView extends AppCompatActivity {

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

        findViewById(R.id.btnSaveNewNote).setOnClickListener(v -> {
            try{
                String newNoteTitle = ((EditText) findViewById(R.id.TxtNewNoteTitle)).getText().toString();
                String newNoteDescription = ((EditText) findViewById(R.id.TxtUpdateDescriptionNote)).getText().toString();
                ValidateModel validateModel = new ValidateModel();
                if(validateModel.validateFields(new String[]{newNoteTitle, newNoteDescription})){
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String created_at = formatter.format(date);
                NotesModel newNote = new NotesModel(0, newNoteTitle, newNoteDescription, created_at, "");
                noteDB.insert(newNote);
                finish();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}