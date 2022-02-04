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

            //If a row has information but is missing price (which is required)
            if(unitPriceA == -2 || unitPriceB == -2 || unitPriceC == -2) {
                if(unitPriceA == -2) {
                    priceA.setText("0.0");
                } if(unitPriceB == -2) {
                    priceB.setText("0.0");
                } if(unitPriceC == -2) {
                    priceC.setText("0.0");
                }
            }

            //If a row has price but nothing else
            if(unitPriceA == -3 || unitPriceB == -3 || unitPriceC == -3) {
                if(unitPriceA == -2) {
                    poundsA.setText("0.0");
                    ouncesA.setText("0.0");
                } if(unitPriceB == -2) {
                    poundsB.setText("0.0");
                    ouncesB.setText("0.0");
                } if(unitPriceC == -2) {
                    poundsC.setText("0.0");
                    ouncesC.setText("0.0");
                }
            }

            //If none of the rows have any information
            if(unitPriceA == -1 && unitPriceB == -1 && unitPriceC == -1) {
                Toast toast = Toast.makeText( getApplicationContext(),
                        "Please enter a value for at least one item",
                        Toast.LENGTH_SHORT );
                toast.show();
            }

            //Comparing all three rows
            if(unitPriceA >= 0.0 && unitPriceB >= 0.0 && unitPriceC >= 0.0) {
                if((unitPriceA < unitPriceB) && (unitPriceA < unitPriceC)) {
                    costBestProduct.setText("Best Buy: A");
                }
                if((unitPriceB < unitPriceA) && (unitPriceB < unitPriceC)) {
                    costBestProduct.setText("Best Buy: B");
                }
                if((unitPriceC < unitPriceA) && (unitPriceC < unitPriceB)) {
                    costBestProduct.setText("Best Buy: C");
                }
                if(unitPriceA == unitPriceB && unitPriceA == unitPriceC) {
                    costBestProduct.setText("Best Buy: ALL OF THEM");
                }
            } else if(unitPriceA >= 0.0 && unitPriceB >= 0.0) { //Here we only compare two rows since one would be empty
                if(unitPriceA < unitPriceB) {
                    costBestProduct.setText("Best Buy: A");
                } else if(unitPriceB < unitPriceA) {
                    costBestProduct.setText("Best Buy: B");
                } else if(unitPriceB == unitPriceA) {
                    costBestProduct.setText("Best Buy: A and B TIE");
                }
            } else if(unitPriceC >= 0.0 && unitPriceB >= 0.0) {
                if(unitPriceC < unitPriceB) {
                    costBestProduct.setText("Best Buy: C");
                } else if(unitPriceB < unitPriceC){
                    costBestProduct.setText("Best Buy: B");
                } else if(unitPriceB == unitPriceC) {
                    costBestProduct.setText("Best Buy: B and C TIE");
                }
            } else if(unitPriceA >= 0.0 && unitPriceC >= 0.0) {
                if(unitPriceA < unitPriceC) {
                    costBestProduct.setText("Best Buy: A");
                } else if(unitPriceC < unitPriceA){
                    costBestProduct.setText("Best Buy: C");
                } else if(unitPriceC == unitPriceA) {
                    costBestProduct.setText("Best Buy: A and C TIE");
                }
            } else if(unitPriceA >= 0.0) { //Here we only have one completed row
                costBestProduct.setText("Best Buy: A");
            } else if(unitPriceB >= 0.0) {
                costBestProduct.setText("Best Buy: B");
            } else if(unitPriceC >= 0.0) {
                costBestProduct.setText("Best Buy: C");
            }
        }
    }

    public double getPricePerUnit(String price, String pounds, String ounces) {
        double unitPrice = 0.0;
        double calcPrice, calcPounds, calcOunces, totalOunces;

        //Checking if the row is empty and if not if the price box is filled
        if(checkRowEmpty(price, pounds, ounces)) {
            return -1;
        } else if(checkPriceEmpty(price)) {
            Toast toast = Toast.makeText( getApplicationContext(),
                    "A price must be entered or the row must be empty",
                    Toast.LENGTH_SHORT );
            toast.show();
            return -2;
        }

        //Error if both pounds and ounces are empty while price is not
        if(pounds.equals("") && ounces.equals("")) {
            Toast toast = Toast.makeText( getApplicationContext(),
                    "At least pounds or ounces must be entered for one of them",
                    Toast.LENGTH_SHORT );
            toast.show();
            return -3;
        }

        //Calculating the price/unit with empty pounds
        if(pounds.equals("")) {
            calcPrice = Double.parseDouble(price);
            calcOunces = Double.parseDouble(ounces);
            totalOunces = calcOunces;
            unitPrice = calcPrice / totalOunces;
            return unitPrice;
        }
        //Calculating the price/unit with empty ounces
        if(ounces.equals("")) {
            calcPrice = Double.parseDouble(price);
            calcPounds = Double.parseDouble(pounds);
            totalOunces = calcPounds * 16;
            unitPrice = calcPrice / totalOunces;
            return unitPrice;
        }

        //Calculating the price/unit with all information there
        calcPrice = Double.parseDouble(price);
        calcPounds = Double.parseDouble(pounds);
        calcOunces = Double.parseDouble(ounces);
        totalOunces = (calcPounds * 16) + calcOunces;
        unitPrice = calcPrice / totalOunces;
        return unitPrice;

    }

    //Checks if the row is completely empty
    public boolean checkRowEmpty(String price, String pounds, String ounces) {
        if(price.equals("") && pounds.equals("") && ounces.equals("")) {
            return true;
        }
        return false;
    }

    //Checks if the price box of the row is empty
    public boolean checkPriceEmpty(String price) {
        if(price.equals("")) {
            return true;
        }
        return false;
    }

}