package com.artistsave.Artist.and.Music.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Converter
public class StringListMapConverter implements AttributeConverter<Map<String, List<String>>,String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, List<String>> map) {
        if(map == null){
            return null;
        }
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            System.err.println("Erro ao converter mapa para JSON: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Map<String, List<String>> convertToEntityAttribute(String s) {
        if(s == null || s.trim().isEmpty()){
            return Collections.emptyMap();
        }
        try {
            return objectMapper.readValue(s, new TypeReference<Map<String, List<String>>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Erro ao converter JSON para mappa: " + e.getMessage());
            return Collections.emptyMap();
        }
    }
}