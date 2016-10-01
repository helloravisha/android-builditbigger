package com.nanodegree.java.library;

/**
 * //**
 * This is a java library  that can be used with any project that can refer to this.
 * the kind of configuration we did for this is present in build.gradle as follows
 *
 * compile project(':javalibrary')  is configuration we can have in
 *  app -- build.gradle.  javalibrary is the name of the project where
 *  we created this class.
 *
 */

public class Joke {

    public String getJoke(){
        return "HA AH ha..";
    }
}
