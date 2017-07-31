package com.tugasakhir.turnamensiamember.View.Registration;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Asus on 10/07/2017.
 */

public class SelectPlayerViewHolder extends RecyclerView.ViewHolder {
    private ImageView mPhotoIV;
    private TextView mNameTV;
    private CheckBox mSelectCB;

    private Long id;
    private SelectPlayerAdapter adapter;

    private int position;

    public SelectPlayerViewHolder(final View itemView, final SelectPlayerAdapter adapter) {
        super(itemView);
        this.adapter = adapter;

        mPhotoIV = (ImageView) itemView.findViewById(R.id.player_image);
        mNameTV = (TextView) itemView.findViewById(R.id.player_name);
        mSelectCB = (CheckBox) itemView.findViewById(R.id.player_check);

        mSelectCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.getMembers().get(position).setSelected(isChecked);
                adapter.updateAction(itemView.getContext());
            }
        });
    }

    public void bindHolder(Member member, int position) {
        this.position = position;
        id = member.getId();
        mNameTV.setText(member.getName());
        mSelectCB.setChecked(member.isSelected());
        Picasso.with(itemView.getContext()).load(member.getPicture_file_name()).into(mPhotoIV);
    }
}
