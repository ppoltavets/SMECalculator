package com.example.smecalculator.repository;

import com.example.smecalculator.entity.BalanceEntity;
import com.example.smecalculator.entity.CashFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlowEntity, String> {

    List<CashFlowEntity> findByLoginOrderByDate(String login);
}
