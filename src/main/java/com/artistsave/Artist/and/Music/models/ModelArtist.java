package com.artistsave.Artist.and.Music.models;

import com.artistsave.Artist.and.Music.models.dataartist.ArtistInfo;
import com.artistsave.Artist.and.Music.service.ScrapingDates;
import com.artistsave.Artist.and.Music.service.StringListMapConverter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "artist")
public class ModelArtist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String urlArtist;
    private List<String> tags;

    @Column(columnDefinition = "TEXT")
    private String summary;
    /*index:
    0 - dia do nascimento do artista ou dia da criação da banda
    1 - local de nascimento do artista ou da banda
    2 - morte do artista ou todos os membros da banda
    JPA não conseguiu salvar em automaticamente entou vou salvar em json
     */
    @Convert(converter = StringListMapConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String,List<String>> imporantDates;

    @OneToMany(mappedBy = "artist")
    private List<ModelTrack> trackList = new ArrayList<>();

    public ModelArtist(){

    }
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

    public List<ModelTrack> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<ModelTrack> trackList) {
        trackList.forEach(t -> t.setArtist(this));
        this.trackList = trackList;
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