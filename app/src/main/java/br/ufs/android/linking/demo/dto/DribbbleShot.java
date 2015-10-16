package br.ufs.android.linking.demo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ubiratansoares on 8/28/15.
 */
public class DribbbleShot implements Serializable {

    @SerializedName("images") Images images;
    @SerializedName("views_count") int views;
    @SerializedName("user") DribbbleUser user;
    @SerializedName("url") String webURL;
    String title;
    String description;
    int id;

    public int getId() {
        return id;
    }

    public String getImageURL() {
        return images.hidpi;
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

    static class Images implements Serializable {
        String hidpi;
    }
}
