package com.example.demo.service;

import com.example.demo.dto.HikeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Hike;
import com.example.demo.repository.HikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class HikeService {

    private static final String NOT_FOUND_PREFIX = "Drumeția cu ID-ul ";
    private static final String NOT_FOUND_SUFFIX = " nu a fost găsită.";

    private final HikeRepository hikeRepository;

    @Autowired
    public HikeService(HikeRepository hikeRepository) {
        this.hikeRepository = hikeRepository;
    }

    public List<Hike> getAllHikes() {
        return hikeRepository.findAll();
    }

    public Hike getHikeById(Long id) {
        return hikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_PREFIX + id + NOT_FOUND_SUFFIX));
    }

    public Hike createHike(HikeDTO hikeDto) {
        Hike hike = new Hike();
        hike.setStart(hikeDto.getStart());
        hike.setEnd(hikeDto.getEnd());
        hike.setObjectives(hikeDto.getObjectives());
        hike.setDate(hikeDto.getDate());
        hike.setDif(hikeDto.getDif());
        hike.setPhoto(hikeDto.getPhoto());
        return hikeRepository.save(hike);
    }

    public Hike updateHike(Long id, HikeDTO hikeDto) {
        Hike existingHike = hikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_PREFIX + id + NOT_FOUND_SUFFIX));
        existingHike.setStart(hikeDto.getStart());
        existingHike.setEnd(hikeDto.getEnd());
        existingHike.setObjectives(hikeDto.getObjectives());
        existingHike.setDate(hikeDto.getDate());
        existingHike.setDif(hikeDto.getDif());
        existingHike.setPhoto(hikeDto.getPhoto());
        return existingHike;
    }

    public void deleteHike(Long id) {
        Hike hikeToDelete = hikeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_PREFIX + id + NOT_FOUND_SUFFIX));
        hikeRepository.deleteById(hikeToDelete.getId());

    }
}