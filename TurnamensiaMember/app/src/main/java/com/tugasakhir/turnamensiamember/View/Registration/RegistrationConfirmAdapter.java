package com.tugasakhir.turnamensiamember.View.Registration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 29/07/2017.
 */

public class RegistrationConfirmAdapter extends BaseAdapter {
    private Context context;
    private List<Member> members;
    private LayoutInflater inflater;

    public RegistrationConfirmAdapter(Context context, List<Member> members) {
        this.context = context;
        this.members = members;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_player, parent, false);

        ImageView photoIV = (ImageView) view.findViewById(R.id.player_image);
        TextView nameTV = (TextView) view.findViewById(R.id.player_name);

        Member member = (Member) getItem(position);

        Picasso.with(view.getContext()).load(member.getPicture_file_name()).into(photoIV);
        nameTV.setText(member.getName());

        return view;
    }
}
