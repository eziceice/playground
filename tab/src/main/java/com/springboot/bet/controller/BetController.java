package com.springboot.bet.controller;

import com.springboot.bet.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.bet.pojo.Bet;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Rest Controller for Bet Services
 */
@RestController
public class BetController {

    @Autowired
    private BetService betService;

    @PostMapping("/bet")
    public ResponseEntity<Bet> insertBet(@Valid @RequestBody Bet bet, HttpServletRequest request) {
        betService.insertBet(bet);
        return new ResponseEntity<>(bet, HttpStatus.OK);
    }
}
