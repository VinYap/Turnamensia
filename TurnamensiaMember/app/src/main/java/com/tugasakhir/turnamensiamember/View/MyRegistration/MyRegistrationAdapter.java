package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.TournamentRegistration;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyRegistrationAdapter extends RecyclerView.Adapter<MyRegistrationViewHolder> {
    private List<TournamentRegistration> tournamentRegistrations;

    private static final int TYPE_NOT_ACCEPTED = 0;
    private static final int TYPE_ACCEPTED = 1;

    public MyRegistrationAdapter(List<TournamentRegistration> tournamentRegistrations) {
        this.tournamentRegistrations = tournamentRegistrations;
    }

    @Override
    public MyRegistrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_registration, parent, false);
        switch (viewType) {
            case TYPE_NOT_ACCEPTED :
                return new MyRegistrationViewHolder(view, false);
            case TYPE_ACCEPTED :
                return new MyRegistrationViewHolder(view, true);
            default :
                return null;
        }
    }

    @Override
    public void onBindViewHolder(MyRegistrationViewHolder holder, int position) {
        TournamentRegistration tournamentRegistration = tournamentRegistrations.get(position);
        holder.bindHolder(tournamentRegistration);
    }

    @Override
    public int getItemCount() {
        if (tournamentRegistrations != null) return tournamentRegistrations.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (tournamentRegistrations != null) {
            if (tournamentRegistrations.get(position).getStatus().equals("Accepted")) return TYPE_ACCEPTED;
            return TYPE_NOT_ACCEPTED;
        }
        return TYPE_ACCEPTED;
    }
}
