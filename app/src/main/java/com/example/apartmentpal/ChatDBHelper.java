package com.example.apartmentpal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.apartmentpal.ChatContract;

public class ChatDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "chat.db";
    private static final int DATABASE_VERSION = 1;

    public ChatDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ChatDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CHAT_TABLE = "CREATE TABLE " +
                ChatContract.ChatEntry.TABLE_NAME + " (" +
                ChatContract.ChatEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ChatContract.ChatEntry.COLUMN_SENDER + " TEXT NOT NULL, " +
                ChatContract.ChatEntry.COLUMN_MESSAGE + " TEXT NOT NULL" +
                ");";
        db.execSQL(SQL_CREATE_CHAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ChatContract.ChatEntry.TABLE_NAME);
        onCreate(db);
    }
}
