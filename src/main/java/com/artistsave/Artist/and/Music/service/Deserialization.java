package com.artistsave.Artist.and.Music.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Deserialization {
    public static <T> T jsonToObject (String json, Class<T> tClass){
        var mapper = new ObjectMapper();
        try {
            return mapper.readValue(json,tClass);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}