package com.example.demo.controller;

import com.example.demo.model.Hike;
import com.example.demo.service.HikeService;
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
        try {
            List<Hike> hikes =  hikeService.getAllHikes();
            return new ResponseEntity<>(hikes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la preluarea listei: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHikeById(@PathVariable Long id) {
        try {
            Hike hike = hikeService.getHikeById(id);
            if (hike == null) {
                return new ResponseEntity<>("Drumetia cu ID-ul " + id + " nu a fost gasita.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(hike, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la cautare: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createHike (@RequestBody Hike hike) {
        try {
            Hike savedHike = hikeService.createHike(hike);
            return new ResponseEntity<>(savedHike, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>("Nu am putut salva drumeția. Verificați datele: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHike(@PathVariable Long id, @RequestBody Hike hike) {
        try {
            Hike updatedHike = hikeService.updateHike(id, hike);
            if (updatedHike == null) {
                return new ResponseEntity<>("Drumeția cu ID-ul " + id + " nu există.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedHike, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Nu am putut actualiza drumeția: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHike(@PathVariable Long id) {
        try {
            hikeService.deleteHike(id);
            return new ResponseEntity<>("Drumeția a fost ștearsă cu succes.", HttpStatus.OK);
        } catch (Exception _) {
            return new ResponseEntity<>("Eroare la ștergere. Posibil ca ID-ul să nu existe.", HttpStatus.NOT_FOUND);
        }
    }

}
