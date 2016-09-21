package com.android.nanodegree.backend;

/**
 * Created by ravisha on 8/27/16.
 */
/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.nanodegree.java.library.Joke;

import javax.inject.Named;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;
import com.nanodegree.java.library.Joke;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokesAPI",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.nanodegree.android.com",
                ownerName = "backend.nanodegree.android.com",
                packagePath = ""
        )
)
public class JokesAPI {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();
        Joke joke = new Joke();
        response.setData(joke.getJoke());
        return response;
    }

}

