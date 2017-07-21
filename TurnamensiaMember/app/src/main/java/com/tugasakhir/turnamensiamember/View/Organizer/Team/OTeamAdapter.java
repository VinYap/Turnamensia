package com.tugasakhir.turnamensiamember.View.Organizer.Team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Asus on 21/06/2017.
 */

public class OTeamAdapter extends BaseAdapter {
    private Context mContext;
    private List<Team> teams;
    private LayoutInflater mInflater;

    public OTeamAdapter(Context context, List<Team> teams) {
        this.mContext = context;
        this.teams = teams;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public Object getItem(int position) {
        return teams.get(position);
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
        ImageView userProfile = (ImageView) view.findViewById(R.id.team_user_profile);
        ImageView userStatus = (ImageView) view.findViewById(R.id.team_user_status);

        Team team = (Team) getItem(position);

//        userPhoto.setImageResource(team.getUserPhoto());
//        username.setText(team.getUsername());
//        userProfile.setImageResource(team.getUserProfile());
//        userStatus.setImageResource(team.getUserStatus());

        return view;
    }
}
