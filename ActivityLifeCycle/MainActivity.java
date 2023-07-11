package com.example.activitylifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

/**
 * This mobile app demonstrate how data is passed on to another screen in Android.
 * @author Teo Espero (BS Software Development, WGU)
 * @version 1.0
 * @since 07/11/2023
 */
public class MainActivity extends AppCompatActivity {
    private Button showGuess;
    private EditText theName;
    private final int REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theName = findViewById(R.id.guess_field);
        showGuess = findViewById(R.id.button_guess);


        showGuess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String guessedName = theName.getText().toString().trim();
                    if (!guessedName.isEmpty()){
                        Intent intent = new Intent(MainActivity.this, ShowGuess.class);
                        intent.putExtra("guess", guessedName);
                        startActivityForResult(intent, REQUEST_CODE);
                    }else{
                        Snackbar.make(theName, "Name field is empty", Snackbar.LENGTH_LONG )
                                .show();
                    }

                }
            });
        }

    /**
     * Retrieve information passed on by the second activity.
     * @param requestCode Verify id the request is valid
     * @param resultCode The result code from the second activity
     * @param data the Intent data passed.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            String message = data.getStringExtra("back_button_action");
            theName.setText("");
            Snackbar.make(theName, message, Snackbar.LENGTH_LONG).show();
        }
    }
}