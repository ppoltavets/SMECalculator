package com.example.smecalculator.repository;

import com.example.smecalculator.entity.TokensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokensRepository extends JpaRepository<TokensEntity, Integer> {
    TokensEntity findAllByToken(String token);
}
