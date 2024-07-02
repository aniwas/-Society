package com.example.apartmentpal;

import android.provider.BaseColumns;

public class ChatContract {
    public static class ChatEntry implements BaseColumns {
        public static final String TABLE_NAME = "chat";
        public static final String COLUMN_SENDER = "sender";
        public static final String COLUMN_MESSAGE = "message";
    }
}
