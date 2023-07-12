package teoespero.parsedata;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

/**
 * This app demonstrates the use of the Volley API to parse strings from a website
 * @author Teo Espero (BS Software Development, WGU)
 * @version 1.0
 * @since 07/11/2023
 */
public class MainActivity extends AppCompatActivity {

    String url = "https://www.google.com";
    private TextView responseArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        responseArea = findViewById(R.id.reponse_text);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            //  display the contents of the URL

            responseArea.setText(response.substring(0,500));
        }, error -> {
            Snackbar.make(responseArea, "An error has occured...", Snackbar.LENGTH_LONG).show();
        });

        queue.add(stringRequest);
    }
}