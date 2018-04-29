package com.example.android.crypto;

/**
 * Created by omkarpathak on 4/18/18.
 */

public class Coin {

    private String mName;
    private String mSymbol;
        private String mPrice;
        private String mPercent;
        private String mUrl;

        public Coin (String name, String symbol, String price, String percent, String url) {
            mName = name;
            mSymbol = symbol;
            mPrice = price;
            mPercent = percent;
            mUrl = url;
        }

    public String getName() {
        return mName;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public String getVal() {
        return mPrice;
    }

    public String getPercent() {
        return mPercent;
    }

    public String getUrl() {
            return  mUrl;
        }

}
