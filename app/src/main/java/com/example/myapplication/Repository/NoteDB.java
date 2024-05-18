package com.example.myapplication.Repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.ViewModel.NotesModel;
import com.example.myapplication.ViewModel.UserModel;

import java.util.ArrayList;
import java.util.List;

public class NoteDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NoteDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_UPDATED_AT = "updated_at";
    public NoteDB(Context context) {
        super(context, "NoteDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, user_id TEXT, created_at TEXT, updated_at TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

    public void insert(NotesModel note){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO notes (title, description, user_id, created_at, updated_at) VALUES ('" + note.getTitle() + "', '" + note.getDescription() + "', '" + note.getUser().getId() + "', '" + note.getCreated_at() + "', '" + note.getUpdated_at() + "')");
    }

    public void update(NotesModel note){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE notes SET title = '" + note.getTitle() + "', description = '" + note.getDescription() + "', updated_at = '" + note.getUpdated_at() + "' WHERE id = " + note.getId());
    }

    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notes WHERE id = " + id);
    }

    public void deleteAll(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notes WHERE user_id = " + user.getId());
    }

    public NotesModel[] getAll(int userId){
        SQLiteDatabase db = this.getReadableDatabase();
        List<NotesModel> notes = new ArrayList<NotesModel>();
        Cursor cursor = db.rawQuery("SELECT * FROM notes WHERE user_id = '" + userId+"'", null);
        if(cursor.moveToFirst()){
            do{
                NotesModel note = new NotesModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5));
                notes.add(note);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes.toArray(new NotesModel[notes.size()]);
    }

    public NotesModel getNoteById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes WHERE id = " + id, null);
        if(cursor.moveToFirst()){
            NotesModel note = new NotesModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(5));
            cursor.close();
            db.close();
            return note;
        }
        return null;
    }
}
