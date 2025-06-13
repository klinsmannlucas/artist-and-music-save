package com.artistsave.Artist.and.Music.main;

import com.artistsave.Artist.and.Music.models.ModelArtist;
import com.artistsave.Artist.and.Music.models.ModelTrack;
import com.artistsave.Artist.and.Music.service.LastFM;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void menu (){
        var choice = -1;
        var menu = """
                1- Cadastrar artistas
                2- Cadastrar músicas
                3- Listar músicas
                4- Buscar músicas por artistas
                5- Pesquisar dados sobre um artista
                
                9- Sair
                """;
        while (choice != 9){
            System.out.println(menu);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    registerArtist();
                    break;
                case 2:
                    registerTrack();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void registerArtist(){
        System.out.println("Digite o nome do artista desejado: ");
        var choiceArtist = sc.nextLine();
        var artistInfo = LastFM.getArtistInfo(choiceArtist);
        var modelArtist = new ModelArtist(artistInfo);
        //Implementar um armazenamento no banco de dados.
        System.out.println(modelArtist);
    }

    private static void registerTrack(){
        System.out.println("Digite o nome do artista da música desejada: ");
        var choiceArtist = sc.nextLine();
        //Implementar uma chegagem no banco de dados.

        System.out.println("Digite o nome da música desejada: ");
        var choiceTrack = sc.nextLine();
        var trackInfo = LastFM.getTrackInfo(choiceTrack,choiceArtist);
        var moldelTrack = new ModelTrack(trackInfo);
        System.out.println(moldelTrack);
    }
}
