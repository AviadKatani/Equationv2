package aviadapps.equationv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreditsActivity extends AppCompatActivity {
    Intent getIT;
    EditText aEV, bEV, cEV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        aEV = (EditText)findViewById(R.id.aTV);
        bEV = (EditText)findViewById(R.id.bTV);
        cEV = (EditText)findViewById(R.id.cTV);
        getIT = getIntent();
    }
    // Move to main activity while saving the data
    public void MainClicked(View view) {
        Intent mIntent = new Intent(this, MainActivity.class);
        mIntent.putExtra("a", getIT.getDoubleExtra("a", 0));
        mIntent.putExtra("b", getIT.getDoubleExtra("b", 0));
        mIntent.putExtra("c", getIT.getDoubleExtra("c", 0));
        mIntent.putExtra("sign", true);
        startActivity(mIntent);
    }
    // Move to the solution activity while saving the data
    public void SolutionClicked(View view) {
        Intent sIntent = new Intent(this, SolutionActivity.class);
        sIntent.putExtra("a", getIT.getDoubleExtra("a", 0));
        sIntent.putExtra("b", getIT.getDoubleExtra("b", 0));
        sIntent.putExtra("c", getIT.getDoubleExtra("c", 0));
        startActivity(sIntent);
    }
}
