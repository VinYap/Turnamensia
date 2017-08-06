package com.tugasakhir.turnamensiamember.View.Team;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

import static com.tugasakhir.turnamensiamember.View.Account.AccountTeamViewHolder.TEAM_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamMemberFragment extends Fragment {
    private RecyclerView mMemberRV;
    private FloatingActionButton mInviteFAB;

    private List<Member> mMembers;
    private Long mTeamId;
    private Boolean mIsLeader;

    public TeamMemberFragment() {
        // Required empty public constructor
    }

    public static TeamMemberFragment newInstance(List<Member> members, Long mTeamId, Boolean isLeader) {
        TeamMemberFragment fragment = new TeamMemberFragment();
        fragment.mMembers = members;
        fragment.mTeamId = mTeamId;
        fragment.mIsLeader = isLeader;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_member, container, false);

        mInviteFAB = (FloatingActionButton) view.findViewById(R.id.invite_fab);
        mMemberRV = (RecyclerView) view.findViewById(R.id.team_member_rv);

        mInviteFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), InviteMemberActivity.class);
                intent.putExtra(TEAM_KEY, mTeamId);
                startActivity(intent);
            }
        });

        if (mIsLeader == false) {
            mInviteFAB.setVisibility(View.GONE);
        }

        mMemberRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mMemberRV.setHasFixedSize(true);

        TeamMemberAdapter adapter = new TeamMemberAdapter(mMembers, mTeamId, mIsLeader);
        mMemberRV.setAdapter(adapter);

        return view;
    }
}
