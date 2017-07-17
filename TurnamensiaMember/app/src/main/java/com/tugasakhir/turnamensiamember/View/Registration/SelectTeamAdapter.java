package com.tugasakhir.turnamensiamember.View.Registration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 03/07/2017.
 */

public class SelectTeamAdapter extends RecyclerView.Adapter<SelectTeamViewHolder> {

    @Override
    public SelectTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_team, parent, false);
        return new SelectTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectTeamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
