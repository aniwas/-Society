package com.example.apartmentpal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.*;

public class AllAllowed extends AppCompatActivity {
    VisitorDBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_allowed);

        db = new VisitorDBHelper(this);

        List<List<String>> allowed = db.getAllowedVisitors();
        LinearLayout cardContainer = findViewById(R.id.cardContainer);

        for(List<String> list : allowed){
            CardView cardView = new CardView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 16); // Margin between cards
            cardView.setLayoutParams(layoutParams);

            cardView.setCardBackgroundColor(getResources().getColor(R.color.card_background_color));
            cardView.setRadius(20);

            LinearLayout innerLayout = new LinearLayout(this);
            innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            innerLayout.setOrientation(LinearLayout.VERTICAL);
            cardView.addView(innerLayout);

            for (String info : list) {
                TextView textView = new TextView(this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                textView.setText(info);
                textView.setTextColor(Color.WHITE);
                textView.setPadding(16, 16, 16, 16);
                innerLayout.addView(textView);
            }

            cardContainer.addView(cardView);
        }


    }
}