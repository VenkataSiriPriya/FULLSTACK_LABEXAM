package com.klef.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.dev.entity.Sports;
import com.klef.dev.repository.SportsRepository;

@Service
public class SportsServiceImpl implements SportsService {

    @Autowired
    private SportsRepository sportsRepository;

    @Override
    public Sports addSports(Sports sports) {
        return sportsRepository.save(sports);
    }

    @Override
    public List<Sports> getAllSports() {
        return sportsRepository.findAll();
    }

    @Override
    public Sports getSportsById(int id) {
        Optional<Sports> opt = sportsRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Sports updateSports(Sports sports) {
        return sportsRepository.save(sports);
    }

    @Override
    public void deleteSportsById(int id) {
        sportsRepository.deleteById(id);
    }
}
