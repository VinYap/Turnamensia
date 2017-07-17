package com.tugasakhir.turnamensiamember.View.Registration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 10/07/2017.
 */

public class SelectedPlayerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;

    public SelectedPlayerAdapter(Context context) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_selected_player, parent, false);

        return view;
    }
}
