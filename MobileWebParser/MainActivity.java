package teoespero.parsedata;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
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
 * @version 1.2
 * @since 07/11/2023
 */
public class MainActivity extends AppCompatActivity {

    String url;
    private Button parseTheSite;
    private Button clearTheHTMLWindow;
    private EditText siteUrl;
    private ScrollView htmlWindow;
    private TextView siteContents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseTheSite = findViewById(R.id.parse_the_site_button);
        htmlWindow = findViewById(R.id.html_window);
        siteUrl = findViewById(R.id.site_to_parse);
        siteContents = findViewById(R.id.site_text);
        clearTheHTMLWindow = findViewById(R.id.clear_html_window);

        RequestQueue queue = Volley.newRequestQueue(this);
        parseTheSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url = siteUrl.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
                    // display the contents of the URL
                    siteContents.setText(response.substring(0,1500));
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert inputMethodManager != null;
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }, error -> {
                    Snackbar.make(htmlWindow, "An error has occurred...", Snackbar.LENGTH_LONG).show();
                });
                queue.add(stringRequest);
            }
        });

        clearTheHTMLWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siteContents.setText("");
                siteUrl.setText("");
            }
        });




    }
}