package com.klef.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.dev.entity.Sports;

@Repository
public interface SportsRepository extends JpaRepository<Sports, Integer> 
{
    Sports findByEmail(String email);
    Sports findByContact(String contact);
}
