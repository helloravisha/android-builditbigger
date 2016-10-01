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
import android.widget.ProgressBar;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;


/**
 * This fragment is used for displaying free version with ads. the layout xml is being refered from
 * the common project  java.
 */
public class MainActivityFragment extends Fragment implements JokeListener {
    private ProgressBar mSpinner;
    private InterstitialAd mInterstitialAd;
    private String joke;

    public MainActivityFragment() {
    }

    /**
     * on screen load, we are trying to load the Interstitial ad ( not displaying), loading banner ad( displaying)
     * , displaying the button.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                displayJoke(joke);// Only once ad is closed, we ae trying to display the joke.
            }
        });
        loadInterstitialAd();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        Button button = (Button) root.findViewById(R.id.btnJoke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJoke();
            }
        });
        mSpinner = (ProgressBar) root.findViewById(R.id.progressBar);
        // As this is a banner ad, it will be loaded, not need of any method calls like show() for displaying the ad.
        mAdView.loadAd(getAddRequestObject());
        return root;
    }

    /**
     * Display interstital ad, once you receive the joke asynchronously, show() method in insterstital
     * class is used for poping up the ad, ensure that you are calling isloaded() method is called
     * first, only if ad is loaded , you can display the ad.
     * @param joke
     */
    @Override
    public void recieveJoke(String joke) {
        this.joke = joke;
        mSpinner.setVisibility(View.INVISIBLE);
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            displayJoke(joke);
        }

    }

    /**
     * Making use of android library to display the joke.
     * @param joke
     */
    public void displayJoke(String joke){
        Intent androidLibraryIntent = new Intent(getActivity(), JokeDisplayAcitivity.class);
        androidLibraryIntent.putExtra(Constants.JOKE_INTENT,joke);
        startActivity(androidLibraryIntent);

    }

    /**
     * Used for making async call and loading the joke.
     */
    public void getJoke() {
        mSpinner.setVisibility(View.VISIBLE);// Trying to show the loading image.
        new EndpointsAsyncTask().execute(this);// Triggering async call for loading the joke.
    }

    /**
     * Requesting an Ad,  Create an ad request. Check logcat output for the hashed device ID to
     * get test ads on a physical device. e.g.
     *  "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
     */
    private AdRequest getAddRequestObject(){
       return  new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }

    /**
     * loadAd() method in the following method call, will just load the ad and will not display in the case of
     * Interstitial , we need to make another method call show for displaying the ad.
      */
    private void loadInterstitialAd() {
        mInterstitialAd.loadAd(getAddRequestObject());
    }
}