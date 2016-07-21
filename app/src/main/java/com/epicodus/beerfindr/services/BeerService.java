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

    public static void findBeers(Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String url = Constants.BASE_URL + Constants.RANDOM_BEER_PARAM +  Constants.KEY + Constants.BREWERY_DB_KEY;
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
                JSONObject dataJSON = returnJSON.getJSONObject("data");
                    String name = "no name";
                    if (dataJSON.has("name") == true) {
                        name = dataJSON.getString("name");
                    }
                    String description = "no description for this beer";
                    if (dataJSON.has("description") == true) {
                        description = dataJSON.getString("description");
                    }
                    String IBU = "No IBU information";
                    if (dataJSON.has("ibu") == true) {
                        IBU = dataJSON.getString("ibu");
                    }
                    String ABV = "No ABV information";
                    if (dataJSON.has("abv") == true) {
                        ABV = dataJSON.getString("abv");
                    }
                    String id = "no id";
                    if (dataJSON.has("id") == true) {
                        id = dataJSON.getString("id");
                    }

                    Beer beer = new Beer(name, description, ABV, IBU, id);
                    beers.add(beer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return beers;
    }
}
