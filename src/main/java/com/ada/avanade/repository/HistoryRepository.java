package com.ada.avanade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ada.avanade.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    
}

