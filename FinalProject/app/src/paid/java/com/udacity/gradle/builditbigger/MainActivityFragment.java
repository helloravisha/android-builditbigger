package com.udacity.gradle.builditbigger;

import android.androidlibrary.JokeDisplayAcitivity;
import android.androidlibrary.util.Constants;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.nanodegree.util.EndpointsAsyncTask;
import com.android.nanodegree.util.JokeListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * This Fragment is used for displaying the paid version of our app. the layout for
 * this fragment is present in paid project folder only. The reason for implementing
 * a customer interface JokeListner is to listen to joke from async task asynchronously.
 */
public class MainActivityFragment extends Fragment implements JokeListener{

    public MainActivityFragment() {
    }

    /**
     * On createView we are just displaying the button, no ads , as this is the paid version.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) root.findViewById(R.id.btnJoke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJoke();
            }
        });
        return root;
    }

    /**
     * Following method is the part of an interface JokeListener, as this is the listner method, this will
     * be called by aysnc task once joke is ready.
     * @param joke
     */
    @Override
    public void recieveJoke(String joke) {
        Intent androidLibraryIntent = new Intent(getActivity(), JokeDisplayAcitivity.class);
        androidLibraryIntent.putExtra(Constants.JOKE_INTENT,joke);
        startActivity(androidLibraryIntent);

    }

    /**
     * Triggering the async task
     */
    public void getJoke(){
        new EndpointsAsyncTask().execute(this);
    }
}