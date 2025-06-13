package com.artistsave.Artist.and.Music.models;

import com.artistsave.Artist.and.Music.models.dataartist.ArtistInfo;
import com.artistsave.Artist.and.Music.service.ScrapingDates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelArtist {
    private long id;
    private String name;
    private String urlArtist;
    private List<String> tags;
    private String summary;
    /*index:
    0 - born
    1 - atual age
    2 - die(if alive this will be null)
     */
    private Map<String,List<String>> imporantDates;

    public ModelArtist(ArtistInfo a){
        this.name = a.artist().name();
        this.urlArtist = a.artist().url()+"/+wiki";
        this.summary = a.artist().bio().summary();

        List<String> tagsTemp = new ArrayList<>();
        a.artist().tags().tag().forEach(t -> tagsTemp.add(t.name()));
        this.tags = tagsTemp;

        this.imporantDates = ScrapingDates.getFromLastFM(this.urlArtist);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUrlArtist() {
        return urlArtist;
    }

    public void setUrlArtist(String urlArtist) {
        this.urlArtist = urlArtist;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Map<String,List<String>> getImporantDates() {
        return imporantDates;
    }

    public void setImporantDates( Map<String,List<String>> imporantDates) {
        this.imporantDates = imporantDates;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", urlArtist='" + urlArtist + '\'' +
                ", tags=" + tags +
                ", summary='" + summary + '\'' +
                ", imporantDates=" + imporantDates +
                '}';
    }
}