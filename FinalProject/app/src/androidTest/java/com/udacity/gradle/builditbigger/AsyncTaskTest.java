package com.udacity.gradle.builditbigger;

/**
 * Created by ravisha on 9/19/16.
 */


import android.test.AndroidTestCase;
import android.util.Log;
import com.android.nanodegree.util.EndpointsAsyncTask;
import com.android.nanodegree.util.JokeListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AsyncTaskTest extends AndroidTestCase implements JokeListener{

    private static final String LOG_TAG = "NonEmptyStringTest";
    private final CountDownLatch mSignal = new CountDownLatch(1);



    @SuppressWarnings("unchecked")
    public void testEndpointsAsyncTask() {

        new EndpointsAsyncTask().execute(this);
        try {
            boolean success = mSignal.await(5, TimeUnit.SECONDS);
            if (!success) {
                fail("Time out..");
            }
        } catch (InterruptedException e) {
            fail();
        }


    }

    @Override
    public void recieveJoke(String joke) {
        assertTrue(joke != null && joke.length() > 0);
        mSignal.countDown();
    }
}