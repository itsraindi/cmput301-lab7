package com.example.androiduitesting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showactivity);

        // Get selected city name from MainActivity
        String cityName = getIntent().getStringExtra("City");

        // Display city name
        TextView cityText = findViewById(R.id.showactivity_city_text);
        cityText.setText(cityName);

        // Return Button setup
        Button return_button = findViewById(R.id.showactivity_return_button);
        return_button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                finish();  // return to MainActivity
             }
        });
    }
}
