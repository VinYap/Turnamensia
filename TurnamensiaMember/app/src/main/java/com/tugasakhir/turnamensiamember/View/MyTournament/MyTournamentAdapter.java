package com.tugasakhir.turnamensiamember.View.MyTournament;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.MyTournament;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyTournamentAdapter extends RecyclerView.Adapter<MyTournamentViewHolder> {
    private List<MyTournament> myTournaments;

    private static final int TYPE_IN_PROGRESS = 0;
    private static final int TYPE_COMPLETED = 1;

    public MyTournamentAdapter(List<MyTournament> myTournaments) {
        this.myTournaments = myTournaments;
    }

    @Override
    public MyTournamentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_tournament, parent, false);
        switch (viewType) {
            case TYPE_IN_PROGRESS :
                return new MyTournamentViewHolder(view, false);
            case TYPE_COMPLETED :
                return new MyTournamentViewHolder(view, true);
            default :
                return null;
        }
    }

    @Override
    public void onBindViewHolder(MyTournamentViewHolder holder, int position) {
        MyTournament myTournament = myTournaments.get(position);
        holder.bindHolder(myTournament);
    }

    @Override
    public int getItemCount() {
        if (myTournaments != null) return myTournaments.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (myTournaments != null) {
            if (myTournaments.get(position).getQr_identifier() == null) return TYPE_COMPLETED;
            return TYPE_IN_PROGRESS;
        }
        return TYPE_COMPLETED;
    }
}
