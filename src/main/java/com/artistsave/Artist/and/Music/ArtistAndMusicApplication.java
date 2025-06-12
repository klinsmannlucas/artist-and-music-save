package com.artistsave.Artist.and.Music;

import com.artistsave.Artist.and.Music.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtistAndMusicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ArtistAndMusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main.menu();
	}
}
