package com.tugasakhir.turnamensiamember.View.Team;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamMemberFragment extends Fragment {
    private RecyclerView mMemberRV;

    private List<Member> mMembers;

    public TeamMemberFragment() {
        // Required empty public constructor
    }

    public static TeamMemberFragment newInstance(List<Member> members) {
        TeamMemberFragment fragment = new TeamMemberFragment();
        fragment.mMembers = members;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_member, container, false);

        mMemberRV = (RecyclerView) view.findViewById(R.id.team_member_rv);
        mMemberRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mMemberRV.setHasFixedSize(true);

        TeamMemberAdapter adapter = new TeamMemberAdapter(mMembers);
        mMemberRV.setAdapter(adapter);

        return view;
    }
}
