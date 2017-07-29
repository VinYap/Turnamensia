package com.tugasakhir.turnamensiamember.View.Schedule;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Asus on 02/07/2017.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        holder.bindHolder();
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
