package br.ufs.android.linking.demo.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ubiratansoares on 8/28/15.
 */

public class ShotComment {

    @SerializedName("body") String comment;
    @SerializedName("user") DribbbleUser commenter;

    public String getComment() {
        return comment;
    }

    public DribbbleUser getCommenter() {
        return commenter;
    }
}
