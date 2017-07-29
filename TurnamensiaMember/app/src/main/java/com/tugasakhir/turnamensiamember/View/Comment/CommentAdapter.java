package com.tugasakhir.turnamensiamember.View.Comment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugasakhir.turnamensiamember.Model.Basic.Comment;
import com.tugasakhir.turnamensiamember.R;

import java.util.List;

/**
 * Created by Andrianto on 7/29/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {
    private List<Comment> mComments;

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment mComment = mComments.get(position);
        holder.bindHolder(mComment);
    }

    @Override
    public int getItemCount() {
        if (mComments != null) {
            return mComments.size();
        } else {
            return 0;
        }
    }

    public void setmComments(List<Comment> mComments) {
        this.mComments = mComments;
    }

    public void addtoTopofList(Comment mComment) {
        this.mComments.add(0, mComment);
    }
}
