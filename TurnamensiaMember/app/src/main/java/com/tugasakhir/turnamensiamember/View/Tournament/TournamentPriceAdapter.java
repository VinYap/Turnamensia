package com.tugasakhir.turnamensiamember.View.Tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 02/07/2017.
 */

public class TournamentPriceAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;

    public TournamentPriceAdapter(Context context) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_tournament_price, parent, false);
        return view;
    }
}
