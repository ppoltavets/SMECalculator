package com.example.smecalculator.service;

import com.example.smecalculator.entity.TokensEntity;

public interface ITokensService {

    public Boolean validateCookie(String cookie);

    public void addCookie(TokensEntity tokens);

    public String findUser(String cookie);
}
