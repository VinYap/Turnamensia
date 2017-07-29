package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Comment;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;

import java.util.List;

/**
 * Created by Andrianto on 7/29/2017.
 */

public class CommentResponse extends Response {
    private List<Comment> dota2_live_match_comments;

    public List<Comment> getDota2_live_match_comments() {
        return dota2_live_match_comments;
    }

    public void setDota2_live_match_comments(List<Comment> dota2_live_match_comments) {
        this.dota2_live_match_comments = dota2_live_match_comments;
    }
}
