package com.android.nanodegree.backend;

import com.nanodegree.java.library.Joke;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    public String  getJoke(){
        Joke joke = new Joke();
        return joke.getJoke();
    }
}