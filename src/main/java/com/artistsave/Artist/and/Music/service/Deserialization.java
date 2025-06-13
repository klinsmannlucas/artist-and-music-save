package com.artistsave.Artist.and.Music.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Deserialization {
    private final static ObjectMapper mapper = new ObjectMapper();

    public static String jsonToStringCorretion(String json,String type){
        if(type.equalsIgnoreCase("artist")){
            try {
                JsonNode rootNode = mapper.readTree(json);

                JsonNode nameNode = rootNode
                        .path("corrections")
                        .path("correction")
                        .path("artist")
                        .path("name");

                return nameNode.asText();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        if(type.equalsIgnoreCase("track")){
            try {
                JsonNode rootNode = mapper.readTree(json);

                JsonNode nameNode = rootNode
                        .path("corrections")
                        .path("correction")
                        .path("track")
                        .path("name");

                return nameNode.asText();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return "";
    }
    public static <T> T jsonToObject (String json, Class<T> tClass){
        try {
            return mapper.readValue(json,tClass);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}