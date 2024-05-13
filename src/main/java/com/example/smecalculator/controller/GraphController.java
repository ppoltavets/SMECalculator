package com.example.smecalculator.controller;

import com.example.smecalculator.entity.BalanceEntity;
import com.example.smecalculator.entity.CostsEntity;
import com.example.smecalculator.entity.DateEntity;
import com.example.smecalculator.service.BalanceService;
import com.example.smecalculator.service.CostsService;
import com.example.smecalculator.service.TokensService;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/analytics")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraphController {
    @Autowired
    private CostsService costsService;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private TokensService tokensService;

    Logger logger = LoggerFactory.getLogger(RegitrationController.class);


    @PostMapping("/save-costs")
    public ResponseEntity<CostsEntity> saveCost(@RequestBody CostsEntity costs, HttpServletRequest request) {
        logger.info("Получен запрос на сохранение в таблицу costs");
        var cookieChecker = findCookieUser(request);
        if (cookieChecker.getStatusCode().equals(HttpStatus.OK)) {
            costs.setLogin(cookieChecker.getBody());
            costsService.saveCosts(costs);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/get-costs")
    public ResponseEntity<?> getCosts(HttpServletRequest request){
        logger.info("Получен запрос на получение Cost'ов");
        var cookieCheker = findCookieUser(request);
        if(cookieCheker.getStatusCode().equals(HttpStatus.OK)){
            costsService.getCosts(cookieCheker.getBody());
            return new ResponseEntity<>(costsService.getCosts(cookieCheker.getBody()), HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("find-cookie")
    public ResponseEntity<String> findCookieUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userCookie")) {
                    if (tokensService.validateCookie(cookie.getValue()))
                        return new ResponseEntity<>(tokensService.findUser(cookie.getValue()), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/save-balance")
    public ResponseEntity<BalanceEntity> saveCost(@RequestBody BalanceEntity balance, HttpServletRequest request) {
        logger.info("Получен запрос на сохранение в таблицу balance");
        var cookieChecker = findCookieUser(request);
        if (cookieChecker.getStatusCode().equals(HttpStatus.OK)) {
            balance.setLogin(cookieChecker.getBody());
            balanceService.saveBalance(balance);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/get-balance")
    public ResponseEntity<?> getBalance(HttpServletRequest request){
        logger.info("Получен запрос на получение Balance'ов");
        var cookieCheker = findCookieUser(request);
        if(cookieCheker.getStatusCode().equals(HttpStatus.OK)){
            balanceService.getBalance(cookieCheker.getBody());
            return new ResponseEntity<>(balanceService.getBalance(cookieCheker.getBody()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
