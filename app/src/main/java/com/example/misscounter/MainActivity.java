package com.example.misscounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private TextView countTextView;
    private Button sendButton;
    private Button resetButton;
    private int count = 0;
    private String message = "Sorry honey, I didn't miss you at all today D: I must've been in a coma so don't worry, I still missed you in my dreams";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTextView = findViewById(R.id.countTextView);
        sendButton = findViewById(R.id.sendButton);
        resetButton = findViewById(R.id.resetButton);

        countTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countUp();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = -1;
                countUp();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count <= 5 && count >= 0) {
                    message = "I only missed you " + count + " times today. Perhaps I was busy and had a lot to do :( Or we spent the whole day together and I don't have to miss you hehe. I love you a lot, don't ever think I don't <3 ";
                } else if (count >= 5 && count <= 10) {
                    message = "I missed you " + count + " times today :) That's probably the number of breaks I had during the busy day. You always creep into my mind when I'm not doing anything <3";
                } else if (count >= 10 && count <= 20) {
                    message = "I missed you a lotttt today, like " + count + " times :D GEEz. SEND ME A SELFIE ALREADY so I can stop missing you so much. What have you been up to? Did you miss me a lot too? <3 ";
                } else {
                    message = "I missed you " + count + " times today!!! THATS A LOT. WHAT HAVE YOU BEEN DOING, I NEED YOU IN MY LIFE. You're in my mind the whole day. No matter what I do I can't get you out of my head. At this point you have to send me a selfie or I'm gonna cry <3 I LOVE YOU";
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_TEXT, message);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        loadCount();
        displayCount(count);
    }

    private void displayCount(int number) {
        countTextView.setText("" + number);
    }

    private void countUp() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        displayCount(++count);
        editor.putInt(TEXT, Integer.parseInt(countTextView.getText().toString()));
        editor.apply();
    }

    private void loadCount() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        count = sharedPreferences.getInt(TEXT, 0);
    }

}