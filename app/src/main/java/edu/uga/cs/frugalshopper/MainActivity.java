package edu.uga.cs.frugalshopper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "FrugalShopper";

    //Item A
    private EditText priceA;
    private EditText poundsA;
    private EditText ouncesA;

    //Item B
    private EditText priceB;
    private EditText poundsB;
    private EditText ouncesB;

    //Item C
    private EditText priceC;
    private EditText poundsC;
    private EditText ouncesC;

    //Results
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

        //Find final answer view
        costBestProduct = findViewById(R.id.costBestProduct);

    }

    //Button functions
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Variables for all the inputs
            double costA = 0.0;
            double costB = 0.0;
            double costC = 0.0;
            double lbsA = 0.0;
            double lbsB = 0.0;
            double lbsC = 0.0;
            double ozA = 0.0;
            double ozB = 0.0;
            double ozC = 0.0;

            //Variables for calculated values to compare
            double avgA = 0.0;
            double avgB = 0.0;
            double avgC = 0.0;

            try {
                costA = Double.parseDouble(priceA.getText().toString());
                costB = Double.parseDouble(priceB.getText().toString());
                costC = Double.parseDouble(priceC.getText().toString());

                lbsA = Double.parseDouble(poundsA.getText().toString());
                lbsB = Double.parseDouble(poundsB.getText().toString());
                lbsC = Double.parseDouble(poundsC.getText().toString());

                ozA = Double.parseDouble(ouncesA.getText().toString());
                ozB = Double.parseDouble(ouncesB.getText().toString());
                ozC = Double.parseDouble(ouncesC.getText().toString());
            } catch ( NumberFormatException nfe ) {
                // Toast is a short message displayed to the user
                Toast toast = Toast.makeText( getApplicationContext(),
                        "Enter positive decimal values",
                        Toast.LENGTH_SHORT );
                toast.show();
            }

            if(costA <= 0.0 || lbsA < 0 || ozA < 0 || costB <= 0.0 || lbsB < 0 || ozB < 0 || costC <= 0.0 || lbsC < 0 || ozC < 0) {
                Toast toast = Toast.makeText( getApplicationContext(),
                        "Enter positive decimal values",
                        Toast.LENGTH_SHORT );
                toast.show();
                return;
            }



        }
    }

    public double getPriceUnit(String price, String pounds, String ounces) {

    }
}