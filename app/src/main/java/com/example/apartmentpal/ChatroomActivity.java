package com.example.apartmentpal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.apartmentpal.ChatContract;
import com.example.apartmentpal.ChatDBHelper;

public class ChatroomActivity extends AppCompatActivity {

    private LinearLayout chatMessagesLayout;
    private EditText messageEditText;
    private Button sendButton;
    private ChatDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        dbHelper = new ChatDBHelper(this);

        chatMessagesLayout = findViewById(R.id.chatMessagesLayout);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);

        Intent intent = getIntent();
        final String user = intent.getStringExtra("name");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(user);
            }
        });

        loadChatMessages(); // Load existing messages when the activity starts
    }

    private void sendMessage(String user) {
        String username = user; // Replace this with the actual username
        String message = messageEditText.getText().toString().trim();

        if (!message.isEmpty()) {
            addMessageToChat(username, message);
            saveMessageToDatabase(username, message);
            messageEditText.getText().clear();
        }
    }

    private void loadChatMessages() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projection = {
                ChatContract.ChatEntry.COLUMN_SENDER,
                ChatContract.ChatEntry.COLUMN_MESSAGE
        };

        Cursor cursor = database.query(
                ChatContract.ChatEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String sender = cursor.getString(cursor.getColumnIndexOrThrow(ChatContract.ChatEntry.COLUMN_SENDER));
            String message = cursor.getString(cursor.getColumnIndexOrThrow(ChatContract.ChatEntry.COLUMN_MESSAGE));
            addMessageToChat(sender, message);
        }

        cursor.close();
        database.close();
    }

    private void addMessageToChat(String username, String message) {
        TextView usernameTextView = new TextView(this);
        usernameTextView.setTextAppearance(android.R.style.TextAppearance_Medium);
        usernameTextView.setTypeface(null, Typeface.BOLD);
        usernameTextView.setText(username);

        TextView messageTextView = new TextView(this);
        messageTextView.setText(message);
        messageTextView.setTextColor(Color.WHITE);
        messageTextView.setTextSize(16);

        LinearLayout.LayoutParams usernameParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        usernameParams.setMargins(0, 8, 0, 2); // Adjust the margin between username and message

        LinearLayout.LayoutParams messageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        messageParams.setMargins(0, 2, 0, 16); // Adjust the margin below the message

        chatMessagesLayout.addView(usernameTextView, usernameParams);
        chatMessagesLayout.addView(messageTextView, messageParams);
    }



    private void saveMessageToDatabase(String username, String message) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ChatContract.ChatEntry.COLUMN_SENDER, username);
        values.put(ChatContract.ChatEntry.COLUMN_MESSAGE, message);
        database.insert(ChatContract.ChatEntry.TABLE_NAME, null, values);
        database.close();
    }
}


