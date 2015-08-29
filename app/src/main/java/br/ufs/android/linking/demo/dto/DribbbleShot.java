package br.ufs.android.linking.demo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ubiratansoares on 8/28/15.
 */
public class DribbbleShot implements Serializable {

    @SerializedName("image_url") String shotURL;
    @SerializedName("views_count") int views;
    @SerializedName("player") DribbbleUser user;
    @SerializedName("url")String webURL;
    String title;
    String description;
    int id;

    public int getId() {
        return id;
    }

    public String getShotURL() {
        return shotURL;
    }

    public String getTitle() {
        return title;
    }

    public int getViews() {
        return views;
    }

    public String getWebURL() {
        return webURL;
    }

    public DribbbleUser getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }
}
