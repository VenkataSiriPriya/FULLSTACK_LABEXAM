package com.klef.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.dev.entity.Sports;
import com.klef.dev.service.SportsService;

@RestController
@RequestMapping("/sportsapi/")
@CrossOrigin(origins = "*")
public class SportsController {

    @Autowired
    private SportsService sportsService;

    @GetMapping("/")
    public String home() {
        return "Sports Management API Running Successfully";
    }

    // Add Sports
    @PostMapping("/add")
    public ResponseEntity<Sports> addSports(@RequestBody Sports sports) {
        Sports savedSports = sportsService.addSports(sports);
        return new ResponseEntity<>(savedSports, HttpStatus.CREATED);
    }

    // Get All Sports
    @GetMapping("/all")
    public ResponseEntity<List<Sports>> getAllSports() {
        List<Sports> sportsList = sportsService.getAllSports();
        return new ResponseEntity<>(sportsList, HttpStatus.OK);
    }

    // Get Sports by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSportsById(@PathVariable int id) {
        Sports sports = sportsService.getSportsById(id);
        if (sports != null) {
            return new ResponseEntity<>(sports, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Sports record with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Update Sports
    @PutMapping("/update")
    public ResponseEntity<?> updateSports(@RequestBody Sports sports) {
        Sports existing = sportsService.getSportsById(sports.getId());
        if (existing != null) {
            Sports updatedSports = sportsService.updateSports(sports);
            return new ResponseEntity<>(updatedSports, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Sports record with ID " + sports.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Delete Sports by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSports(@PathVariable int id) {
        Sports existing = sportsService.getSportsById(id);
        if (existing != null) {
            sportsService.deleteSportsById(id);
            return new ResponseEntity<>("Sports record with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Sports record with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
