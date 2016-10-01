package com.android.nanodegree.util;
import android.androidlibrary.JokeDisplayAcitivity;
import android.androidlibrary.util.Constants;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import android.androidlibrary.JokeDisplayAcitivity;



import com.android.nanodegree.backend.myApi.MyApi;
import com.android.nanodegree.backend.myApi.model.MyBean;


import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import android.content.Context;

import java.io.IOException;

import javax.xml.transform.Result;

/**
 * The three generic arguments passed to  Asyntask are passed as data types
 * to three methods of async task doInBackground(),progress () and onPostExecute
 * The Middle arguments in the generics is used for progress status.
 */
public class EndpointsAsyncTask extends AsyncTask<JokeListener, Void, String> {
    private static MyApi myApi = null;
    private Context context;
    private JokeListener jokeListener = null;
    private final String HOST_NAME = "http://10.0.2.2:8080/_ah/api/";

     public EndpointsAsyncTask(){

     }

    /**
     * This method "arguments" and "retun type" completely depends on
     * First and last arguments of the above syntax AsyncTask<JokeListener, Void, String>
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(JokeListener... params) {
        if(myApi == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    //  10.0.2.2 is localhost's IP address in Android emulator
                    .setRootUrl(HOST_NAME)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApi = builder.build();
            jokeListener = params[0];
        }
        try {
            return myApi.getJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Once we are done with the complete execution of this async task, this method will be called.
     * the output of the above method "doInBackGround" will be passed as argument for this method.
     * AsyncTask<JokeListener, Void, String>  , in this syntax, the last parameter should match
     * the return type of "doInBackGround" and argument of onPostExecute()
     *
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        jokeListener.recieveJoke(result);
    }
}