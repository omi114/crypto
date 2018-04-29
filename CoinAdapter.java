package com.example.android.crypto;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by omkarpathak on 4/18/18.
 */

public class CoinAdapter  extends ArrayAdapter<Coin> {

    private ArrayList<Coin> mCoinList;
    private Context mContext;



    public CoinAdapter(Context context, ArrayList<Coin> coinList) {
        super(context, 0, coinList);
        mCoinList = coinList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mCoinList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Coin currentCoin = getItem(position);

        TextView nameTV = listItemView.findViewById(R.id.name_tv);
        nameTV.setText(currentCoin.getName());

        TextView symbolTV = listItemView.findViewById(R.id.symbol_tv);
        symbolTV.setText(currentCoin.getSymbol());

        TextView valueTV = listItemView.findViewById(R.id.value_tv);
        valueTV.setText(currentCoin.getVal() + " USD");

        TextView percentTV = listItemView.findViewById(R.id.percent_tv);
        String percentString = currentCoin.getPercent() + "%";
        percentTV.setText(getSignedString(percentString));
        percentTV.setTextColor(mContext.getResources().getColor(getColorFromString(percentString)));

        return listItemView;

    }

    private int getColorFromString(String percentChangeString) {
        if(percentChangeString.contains("-")){
            return R.color.percentDownColor;
        }
        return R.color.percentUpColor;
    }

    private String getSignedString(String percentChangeString) {
        if(percentChangeString.contains("-")){
            return "-" + percentChangeString;
        }
        return "+" + percentChangeString;
    }
}
