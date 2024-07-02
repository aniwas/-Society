package com.example.apartmentpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementHelper extends SQLiteOpenHelper {
    public AnnouncementHelper(Context context) {
        super(context, "announcements.db", null, 1);
    }

    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE announcements(id INTEGER PRIMARY KEY,announcement TEXT)");
        //myDB.execSQL("CREATE TABLE allowed(name TEXT,role TEXT, flat TEXT, date TEXT)");
    }

    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS announcements");
        //myDB.execSQL("DROP TABLE IF EXISTS allowed");
    }

    public boolean addAnnouncement(String message){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("announcement",message);
        long result = myDB.insert("announcements",null,cv);
        if(result == -1) return false;
        else return true;
    }

    public boolean clearAnnouncements(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        try {
            myDB.execSQL("DELETE FROM announcements");
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public List<String> getAllAnnouncements() {
        List<String> announcementsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM announcements", null);

        if (cursor.moveToFirst()) {
            do {
                String announcement = cursor.getString(1);
                announcementsList.add(announcement);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return announcementsList;
    }
}
