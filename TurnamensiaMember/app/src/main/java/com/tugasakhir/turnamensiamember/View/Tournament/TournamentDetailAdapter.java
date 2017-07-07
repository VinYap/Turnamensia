package com.tugasakhir.turnamensiamember.View.Tournament;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tugasakhir.turnamensiamember.Model.Basic.TournamentDetail;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 02/07/2017.
 */

public class TournamentDetailAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;

    private List<TournamentDetail> mTournamentDetails;

    public TournamentDetailAdapter(Context context, List<TournamentDetail> tournamentDetails) {
        this.mContext = context;
        this.mTournamentDetails = tournamentDetails;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mTournamentDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return mTournamentDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_tournament_detail, parent, false);
        return view;
    }
}
