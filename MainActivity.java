package com.example.android.crypto;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Coin>>{

    private String COIN_MARKET_URL = "https://api.coinmarketcap.com/v1/ticker/?limit=10";

    public static final int LOADER_ID = 1;

    private CoinAdapter mAdapter;

    private TextView emptyTextView;

    private ProgressBar progressBar;

    ListView coinListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get all the views
        //emptyTextView = (TextView) findViewById(R.id.empty_view);
        //progressBar = (ProgressBar) findViewById(R.id.progress);
        //progressBar.setVisibility(View.VISIBLE);
        // Find a reference to the {@link ListView} in the layout
        coinListView = (ListView) findViewById(R.id.list);
        // Set empty view as an alternative to list view
        coinListView.setEmptyView(emptyTextView);

        // Create a new {@link ArrayAdapter} of from a new ArrayList
        mAdapter = new CoinAdapter(this, new ArrayList<Coin>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        coinListView.setAdapter(mAdapter);

        // What happens when an item is clicked
        coinListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Coin clickedCoin = mAdapter.getItem(i);
                Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(clickedCoin.getUrl()));
                startActivity(urlIntent);
            }
        });


        //Check internet connectivity
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        if (isConnected) {
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            mAdapter.clear();
            //progressBar.setVisibility(View.GONE);
            //emptyTextView.setText(R.string.no_internet);
        }

    }

    @Override
    public Loader<List<Coin>> onCreateLoader(int i, Bundle bundle) {

        return new CoinLoader(this, COIN_MARKET_URL);


        //return new EarthquakeLoader(this, USGS_TOP_TEN_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Coin>> loader, List<Coin> coinList) {

        //progressBar.setVisibility(View.GONE);

        mAdapter.clear();

        //coinListView.setAdapter(null);

        Log.i("onLoadFinished:","" + coinList.isEmpty());
        if (coinList != null && !coinList.isEmpty()) {
            mAdapter.addAll(coinList);
            //coinListView.setAdapter(mAdapter);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Coin>> loader) {
        mAdapter.clear();
    }


}

