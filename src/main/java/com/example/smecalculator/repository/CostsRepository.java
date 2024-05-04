package com.example.smecalculator.repository;

import com.example.smecalculator.entity.CostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CostsRepository extends JpaRepository<CostsEntity, String> {

    List<CostsEntity> findByLoginOrderByDate(String login);

    CostsEntity findByLoginAndDate(String login, LocalDate date);
}
