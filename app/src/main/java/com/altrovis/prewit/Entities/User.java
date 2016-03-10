package com.altrovis.prewit.Entities;

/**
 * Created by ricki on 3/8/2016.
 */
public class User {
    private String Nickname;
    private String UrlProfilPicture;

    public User(String nickname, String urlProfilPicture) {
        Nickname = nickname;
        UrlProfilPicture = urlProfilPicture;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getUrlProfilPicture() {
        return UrlProfilPicture;
    }

    public void setUrlProfilPicture(String urlProfilPicture) {
        UrlProfilPicture = urlProfilPicture;
    }
}
