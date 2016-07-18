package com.epicodus.beerfindr;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Peter on 7/18/16.
 */
public class BeerService {

    public static void findBeers(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String url = Constants.BASE_URL + Constants.RANDOM_BEER_PARAM + Constants.KEY + Constants.BREWERY_DB_KEY ;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);


    }
}
