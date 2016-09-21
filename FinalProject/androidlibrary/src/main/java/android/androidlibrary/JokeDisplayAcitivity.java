package android.androidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.androidlibrary.util.Constants;

public class JokeDisplayAcitivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display_acitivity);
        // Processing the joke intent
        String joke = getIntent().getStringExtra(Constants.JOKE_INTENT);
        TextView tXtJoke = (TextView) findViewById(R.id.txtJoke);
        tXtJoke.setText(joke);
    }
}
