package com.epicodus.beerfindr.services;

import android.util.Log;

import com.epicodus.beerfindr.Constants;
import com.epicodus.beerfindr.models.Beer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Peter on 7/18/16.
 */
public class BeerService {
    public static final String TAG = BeerService.class.getSimpleName();

    public static void findBeers(String abv, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String url = Constants.BASE_URL + Constants.RANDOM_BEER_PARAM + Constants.ABV_KEY + abv +  Constants.KEY + Constants.BREWERY_DB_KEY;
        Log.d(TAG, url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Beer> processResults(Response response) {
        ArrayList<Beer> beers = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject returnJSON = new JSONObject(jsonData);
                JSONArray dataJSON = returnJSON.getJSONArray("data");
                for (int i = 0; i < dataJSON.length(); i++) {
                    JSONObject beerJSON = dataJSON.getJSONObject(i);
                    String name = "no name";
                    if (beerJSON.has("name") == true) {
                        name = beerJSON.getString("name");
                    }
                    String description = "no description for this beer";
                    if (beerJSON.has("description") == true) {
                        description = beerJSON.getString("description");
                    }
                    String IBU = "No IBU information";
                    if (beerJSON.has("ibu") == true) {
                        IBU = beerJSON.getString("ibu");
                    }
                    String ABV = "No ABV information";
                    if (beerJSON.has("abv") == true) {
                        ABV = beerJSON.getString("abv");
                    }
                    String id = "no id";
                    if (beerJSON.has("id") == true) {
                        id = beerJSON.getString("id");
                    }

                    Beer beer = new Beer(name, description, ABV, IBU, id);
                    beers.add(beer);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beers;
    }
}
