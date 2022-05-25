package com.example.moviestreaming.Model;

public class Episode {

    private String id, id_season, name, detail, link_img, time, link_play_episode;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId_season(String id_season) {
        this.id_season = id_season;
    }

    public String getId_season() {
        return id_season;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink_play_episode() {
        return link_play_episode;
    }

    public void setLink_play_episode(String link_play_episode) {
        this.link_play_episode = link_play_episode;
    }
}
