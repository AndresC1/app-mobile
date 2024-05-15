package com.example.myapplication.Repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.ViewModel.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public UserDB(Context context) {
        super(context, "UserDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public void insert(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO users (email, password) VALUES ('" + user.getEmail() + "', '" + user.getPassword() + "')");
    }

    public void update(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE users SET email = '" + user.getEmail() + "', password = '" + user.getPassword() + "' WHERE id = " + user.getId());
    }

    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM users WHERE id = " + id);
    }

    public UserModel[] deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<UserModel> users = new ArrayList<UserModel>();
        Cursor cursor = db.rawQuery("SELECT * FROM users", null);
        if (cursor.moveToFirst()) {
            do {
                users.add(new UserModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return users.toArray(new UserModel[users.size()]);
    }
}
