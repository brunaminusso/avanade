package com.ada.avanade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ada.avanade.model.Battle;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {
    
}