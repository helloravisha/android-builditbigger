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

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
   // private static JokeApi jokeApi = null;
    private static MyApi myApi = null;
    private Context context;

     public EndpointsAsyncTask(Context context){
         this.context =context;
     }

/*
    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (jokeApi == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(context.getString(R.string.root_url_api));
            jokeApi = builder.build();

        }
        try {
            return jokeApi.putJoke(new MyBean()).execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
*/

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApi == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    //  10.0.2.2 is localhost's IP address in Android emulator
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApi = builder.build();
        }
        try {
            return myApi.getJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Intent androidLibraryIntent = new Intent(context, JokeDisplayAcitivity.class);
        androidLibraryIntent.putExtra(Constants.JOKE_INTENT,result);
        context.startActivity(androidLibraryIntent);
    }
}