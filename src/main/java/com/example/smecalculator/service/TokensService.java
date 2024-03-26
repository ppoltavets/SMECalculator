package com.example.smecalculator.service;

import com.example.smecalculator.entity.TokensEntity;
import com.example.smecalculator.repository.RegistrationRepository;
import com.example.smecalculator.repository.TokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokensService implements ITokensService{
    private final TokensRepository repository;

    public TokensService(TokensRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean validateCookie(String cookie) {
        return repository.findAllByToken(cookie) != null;
    }

    @Override
    public void addCookie(TokensEntity tokens) {
        repository.save(tokens);
    }

}
