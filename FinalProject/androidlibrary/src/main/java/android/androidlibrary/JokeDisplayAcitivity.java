package android.androidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.androidlibrary.util.Constants;

/**
 * This is a library activity that can be used with any project that can refer to this.
 * the kind of configuration we did for this is present in build.gradle as follows
 *
 *  compile project(':androidlibrary')  is configuration we can have in
 *  app -- build.gradle.
 *
 */
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
