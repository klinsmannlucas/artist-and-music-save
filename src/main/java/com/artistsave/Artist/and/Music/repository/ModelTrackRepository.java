package com.artistsave.Artist.and.Music.repository;

import com.artistsave.Artist.and.Music.models.ModelTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelTrackRepository extends JpaRepository<ModelTrack,Long> {
    List<ModelTrack> findByArtistName(String artistCorretion);
}