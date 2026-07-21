package com.example.demo.controller;

import com.example.demo.dto.HikeDTO;
import com.example.demo.model.Hike;
import com.example.demo.service.HikeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hike")
public class HikeController {

    private final HikeService hikeService;

    @Autowired
    public HikeController(HikeService hikeService) {
        this.hikeService = hikeService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllHikes() {
        List<Hike> hikes =  hikeService.getAllHikes();
        return new ResponseEntity<>(hikes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHikeById(@PathVariable Long id) {
        Hike hike = hikeService.getHikeById(id);
        return new ResponseEntity<>(hike, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> createHike (@Valid @RequestBody HikeDTO hikeDto) {
        Hike savedHike = hikeService.createHike(hikeDto);
        return new ResponseEntity<>(savedHike, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHike(@PathVariable Long id, @Valid @RequestBody HikeDTO hikeDto) {
        Hike updatedHike = hikeService.updateHike(id, hikeDto);
        return new ResponseEntity<>(updatedHike, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHike(@PathVariable Long id) {
        hikeService.deleteHike(id);
        return new ResponseEntity<>("Drumeția a fost ștearsă cu succes.", HttpStatus.OK);
    }
}
