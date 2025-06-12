package com.artistsave.Artist.and.Music.models.dataartist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Tag (String name,
                   String url){
}