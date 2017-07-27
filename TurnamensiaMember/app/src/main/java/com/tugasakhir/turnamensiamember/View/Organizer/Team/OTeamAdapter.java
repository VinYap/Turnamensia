package com.tugasakhir.turnamensiamember.View.Organizer.Team;

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
 * Created by Asus on 21/06/2017.
 */

public class OTeamAdapter extends BaseAdapter {
    private Context mContext;
    private List<Member> members;
    private LayoutInflater mInflater;

    public OTeamAdapter(Context context, List<Member> members) {
        this.mContext = context;
        this.members = members;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View view = mInflater.inflate(R.layout.item_organizer_team, parent, false);

        ImageView userPhoto = (ImageView) view.findViewById(R.id.team_user_image);
        TextView username = (TextView) view.findViewById(R.id.team_username);
        ImageView userStatus = (ImageView) view.findViewById(R.id.team_user_status);

        Member member = (Member) getItem(position);

        Picasso.with(view.getContext()).load(member.getImage()).into(userPhoto);
        username.setText(member.getName());
        if (member.getAttendances_status() == 1) {
            userStatus.setImageResource(R.drawable.ic_check_green_32dp);
        } else {
            userStatus.setImageResource(R.drawable.ic_access_time_blue_32dp);
        }

        return view;
    }
}
