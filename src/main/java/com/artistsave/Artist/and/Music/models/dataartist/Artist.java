package com.artistsave.Artist.and.Music.models.dataartist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Artist(String name,
                     String url,
                     Tags tags,
                     Bio bio) {
}