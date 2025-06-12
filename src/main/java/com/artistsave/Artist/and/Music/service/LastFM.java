package com.artistsave.Artist.and.Music.service;

import com.artistsave.Artist.and.Music.models.dataartist.ArtistInfo;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class LastFM {
    private static final String URL = "http://ws.audioscrobbler.com/2.0/?";
    private static final String API_KEY = System.getenv("LASTFM_API_KEY");

    public static ArtistInfo getArtistInfo (String artistName){
        String codedUrl = URL+
                "method=artist.getinfo"+
                "&format=json"+
                "&autocorret=1"+
                "&artist="+ URLEncoder.encode(artistName, StandardCharsets.UTF_8)+
                "&api_key="+API_KEY;

        String json = LastFM.getJson(codedUrl);
        return Deserialization.jsonToObject(json,ArtistInfo.class);
    }

    private static String getJson (String codedUrl){
        var client = HttpClient.newHttpClient();
        var resquest = HttpRequest.newBuilder()
                .uri(URI.create(codedUrl))
                .header("User-Agent",System.getenv("USER_AGENT"))
                .GET()
                .build();
        try {
            var response = client.send(resquest, HttpResponse.BodyHandlers.ofString());
            Thread.sleep(1000);
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}