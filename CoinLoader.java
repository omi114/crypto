package com.example.android.crypto;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.util.List;

/**
 * Created by omkarpathak on 4/18/18.
 */

public class CoinLoader extends AsyncTaskLoader<List<Coin>> {

    private String mCoinUrl;

    public CoinLoader(Context context, String coinUrl) {
        super(context);
        mCoinUrl = coinUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Coin> loadInBackground() {

        if (mCoinUrl == null) {
            return null;
        }
        try {
            return Utilities.fetchCoins(mCoinUrl);
        } catch (JSONException e) {
            Log.e("loadInBackground","Problem fetching news");
            return null;
        }
    }
}
