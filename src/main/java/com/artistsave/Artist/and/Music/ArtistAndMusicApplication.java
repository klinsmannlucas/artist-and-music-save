package com.artistsave.Artist.and.Music;

import com.artistsave.Artist.and.Music.menuapplication.Menu;
import com.artistsave.Artist.and.Music.repository.ModelArtistRepository;
import com.artistsave.Artist.and.Music.repository.ModelTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtistAndMusicApplication implements CommandLineRunner {
	@Autowired
	ModelTrackRepository modelTrackRepository;
	@Autowired
	ModelArtistRepository modelArtistRepository;

	public static void main(String[] args) {
		SpringApplication.run(ArtistAndMusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var menu = new Menu(modelArtistRepository,modelTrackRepository);
		menu.showMenu();
	}
}
