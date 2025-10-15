package com.klef.dev.service;

import java.util.List;
import com.klef.dev.entity.Sports;

public interface SportsService {
    Sports addSports(Sports sports);
    List<Sports> getAllSports();
    Sports getSportsById(int id);
    Sports updateSports(Sports sports);
    void deleteSportsById(int id);
}
