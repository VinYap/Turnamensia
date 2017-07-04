package com.tugasakhir.turnamensiamember.View.Comment;

import android.os.Bundle;

import com.tugasakhir.turnamensiamember.R;
import com.tugasakhir.turnamensiamember.View.BaseActivity;

public class CommentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_comment, mBaseLayout);

        showUpCaretMenu();
    }
}
