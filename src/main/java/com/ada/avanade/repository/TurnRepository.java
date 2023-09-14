package com.ada.avanade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ada.avanade.model.Turn;

public interface TurnRepository extends JpaRepository<Turn, Long> {
    
}
