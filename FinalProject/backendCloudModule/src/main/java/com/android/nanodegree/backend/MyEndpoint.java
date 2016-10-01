/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.android.nanodegree.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import com.nanodegree.java.library.Joke;

/**
 * An endpoint class we are exposing
 *
 * Notice the annotation @Api; this is where you set the configuration of the backend API, such as its name and version,
 * along with other possible settings. (See Endpoint Annotations for all of the available attributes of this annotation.)
 * <p/>
 * This is where you specify the name and version of the API, both of which show up in the API Explorer
 * <p/>
 * Notice the @ApiMethod annotation; it allows you to configure your methods at a finer level than
 * the API-wide configuration.
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.nanodegree.android.com",
                ownerName = "backend.nanodegree.android.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "getJoke")
    public MyBean getJoke(MyBean joke) {
        MyBean response = new MyBean();
        return joke;
    }

}
