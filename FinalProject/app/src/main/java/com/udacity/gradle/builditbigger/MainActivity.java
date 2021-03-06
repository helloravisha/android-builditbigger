package com.udacity.gradle.builditbigger;

import android.androidlibrary.JokeDisplayAcitivity;
import android.androidlibrary.util.Constants;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.nanodegree.java.library.Joke;
import  com.android.nanodegree.util.EndpointsAsyncTask;


public class MainActivity extends ActionBarActivity {

    private Joke joke = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        joke = new Joke();
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //
        //Toast.makeText(this, joke.getJoke(), Toast.LENGTH_SHORT).show();

        /*
        Intent androidLibraryIntent = new Intent(this, JokeDisplayAcitivity.class);
        androidLibraryIntent.putExtra(Constants.JOKE_INTENT,joke.getJoke());
        startActivity(androidLibraryIntent);
        */


       // new EndpointsAsyncTask(this).execute();

    }


}
