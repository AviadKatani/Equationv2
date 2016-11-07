package aviadapps.equationv2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random rnd;
    Intent getIT;
    double a,b,c;
    EditText aEV, bEV, cEV;
    boolean click;
    boolean isReturned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aEV = (EditText)findViewById(R.id.aTV);
        bEV = (EditText)findViewById(R.id.bTV);
        cEV = (EditText)findViewById(R.id.cTV);

        getIT = getIntent();
        isReturned = getIT.getBooleanExtra("sign", true);
        // If the main activity is returned (goes back from another activity)
        if(isReturned) {
            a = getIT.getDoubleExtra("a", 0);
            b = getIT.getDoubleExtra("b", 0);
            c = getIT.getDoubleExtra("c", 0);
            aEV.setText("" + a);
            bEV.setText("" + b);
            cEV.setText("" + c);
        }
    }
    // Move to credits activity while saving the data
    public void CreditsClicked(View view) {
        isReturned = true;
        Intent cIntent = new Intent(this, CreditsActivity.class);
        cIntent.putExtra("a", a);
        cIntent.putExtra("b", b);
        cIntent.putExtra("c", c);
        cIntent.putExtra("sign", true);
        startActivity(cIntent);
    }
    // Move to the solution activity while saving the data
    public void SolutionClicked(View view) {
        isReturned = true;
        Intent sIntent = new Intent(this, SolutionActivity.class);
        sIntent.putExtra("a", a);
        sIntent.putExtra("b", b);
        sIntent.putExtra("c", c);
        startActivity(sIntent);
    }
    // Generate numbers between -10 to 10
    public void GenerateClicked(View view) {
        rnd = new Random();
        a = rnd.nextInt(21) - 10;
        b = rnd.nextInt(21) - 10;
        c = rnd.nextInt(21) - 10;
        aEV.setText("" + a);
        bEV.setText("" + b);
        cEV.setText("" + c);
        // Generate 3 numbers to 3 EV's.
    }

    // When clicked,  we'll try to take the numbers from the input and go to solution activity
    public void CalculateClicked(View view) {
        // If nothing goes wrong and the app can do try { .. } without crash
        try {
            a = Double.parseDouble(aEV.getText().toString());
            b = Double.parseDouble(bEV.getText().toString());
            c = Double.parseDouble(cEV.getText().toString());
            // If a is equal to 0, the equation cannot be solved (no solution)
            if(a == 0) {
                Toast.makeText(this, "A can't be 0" + a, Toast.LENGTH_LONG).show();
            }
            // If the equation can be solved, then the data goes to Solution Activity
            else {
                click = true;
                isReturned = true;
                Intent sIntent = new Intent(this, SolutionActivity.class);
                sIntent.putExtra("a", a);
                sIntent.putExtra("b", b);
                sIntent.putExtra("c", c);
                startActivity(sIntent);
            }
        }
        // If something is wrong... (Most of the times, there aren't inputs which cause to a crash
        catch (Exception e) {
            aEV.setError("You have to choose a number or click the Generate button.");
            bEV.setError("You have to choose a number or click the Generate button.");
            cEV.setError("You have to choose a number or click the Generate button.");
            click = false;
        }
    }
}

