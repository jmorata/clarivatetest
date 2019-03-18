package com.jmorata.clarivatetest.controller;

import com.jmorata.clarivatetest.service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${clarivatetest.ttl}")
    private Integer ttl;

    @Autowired
    LoginService loginService;

    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login(@RequestBody final RequestLogin requestLogin) throws ServletException {
        final boolean existUser = loginService.checkUser(requestLogin.getUsername(), requestLogin.getPassword());

        if (!existUser) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        final Instant now = Instant.now();

        final String jwt = Jwts.builder()
                .setSubject(requestLogin.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(ttl, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
                .compact();

        return new ResponseEntity<>(jwt, HttpStatus.ACCEPTED);
    }

}
