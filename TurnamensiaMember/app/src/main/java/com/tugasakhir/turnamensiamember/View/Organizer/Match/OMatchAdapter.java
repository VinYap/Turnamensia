package com.tugasakhir.turnamensiamember.View.Organizer.Match;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.OrganizerMatch;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 18/06/2017.
 */

public class OMatchAdapter extends RecyclerView.Adapter<OMatchViewHolder> {
    private List<OrganizerMatch> mMatchs;

    public OMatchAdapter(List<OrganizerMatch> match) {
        this.mMatchs = match;
    }

    @Override
    public OMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organizer_match, parent, false);
        return new OMatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OMatchViewHolder holder, int position) {
        OrganizerMatch match = mMatchs.get(position);
        holder.bindHolder(position, match);
    }

    @Override
    public int getItemCount() {
        return mMatchs.size();
    }
}
