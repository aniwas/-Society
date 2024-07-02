package com.example.apartmentpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ComplaintDBHelper extends SQLiteOpenHelper {
    public ComplaintDBHelper(Context context) {
        super(context, "complaints.db", null, 1);
    }

    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE complaints(id INTEGER PRIMARY KEY,name TEXT,flat TEXT, complaint TEXT,time TEXT, status TEXT)");
        //myDB.execSQL("CREATE TABLE allowed(name TEXT,role TEXT, flat TEXT, date TEXT)");
    }

    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS complaints");
        //myDB.execSQL("DROP TABLE IF EXISTS allowed");
    }

    public boolean addComplaint(String name,String flat,String complaint, String time){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("flat",flat);
        cv.put("complaint",complaint);
        cv.put("time",time);
        cv.put("status","Received");
        long result = myDB.insert("complaints",null,cv);
        if(result == -1) return false;
        else return true;
    }

    public List<List<String>> getReceivedComplaints(){
        List<List<String>> receivedComplaints = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM complaints WHERE status = 'Received'", null);

        if (cursor.moveToFirst()) {
            do {
                List<String> complaintData = new ArrayList<>();
                complaintData.add(cursor.getString(0));
                complaintData.add(cursor.getString(1));
                complaintData.add(cursor.getString(2));
                complaintData.add(cursor.getString(3));
                complaintData.add(cursor.getString(4));

                receivedComplaints.add(complaintData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return receivedComplaints;
    }

    public boolean resolve(String id){
        SQLiteDatabase myDB = this.getReadableDatabase();
        try {
            myDB.execSQL("UPDATE complaints SET status = 'resolved' where id = ?", new String[]{id});
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
