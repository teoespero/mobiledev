package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class receives the text entered from the other screen.
 * @author Teo Espero (BS Software Development, WGU)
 * @version 1.0
 * @since 07/11/2023
 */
public class ShowGuess extends AppCompatActivity {

    private TextView theName;
    private Button backButtonAction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);
        theName = findViewById(R.id.received_text_view);
        backButtonAction =findViewById(R.id.back_button);

        String stringValue = getIntent().getStringExtra("guess");
        theName.setText("Hello " + stringValue + "!\nWelcome to my Android app.");

        backButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("back_button_action", "The back button was clicked...");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}