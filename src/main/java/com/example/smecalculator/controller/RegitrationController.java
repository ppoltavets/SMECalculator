package com.example.smecalculator.controller;

import com.example.smecalculator.entity.RegistrationEntity;
import com.example.smecalculator.entity.TokensEntity;
import com.example.smecalculator.service.TokensService;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.smecalculator.service.RegistrationService;

import java.io.IOException;
import java.util.UUID;


@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/registration")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegitrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private TokensService tokensService;

    Logger logger = LoggerFactory.getLogger(RegitrationController.class);


    @PostMapping("/save-user") // Регистрация пользователя, сохранение инфо в базу
    public ResponseEntity<String> saveUser(@RequestBody RegistrationEntity entity) {
        logger.info("Получен запрос на регистрацию");
        ResponseEntity<String> response = null;
        registrationService.addUser(entity);
        response = new ResponseEntity<String>("Sign-up success:", HttpStatus.OK);
        return response;
    }

    @PostMapping("/login-user")
    public ResponseEntity<RegistrationEntity> loginUser(@RequestBody RegistrationEntity entryUser, HttpServletResponse response) {

        logger.info("Получен запрос на авторизацию");
        ResponseEntity<RegistrationEntity> responseEntity = null;

        var foundUsers = registrationService.login(entryUser);
        if (foundUsers == null) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return responseEntity;
        }

        try {
            setCookie(foundUsers.getLogin(), response);
        } catch (IOException e) {
            logger.error("Не удалось передать куки в ответе");
        }

        responseEntity = new ResponseEntity<>(foundUsers, HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/get-user/{login}")
    public ResponseEntity<RegistrationEntity> getUser(@PathVariable String login, HttpServletRequest request) {
        logger.info("Получен запрос на получение информации о пользователе");
        ResponseEntity<RegistrationEntity> response = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    if (tokensService.validateCookie(cookie.getValue())) {
                        var foundUser = registrationService.findUser(login);
                        if (foundUser == null) {
                            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        } else {
                            response = new ResponseEntity<>(foundUser, HttpStatus.OK);
                        }
                        return response;
                    }
                }
            }
        }

        response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return response;
    }


    @GetMapping("/account-info/")
    public ResponseEntity<RegistrationEntity> accountInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        ResponseEntity<RegistrationEntity> response = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    if (tokensService.validateCookie(cookie.getValue())) {
                        var user = tokensService.findUser(cookie.getValue());
                        var clientInfo = registrationService.returnUserInfo(user);
                        response = new ResponseEntity<>(clientInfo, HttpStatus.OK);
                        return response;
                    }
                }
            }
        }
        response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return response;
    }

    @PostMapping("/account-info/update")
    public ResponseEntity<RegistrationEntity> accountUpdate(@RequestBody RegistrationEntity updateInfo, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        ResponseEntity<RegistrationEntity> response = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    if (tokensService.validateCookie(cookie.getValue())) {
                        registrationService.addUserInfo(updateInfo);
                        response = new ResponseEntity<>(HttpStatus.OK);
                    }
                }
            }
        }
        response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return response;
    }

    @GetMapping(value = "/set-cookie")
    public ResponseEntity<?> setCookie(String login, HttpServletResponse response) throws IOException {
        var token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("userCookie", token);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        response.setContentType("text/plain");
        var saveToken = TokensEntity.builder().username(login).token(token)
                .build();
        tokensService.addCookie(saveToken);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
