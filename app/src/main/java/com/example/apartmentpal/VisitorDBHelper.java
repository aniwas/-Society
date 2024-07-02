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
import java.util.*;
import androidx.annotation.Nullable;

import java.sql.SQLException;

public class VisitorDBHelper extends SQLiteOpenHelper {


    // Define table creation query
    public VisitorDBHelper(Context context) {
        super(context, "visitor.db", null, 1);
    }
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE visitors(id INTEGER PRIMARY KEY,name TEXT,role TEXT, flat TEXT, status TEXT)");
        myDB.execSQL("CREATE TABLE allowed(name TEXT,role TEXT, flat TEXT, date TEXT)");
    }

    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS visitors");
        myDB.execSQL("DROP TABLE IF EXISTS allowed");
    }

    public boolean insertVisitor(String name,String role,String flatNo){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("status","pending");
        cv.put("flat",flatNo);
        cv.put("role",role);
        long result = myDB.insert("visitors",null,cv);
        if(result == -1) return false;
        else return true;
    }

    public boolean insertIntoAllowed(String name,String role,String flat,String date){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("role",role);
        cv.put("flat",flat);
        cv.put("date",date);
        long result = myDB.insert("allowed",null,cv);
        if(result == -1) return false;
        else return true;
    }

    public boolean allow(int id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        try {
            myDB.execSQL("UPDATE visitors SET status = 'allowed' where id = ?", new String[]{String.valueOf(id)});
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean restrict(int id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        try {
            myDB.execSQL("UPDATE visitors SET status = 'denied' where id = ?", new String[]{String.valueOf(id)});
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public List<List<String>> getAllowedVisitors() {
        List<List<String>> allowedVisitors = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("allowed", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String role = cursor.getString(1);
                String flat = cursor.getString(2);
                String date = cursor.getString(3);

                List<String> visitorDetails = new ArrayList<>();
                visitorDetails.add(name);
                visitorDetails.add(role);
                visitorDetails.add(flat);
                visitorDetails.add(date);

                allowedVisitors.add(visitorDetails);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return allowedVisitors;
    }


    public List<Visitor> getPendingVisitors(String flat) {
        List<Visitor> pendingVisitors = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Visitors WHERE status = ? AND flat = ?";
        String[] selectionArgs = new String[]{"pending", flat};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        while (cursor.moveToNext()) {
            //int id = cursor.getInt(0); // Assuming 'id' is the first column index
            String name = cursor.getString(1); // Assuming 'name' is the second column index
            String id = cursor.getString(0);
            String role = cursor.getString(2); // Assuming 'role' is the fifth column index

            Visitor visitor = new Visitor(id, name, role);
            //visitor.setId(id); // Optionally set the id for the visitor object

            pendingVisitors.add(visitor);
        }

        cursor.close();

        return pendingVisitors;
    }

    public boolean clearVisitorLog(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        try {
            myDB.execSQL("DELETE FROM allowed");
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


}
