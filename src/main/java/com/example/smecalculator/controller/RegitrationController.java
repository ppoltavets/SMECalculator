package com.example.smecalculator.controller;

import com.example.smecalculator.entity.RegistrationEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.smecalculator.service.RegistrationService;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/registration")
public class RegitrationController {

    @Autowired
    private RegistrationService registrationService;

    Logger logger = LoggerFactory.getLogger(RegitrationController.class);


    @PostMapping("/save-user") // Регистрация пользователя, сохранение инфо в базу
    public ResponseEntity<String> saveUser(@RequestBody RegistrationEntity entity) {
        ResponseEntity<String> response = null;
        registrationService.addUser(entity);
        response = new ResponseEntity<String>("Sign-up success:",HttpStatus.OK);
        return response;
    }

    @PostMapping("/login-user") //
    public ResponseEntity<RegistrationEntity> loginUser(@RequestBody RegistrationEntity entryUser) {
        logger.info("Получен запрос");
        ResponseEntity<RegistrationEntity> response = null;
        var foundUsers = registrationService.login(entryUser);
        if (foundUsers == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return response;
        }
        response = new ResponseEntity<>(foundUsers,HttpStatus.OK);
        return response;
    }

    @GetMapping("/get-user/{login}")
    public ResponseEntity<RegistrationEntity> getUser(@PathVariable String login){
        ResponseEntity<RegistrationEntity> response = null;
        var foundUser = registrationService.findUser(login);
        if(foundUser == null)
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND); // TODO: Проверка не работает надо будет подебажить
        response = new ResponseEntity<>(foundUser,HttpStatus.OK);
        return response;
    }
}
