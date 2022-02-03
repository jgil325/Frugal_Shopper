package edu.uga.cs.frugalshopper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "FrugalShopper";

    private EditText priceA;
    private EditText poundsA;
    private EditText ouncesA;
    private EditText priceB;
    private EditText poundsB;
    private EditText ouncesB;
    private EditText priceC;
    private EditText poundsC;
    private EditText ouncesC;
    private TextView costBestProduct;
    private Button compare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i( DEBUG_TAG, "MainActivity.onCreate()" );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find all the individual views
        priceA = findViewById(R.id.priceA);
        priceB = findViewById(R.id.priceB);
        priceC = findViewById(R.id.priceC);
        poundsA = findViewById(R.id.poundsA);
        poundsB = findViewById(R.id.poundsB);
        poundsC = findViewById(R.id.poundsC);
        ouncesA = findViewById(R.id.ouncesA);
        ouncesB = findViewById(R.id.ouncesB);
        ouncesC = findViewById(R.id.ouncesC);

        //Set button listener
        compare = findViewById( R.id.button );
        compare.setOnClickListener( new ButtonClickListener() );
    }

    //Button functions
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            double costA;
            double costB;
            double costC;

        }
    }
}