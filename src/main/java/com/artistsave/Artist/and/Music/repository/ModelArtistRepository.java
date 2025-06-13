package com.artistsave.Artist.and.Music.repository;

import com.artistsave.Artist.and.Music.models.ModelArtist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelArtistRepository extends JpaRepository<ModelArtist, Long> {
    boolean existsByName(String autoCorretion);

    ModelArtist findByName(String autoCorretion);
}