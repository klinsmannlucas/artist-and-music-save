package com.artistsave.Artist.and.Music.models.datatrack;

import com.artistsave.Artist.and.Music.models.dataartist.Artist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Track(String name,
                    String duration,
                    Toptags toptags,
                    Artist artist) {
}