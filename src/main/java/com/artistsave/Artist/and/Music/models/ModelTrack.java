package com.artistsave.Artist.and.Music.models;

import com.artistsave.Artist.and.Music.models.datatrack.TrackInfo;

import java.util.ArrayList;
import java.util.List;

public class ModelTrack {
    private String name;
    private String artistName;
    private int duration;
    private List<String> tags;

    public ModelTrack() {

    }
    public ModelTrack(TrackInfo trackInfo) {
        this.name = trackInfo.track().name();
        this.artistName = trackInfo.track().artist().name();
        this.duration = Integer.parseInt(trackInfo.track().duration());

        List<String> tagsTemp = new ArrayList<>();
        trackInfo.track().toptags().tag().forEach(t -> tagsTemp.add(t.name()));
        this.tags = tagsTemp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ModelTrack{" +
                "name='" + name + '\'' +
                ", artistName='" + artistName + '\'' +
                ", duration=" + duration +
                ", tags=" + tags +
                '}';
    }
}