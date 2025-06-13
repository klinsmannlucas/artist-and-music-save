package com.artistsave.Artist.and.Music.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrapingDates {
    public static Map<String,List<String>> getFromLastFM (String urlArtist){
        try {
            //1. Capturo html do site e crio o map.
            Document document = Jsoup.connect(urlArtist).userAgent("Mozilla").get();
            Map<String,List<String>> mapImportantDates = new HashMap<>();

            //2. Seleciono a parte do html que vou utilizar.
            Elements factbox = document.select(".factbox-item");

            //3.Percorro para cada item dentro do factox que capturei do site.
            for(Element row : factbox){

                //4. Strin key recebe a cabeça da tabela.
                String key = row.select(".factbox-heading").text();

                //5. Crio uma list para armazenar possíveis valores que vão de 1 até n.
                List<String> values = new ArrayList<>();

                //5.1 Se o cabeçalho for "Members" isso significa que é uma banda, logo tera mais de 1 artista, logo preciso armazenar mais de um valor.
                if(key.equalsIgnoreCase("Members")){

                    //5.1.1 Aqui crio uma subdivisão separando pela etiqueta <li> que já está dentro da seleção anterior.
                    Elements members = row.select("li");

                    //5.1.2 Aqui percorro cada elemento <li> que é cada membro e vou adicionando a lista de valores.
                    for(Element menber : members){
                        values.add(menber.text());
                    }

                    //5.1.3 A primeira .add está adicionando o <li> completamente, ainda não sei como fazer corretamente esse cssQuery.
                    values.removeFirst();
                }else{

                    //5.2 Caso o cabeçalho é diferente de "Member" isso significa que ele vai ter um único valor.
                    values.add(row.select("p").text());
                }

                //6 Salvo os dados em um mapa.
                mapImportantDates.put(key,values);
            }
            return mapImportantDates;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
