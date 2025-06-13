package com.artistsave.Artist.and.Music.menuapplication;

import com.artistsave.Artist.and.Music.models.ModelArtist;
import com.artistsave.Artist.and.Music.models.ModelTrack;
import com.artistsave.Artist.and.Music.repository.ModelArtistRepository;
import com.artistsave.Artist.and.Music.repository.ModelTrackRepository;
import com.artistsave.Artist.and.Music.service.LastFM;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static ModelTrackRepository modelTrackRepository;
    private static ModelArtistRepository modelArtistRepository;

    public Menu(ModelArtistRepository modelArtistRepository, ModelTrackRepository modelTrackRepository) {
        Menu.modelTrackRepository = modelTrackRepository;
        Menu.modelArtistRepository = modelArtistRepository;
    }

    public static void showMenu(){
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
                case 3:
                    showAllTrack();
                    break;
                case 4:
                    showTrackByArtist();
                    break;
                case 5:
                    showDataFromArtist();
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void registerArtist( ){
        System.out.println("Digite o nome do artista desejado: ");
        var choiceArtist = sc.nextLine();
        var artistCorretion = LastFM.getArtistCorretion(choiceArtist);
        var exists = modelArtistRepository.existsByName(artistCorretion);

        if(exists) {
            System.out.println("Artista já cadastrado no banco de dados.");
        }else {
            var artistInfo = LastFM.getArtistInfo(artistCorretion);
            var modelArtist = new ModelArtist(artistInfo);
            System.out.println(modelArtist);
            modelArtistRepository.save(modelArtist);
            System.out.println("Artista salvo com sucesso.");
            System.out.println("Artista já cadastrado no banco de dados.");
        }
    }

    private static void registerTrack(){
        System.out.println("Digite o nome do artista da música desejada: ");
        var choiceArtist = sc.nextLine();
        var artistCorretion = LastFM.getArtistCorretion(choiceArtist);
        var exists = modelArtistRepository.existsByName(artistCorretion);

        if(exists){
            System.out.println("Digite o nome da música desejada: ");
            var choiceTrack = sc.nextLine();
            var trackCorretion = LastFM.getTrackCorretion(artistCorretion,choiceTrack);
            var trackInfo = LastFM.getTrackInfo(trackCorretion,artistCorretion);
            //Armazeno informações de música em modeltrack.
            var modelTrack = new ModelTrack(trackInfo);
            //Capturo do banco de dados o modelArtist.
            var modelArtist = modelArtistRepository.findByName(artistCorretion);
            //Salvo o modelArtist relacionado a essa modelTrack.
            modelTrack.setArtist(modelArtist);
            //Armazeno no banco de dados a track.
            modelTrackRepository.save(modelTrack);

            System.out.println("Música salva com sucesso.");
        }else {
            System.out.println("Artista não encontrado no banco de dados.");
        }
    }

    private static void showAllTrack (){
        List<ModelTrack> modelTrackList = modelTrackRepository.findAll();
        modelTrackList.forEach(t -> System.out.println("Nome: " + t.getName() + " Artist: " + t.getArtist().getName()));
    }

    private static void showTrackByArtist(){
        System.out.println("Digite o nome do artista que deseja ver as músicas: ");
        var choiceArtist = sc.nextLine();
        var artistCorretion = LastFM.getArtistCorretion(choiceArtist);
        var exist = modelArtistRepository.existsByName(artistCorretion);
        if(exist) {
            List<ModelTrack> modelTrackList = modelTrackRepository.findByArtistName(artistCorretion);
            modelTrackList.forEach(t ->System.out.println("Nome: " + t.getName() + " Artist: " + t.getArtist().getName()));
        }else{
            System.out.println("Artista não encontrado no banco de dados.");
        }
    }
    private static void showDataFromArtist(){

    }
}
