package br.ufs.android.linking.demo.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ubiratansoares on 8/28/15.
 */
public class DribbbleUser implements Serializable {

    @SerializedName("followers_count") int followers;
    @SerializedName("likes_received_count") int likes;
    @SerializedName("avatar_url") String avatarURL;
    int id;
    String name;
    String location;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getFollowers() {
        return followers;
    }

    public int getLikes() {
        return likes;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}

