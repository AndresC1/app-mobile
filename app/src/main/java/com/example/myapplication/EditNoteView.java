package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Repository.NoteDB;
import com.example.myapplication.ViewModel.DateModel;
import com.example.myapplication.ViewModel.NotesModel;

public class EditNoteView extends AppCompatActivity {
    private NotesModel noteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_note_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        if(intent != null){
            int noteId = intent.getIntExtra("id", 0);
            String noteTitle = intent.getStringExtra("title");
            String noteDescription = intent.getStringExtra("description");
            String noteCreated_at = intent.getStringExtra("created_at");
            String noteUpdated_at = intent.getStringExtra("updated_at");
            noteData = new NotesModel(
                    noteId,
                    noteTitle,
                    noteDescription,
                    noteCreated_at,
                    noteUpdated_at
            );

            TextView title = (TextView) findViewById(R.id.TxtUpdateTitleNote);
            title.setText(noteTitle);
            TextView description = (TextView) findViewById(R.id.TxtUpdateDescriptionNote);
            description.setText(noteDescription);
        }
        NoteDB noteDB = new NoteDB(getApplicationContext());

        findViewById(R.id.btnUpdateNote).setOnClickListener(v -> {
            try{
                DateModel date = new DateModel();
                String newNoteTitle = ((TextView) findViewById(R.id.TxtUpdateTitleNote)).getText().toString();
                String newNoteDescription = ((TextView) findViewById(R.id.TxtUpdateDescriptionNote)).getText().toString();
                noteData.setTitle(newNoteTitle);
                noteData.setDescription(newNoteDescription);
                noteData.setUpdated_at(date.getDateNow());
                noteDB.update(noteData);
                finish();
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}