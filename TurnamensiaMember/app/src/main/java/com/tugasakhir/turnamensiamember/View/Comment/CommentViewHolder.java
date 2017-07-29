package com.tugasakhir.turnamensiamember.View.Comment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tugasakhir.turnamensiamember.Model.API.ConnectionAPI;
import com.tugasakhir.turnamensiamember.Model.Basic.Comment;
import com.tugasakhir.turnamensiamember.R;

/**
 * Created by Andrianto on 7/29/2017.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {
    private ImageView mCommentMemberIV;
    private TextView mCommentMemberTV;
    private TextView mCommentDetailTV;

    public CommentViewHolder(View itemView) {
        super(itemView);

        mCommentMemberIV = (ImageView) itemView.findViewById(R.id.comment_member_image);
        mCommentMemberTV = (TextView) itemView.findViewById(R.id.comment_member_name);
        mCommentDetailTV = (TextView) itemView.findViewById(R.id.comment_detail);
    }

    public void bindHolder(Comment comment) {
        if (comment.getMember().getPicture_file_name() != null) {
            String image = ConnectionAPI.getBaseUrl() + "/storage/member/" + comment.getMember().getPicture_file_name();
            Picasso.with(itemView.getContext()).load(image).into(mCommentMemberIV);
        }
        mCommentMemberTV.setText(comment.getMember().getName());
        mCommentDetailTV.setText(comment.getDetail());
    }
}
