package com.tugasakhir.turnamensiamember.View.Account;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 02/07/2017.
 */

public class AccountTeamAdapter extends RecyclerView.Adapter<AccountTeamViewHolder> {

    public AccountTeamAdapter() {

    }

    @Override
    public AccountTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account_team, parent, false);
        return new AccountTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountTeamViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
