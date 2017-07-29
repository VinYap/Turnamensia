package com.tugasakhir.turnamensiamember.Model.Socket;

import com.tugasakhir.turnamensiamember.Model.Basic.Comment;

/**
 * Created by Andrianto on 7/29/2017.
 */

public class CommentUpdateEvent {
    private Comment comment_mobile;

    public Comment getComment_mobile() {
        return comment_mobile;
    }

    public void setComment_mobile(Comment comment_mobile) {
        this.comment_mobile = comment_mobile;
    }
}
