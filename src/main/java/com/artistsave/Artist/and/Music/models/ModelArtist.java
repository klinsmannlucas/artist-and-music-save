package com.artistsave.Artist.and.Music.models;

import com.artistsave.Artist.and.Music.models.dataartist.ArtistInfo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelArtist {
    private long id;
    private String name;
    private String urlArtist;
    private Map<String,String> tags;
    private String summary;
    /*index:
    0 - born
    1 - atual age
    2 - die(if alive this will be null)
     */
    private List<LocalDate> imporantDates;

    public ModelArtist(ArtistInfo a){
        this.name = a.artist().name();
        this.urlArtist = a.artist().url()+"/+wiki";
        this.summary = a.artist().bio().summary();

        Map<String,String> mapNameUrl = new HashMap<>();
        a.artist().tags().tag().forEach(t -> mapNameUrl.put(t.name(),t.url()));

        this.tags = mapNameUrl;
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

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
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

    public List<LocalDate> getImporantDates() {
        return imporantDates;
    }

    public void setImporantDates(List<LocalDate> imporantDates) {
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