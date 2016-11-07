package aviadapps.equationv2;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

        import java.text.DecimalFormat;

public class SolutionActivity extends AppCompatActivity {
    Intent getIntent;
    double a, b, c;
    WebView googleGraph;
    String urlGraph;
    TextView validTV, solutionTV, solution2TV, aTV, bTV, cTV;
    boolean isValid;
    double solution, solution2, calc;
    ImageView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        validTV = (TextView) findViewById(R.id.validTV);
        solutionTV = (TextView) findViewById(R.id.solutionTV);
        solution2TV = (TextView) findViewById(R.id.solution2TV);
        aTV = (TextView)findViewById(R.id.aTV);
        bTV = (TextView)findViewById(R.id.bTV);
        cTV = (TextView)findViewById(R.id.cTV);
        resultView = (ImageView)findViewById(R.id.resultView);
        googleGraph = (WebView) findViewById(R.id.googleGraph);
        googleGraph.setWebViewClient(new MyWebViewClient());
        googleGraph.getSettings().setJavaScriptEnabled(true);
        getIntent = getIntent();
        a = getIntent.getDoubleExtra("a", 0);
        b = getIntent.getDoubleExtra("b", 0);
        c = getIntent.getDoubleExtra("c", 0);
        // If the user didn't put anything
        if ((a == 0) && (b == 0) && (c == 0)) {
            validTV.setText("Choose numbers on the main tab.");
            solutionTV.setText("Choose numbers on the main tab.");
            solution2TV.setText("Choose numbers on the main tab.");
            // If the user did put something in
        } else {
            calc = b * b - 4 * a * c;
            // If the numbers in the square aren't equal to 0 => because you won't be able to square it
            if (0 <= calc) {
                isValid = true;
                validTV.setText("The solution is valid!");
                urlGraph = "https://www.google.co.il/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=" + a + "x%5E2%2" + b + "x%2B" + c;
                // The numbers in the square are 0 <=
            } else {
                isValid = false;
                validTV.setText("The solution isn't valid!");
                googleGraph.setVisibility(View.GONE);
                solutionTV.setText("You can't square a number below 0");
                solution2TV.setText("You can't square a number below 0");
            }

            // If the numbers can be calculated to a minimum 0ne solution
            if (isValid) {
                DecimalFormat df = new DecimalFormat("#.##");
                // If true, then there is only one solution
                solution = -b / (2 * a);
                solution = Double.valueOf(df.format(solution));
                solutionTV.setText("x1: " + solution);
                solution2TV.setText("There is no x2.");
            }
                    // Where to assign the image.
                // calc != 0, so there is 2 solutions
            else {
                DecimalFormat df = new DecimalFormat("#.##");
                solution = (-b + Math.sqrt(calc)) / (2 * a);
                solution = Double.valueOf(df.format(solution));
                solution2 = (-b - Math.sqrt(calc)) / (2 * a);
                solution2 = Double.valueOf(df.format(solution2));

                solutionTV.setText("x1: " + solution);
                solution2TV.setText("x2: " + solution2);
                // Where to assign the Image
            }

        }
    }

    // Move to credits activity while saving the data
    public void CreditsClicked(View view) {
        Intent cIntent = new Intent(this, CreditsActivity.class);
        cIntent.putExtra("a", a);
        cIntent.putExtra("b", b);
        cIntent.putExtra("c", c);
        cIntent.putExtra("sign", true);
        startActivity(cIntent);
    }
    // Move to main activity while saving the data
    public void MainClicked(View view) {
        Intent mIntent = new Intent(this, MainActivity.class);
        if (isValid) {
            mIntent.putExtra("a", a);
            mIntent.putExtra("b", b);
            mIntent.putExtra("c", c);
            mIntent.putExtra("sign", isValid);
        }
        startActivity(mIntent);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
