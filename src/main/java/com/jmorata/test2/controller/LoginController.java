package com.jmorata.test2.controller;

import com.jmorata.test2.service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${test.ttl}")
    private Integer ttl;

    @Autowired
    LoginService loginService;

    @GetMapping(value = "/test")
    public ResponseEntity<List<String>> test() {
        List<String> usernames = loginService.loadAll();

        return new ResponseEntity<>(usernames, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> login(@RequestBody final RequestLogin requestLogin) throws ServletException {
        final boolean existUser = loginService.checkUser(requestLogin.getUsername(), requestLogin.getPassword());

        if (!existUser) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        final Instant now = Instant.now();
        final String jwt = getJwt(requestLogin, now);

        return new ResponseEntity<>(jwt, HttpStatus.ACCEPTED);
    }

    private String getJwt(@RequestBody RequestLogin requestLogin, Instant now) {
        return Jwts.builder()
                .setSubject(requestLogin.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(ttl, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
                .compact();
    }

}
