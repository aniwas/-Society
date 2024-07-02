package com.example.apartmentpal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.SQLException;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE users(flatno TEXT PRIMARY KEY,username TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS users");
    }

    public boolean insertUser(String flat, String user, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("flatno", flat);
        values.put("username", user);
        values.put("password", pass);

        try {
            long result = db.insert("users", null, values);
            if (result == -1) {
                return false; // error occurred
            } else {
                return true; // insertion successful
            }
        } catch (Exception e) {
            Log.e("SQLite Error", "Error inserting user: " + e.getMessage());
            return false;
        } finally {
            db.close(); // always close the database connection
        }
    }
    public boolean checkFlat(String flat){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM users where flatNo = ?",new String[] {flat});
        if(cursor.getCount() > 0) return true;
        return false;
    }

    public boolean checkFlatPassword(String flat,String pass){
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM users where flatNo = ? AND password = ?",new String[] {flat,pass});
        if(cursor.getCount() > 0) return true;
        return false;
    }

    public String getUser(String flatNo) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        String userData = null;

        try {
            Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE flatNo = ?", new String[]{flatNo});

            if (cursor != null && cursor.moveToFirst()) {
                // Fetch user details from the cursor
                userData = cursor.getString(cursor.getColumnIndex("username"));
            }

            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            // Log the error for debugging
            Log.e("SQLite Error", "Error fetching user data: " + e.getMessage());
        }
        return userData;
    }

}
