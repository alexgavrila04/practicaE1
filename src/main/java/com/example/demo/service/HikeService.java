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
            existingHike.setStart(hike.getStart());
            existingHike.setEnd(hike.getEnd());
            existingHike.setObjectives(hike.getObjectives());
            existingHike.setDate(hike.getDate());
            existingHike.setDif(hike.getDif());
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
