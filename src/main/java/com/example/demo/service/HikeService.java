package com.example.demo.service;

import com.example.demo.model.Hike;
import com.example.demo.repository.HikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HikeService {

    private final HikeRepository hikeRepository;

    @Autowired
    public HikeService(HikeRepository hikeRepository) {
        this.hikeRepository = hikeRepository;
    }

    public List<Hike> getAllHikes() {
        return hikeRepository.findAll();
    }

    public Hike getHikeById(Long id) {
        return hikeRepository.findById(id).orElse(null);
    }

    public Hike createHike(Hike hike) {
        return hikeRepository.save(hike);
    }

    public Hike updateHike(Long id, Hike hike) {
        Hike existingHike = hikeRepository.findById(id).orElse(null);
        if (existingHike != null) {
            existingHike.setPhoto(hike.getPhoto());
            return hikeRepository.save(existingHike);
        } else {
            return null;
        }
    }

    public void deleteHike(Long id) {
        hikeRepository.deleteById(id);
    }
}
