package com.example.smecalculator.repository;

import com.example.smecalculator.entity.BalanceEntity;
import com.example.smecalculator.entity.CostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceRepository extends JpaRepository<BalanceEntity, String> {

    List<BalanceEntity> findByLoginOrderByDate(String login);
}
