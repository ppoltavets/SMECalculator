package com.example.smecalculator.repository;

import com.example.smecalculator.entity.BalanceEntity;
import com.example.smecalculator.entity.CostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, String> {

    List<BalanceEntity> findByLoginOrderByDate(String login);
}
