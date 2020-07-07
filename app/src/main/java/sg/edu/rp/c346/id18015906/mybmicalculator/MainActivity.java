package sg.edu.rp.c346.id18015906.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etWeight;
    EditText etHeight;
    Button calc;
    Button reset;
    TextView date;
    TextView bmi;

    Calendar now = Calendar.getInstance();

    String datetime = (Calendar.DAY_OF_MONTH) + "/" +
            (now.get(Calendar.MONTH)+1) + "/" +
            now.get(Calendar.YEAR) + "" +
            now.get(Calendar.HOUR_OF_DAY) + ":" +
            now.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);
        calc = findViewById(R.id.buttonCalculate);
        reset = findViewById(R.id.buttonReset);
        date = findViewById(R.id.textViewDate);
        bmi = findViewById(R.id.textViewBMI);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double calcbmi = Double.parseDouble(etWeight.getText().toString()) * Double.parseDouble(etHeight.getText().toString()) * Double.parseDouble(etHeight.getText().toString());
                String output = String.format("Last Calculated BMI: %f",calcbmi);
                date.setText(String.format("Last Calculated Date: ",datetime));
                bmi.setText(output);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWeight.setText("");
                etHeight.setText("");
                date.setText(R.string.lastcalcdate);
                bmi.setText(R.string.lastcalcbmi);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Step 1a: Obtain an instance of the Shared Preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1b: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step 1c: Add the key-value pair
        prefEdit.putFloat("weight", Float.parseFloat(etWeight.getText().toString()));
        prefEdit.putFloat("height", Float.parseFloat(etHeight.getText().toString()));
        prefEdit.putString("date", datetime);
        prefEdit.putFloat("bmi", Float.parseFloat(bmi.getText().toString()));

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data with the key from the SharedPreference object
        Float weight = prefs.getFloat("weight", (float)0.00);
        Float height = prefs.getFloat("height", (float)0);
        String datetxt = prefs.getString("date", "");
        Float bmitxt = prefs.getFloat("bmi", (float)0.00);

        etWeight.setText(weight.toString());
        etHeight.setText(height.toString());
        String output = String.format("Last Calculated BMI: %f",bmitxt);
        date.setText(String.format("Last Calculated Date: %f",datetime));
        bmi.setText(output);

    }


}
