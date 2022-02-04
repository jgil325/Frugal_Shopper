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

    //Unit Prices
    private double unitPriceA, unitPriceB, unitPriceC;

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
            unitPriceA = getPricePerUnit(priceA.getText().toString(), poundsA.getText().toString(), ouncesA.getText().toString());
            unitPriceB = getPricePerUnit(priceB.getText().toString(), poundsB.getText().toString(), ouncesB.getText().toString());
            unitPriceC = getPricePerUnit(priceC.getText().toString(), poundsC.getText().toString(), ouncesC.getText().toString());


        }
    }

    public double getPricePerUnit(String price, String pounds, String ounces) {
        double unitPrice = 0.0;
        double calcPrice, calcPounds, calcOunces, totalOunces;
        if(checkRowEmpty(price, pounds, ounces)) {
            unitPrice = -1;
        } else if(checkPriceEmpty(price)) {
            Toast toast = Toast.makeText( getApplicationContext(),
                    "A price must be entered or the row must be empty",
                    Toast.LENGTH_SHORT );
            toast.show();
            return -1;
        }

        if(pounds.equals("")) {
            calcPrice = Double.parseDouble(price);
            calcOunces = Double.parseDouble(ounces);
            totalOunces = calcOunces;
            unitPrice = calcPrice / totalOunces;
            return unitPrice;
        }

        if(ounces.equals("")) {
            calcPrice = Double.parseDouble(price);
            calcPounds = Double.parseDouble(pounds);
            totalOunces = calcPounds * 16;
            unitPrice = calcPrice / totalOunces;
            return unitPrice;
        }

        calcPrice = Double.parseDouble(price);
        calcPounds = Double.parseDouble(pounds);
        calcOunces = Double.parseDouble(ounces);
        totalOunces = (calcPounds * 16) + calcOunces;
        unitPrice = calcPrice / totalOunces;
        return unitPrice;

    }

    public boolean checkRowEmpty(String price, String pounds, String ounces) {
        if(price.equals("") && pounds.equals("") && ounces.equals("")) {
            return true;
        }
        return false;
    }

    public boolean checkPriceEmpty(String price) {
        if(price.equals("") && !(pounds.equals("")) && !(ounces.equals(""))) {
            return true;
        }
        return false;
    }
}