package teoespero.showmyname;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * MainActivity defines the logic that shows the information entered by the user.
 * @author Teo Espero (BS Software Development, WGU)
 * @version 1.0.07052023
 * @since 07/05/2023
 */
public class MainActivity extends AppCompatActivity {

    private Button btnShowMyName;
    private Button btnReset;
    private TextView txtName;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowMyName = findViewById(R.id.btnShowMyName);
        btnReset = findViewById(R.id.btnReset);
        txtName = findViewById(R.id.txtName);
        editTextName = findViewById(R.id.editTextName);

        btnShowMyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String theName = editTextName.getText().toString();
                if (!theName.isEmpty()){
                    txtName.setText("Hello " + theName + "! Nice meeting you.");
                }else{
                    txtName.setText("Hello there, what's your name?");
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName.setText("");
                txtName.setText("");
            }
        });
    }
}