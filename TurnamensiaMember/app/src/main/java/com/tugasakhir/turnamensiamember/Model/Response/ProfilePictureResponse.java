package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Response;

/**
 * Created by alvin on 7/20/2017.
 */

public class ProfilePictureResponse extends Response {
    private String file_path;

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
