package com.example.apartmentpal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AmenityHelper extends SQLiteOpenHelper {

    public AmenityHelper(Context context) {
        super(context, "amenities.db", null, 1);
    }

    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE amenities(name TEXT PRIMARY KEY,slot9AMto12PM TEXT, slot12PMto3PM TEXT, slot3PMto6PM TEXT)");
        //myDB.execSQL("CREATE TABLE allowed(name TEXT,role TEXT, flat TEXT, date TEXT)");
    }

    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("DROP TABLE IF EXISTS amenities");
        //myDB.execSQL("DROP TABLE IF EXISTS allowed");
    }
    public void createAmenities(){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name","Gym");
        cv.put("slot9AMto12PM","free");
        cv.put("slot12PMto3PM","free");
        cv.put("slot3PMto6PM","free");
        myDB.insert("amenities",null,cv);

        cv.put("name","Swimming Pool");
        cv.put("slot9AMto12PM","free");
        cv.put("slot12PMto3PM","free");
        cv.put("slot3PMto6PM","free");
        myDB.insert("amenities",null,cv);

        cv.put("name","Ground");
        cv.put("slot9AMto12PM","free");
        cv.put("slot12PMto3PM","free");
        cv.put("slot3PMto6PM","free");
        myDB.insert("amenities",null,cv);

        cv.put("name","Party Hall");
        cv.put("slot9AMto12PM","free");
        cv.put("slot12PMto3PM","free");
        cv.put("slot3PMto6PM","free");
        myDB.insert("amenities",null,cv);

    }

    public List<String> getAvailableTimeSlots(String amenity){
        List<String> timeSlots = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM amenities WHERE name = '"+amenity+"'", null);

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(1).equals("free")) timeSlots.add("9AM TO 12PM");
                if(cursor.getString(2).equals("free")) timeSlots.add("12PM TO 3PM");
                if(cursor.getString(3).equals("free")) timeSlots.add("3PM TO 6PM");
            } while (cursor.moveToNext());
        }

        cursor.close();
        return timeSlots;
    }

    public List<String> getBookedTimeSlots(String amenity){
        List<String> timeSlots = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM amenities WHERE name = '"+amenity+"'", null);

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(1).equals("booked")) timeSlots.add("9AM TO 12PM");
                if(cursor.getString(2).equals("booked")) timeSlots.add("12PM TO 3PM");
                if(cursor.getString(3).equals("booked")) timeSlots.add("3PM TO 6PM");
            } while (cursor.moveToNext());
        }

        cursor.close();
        return timeSlots;
    }

    public boolean bookTimeSlot(String timeSlot,String amenity){
        SQLiteDatabase myDB = this.getReadableDatabase();
        try {
            myDB.execSQL("UPDATE amenities SET "+timeSlot+" = 'booked' where name = ?", new String[]{amenity});
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean cancelTimeSlot(String timeSlot,String amenity){
        SQLiteDatabase myDB = this.getReadableDatabase();
        try {
            myDB.execSQL("UPDATE amenities SET "+timeSlot+" = 'free' where name = ?", new String[]{amenity});
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


}
