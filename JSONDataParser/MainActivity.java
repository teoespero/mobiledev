package teoespero.jsonarrayparser;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This app demonstrates the use of the Volley API to parse a JSON files from a website
 * @author Teo Espero (BS Software Development, WGU)
 * @version 1.2
 * @since 07/11/2023
 */
public class MainActivity extends AppCompatActivity {

    String url = "https://jsonplaceholder.typicode.com/posts";
    RequestQueue queue;

    private Button previous_button;
    private Button next_button;
    private TextView userId_fld;
    private TextView id_fld;
    private TextView title_fld;
    private TextView body_fld;
    private int arrCounter = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId_fld =findViewById(R.id.user_id_data);
        id_fld = findViewById(R.id.id_data);
        title_fld = findViewById(R.id.title_data);
        body_fld = findViewById(R.id.body_data);
        previous_button = findViewById(R.id.previous_button);
        next_button = findViewById(R.id.next_button);
        queue = Volley.newRequestQueue(this);



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                for (int counter = 0; counter < response.length(); counter++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(counter);
                        list.add(response.getJSONObject(counter));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    userId_fld.setText(list.get(arrCounter).get("userId").toString());
                    id_fld.setText(list.get(arrCounter).get("id").toString());
                    title_fld.setText(list.get(arrCounter).get("title").toString());
                    body_fld.setText(list.get(arrCounter).get("body").toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                next_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (arrCounter < response.length()){
                            next_action(view);
                            try {
                                userId_fld.setText(list.get(arrCounter).get("userId").toString());
                                id_fld.setText(list.get(arrCounter).get("id").toString());
                                title_fld.setText(list.get(arrCounter).get("title").toString());
                                body_fld.setText(list.get(arrCounter).get("body").toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Snackbar.make(userId_fld, "End of file reached...", Snackbar.LENGTH_LONG).show();
                        }

                    }
                });

                previous_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (arrCounter > 0 ){
                            previous_action(view);
                            try {
                                userId_fld.setText(list.get(arrCounter).get("userId").toString());
                                id_fld.setText(list.get(arrCounter).get("id").toString());
                                title_fld.setText(list.get(arrCounter).get("title").toString());
                                body_fld.setText(list.get(arrCounter).get("body").toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Snackbar.make(userId_fld, "Beginning of file reached...", Snackbar.LENGTH_LONG).show();
                        }

                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSON", "An error occurred...");
            }
        });

        queue.add(jsonArrayRequest);

    }

    public void previous_action(View view) {
        --arrCounter;
    }

    public void next_action(View view) {
        ++arrCounter;
    }
}