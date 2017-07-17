package com.tugasakhir.turnamensiamember.View.Registration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 10/07/2017.
 */

public class SelectPlayerAdapter extends RecyclerView.Adapter<SelectPlayerViewHolder> {
    @Override
    public SelectPlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_player, parent, false);
        return new SelectPlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectPlayerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
