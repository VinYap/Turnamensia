package com.tugasakhir.turnamensiamember.View.MyRegistration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 29/07/2017.
 */

public class MyRegistrationAdapter extends RecyclerView.Adapter<MyRegistrationViewHolder> {
    @Override
    public MyRegistrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_registration, parent, false);
        return new MyRegistrationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRegistrationViewHolder holder, int position) {
        holder.bindHolder();
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
