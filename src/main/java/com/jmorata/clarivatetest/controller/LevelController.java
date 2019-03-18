package com.jmorata.clarivatetest.controller;

import com.jmorata.clarivatetest.domain.User;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/level")
public class LevelController {

    @PutMapping("/{level}/score/{score}")
    public void setScore(@RequestAttribute("claims") final Claims claims, @PathVariable final Integer level, @PathVariable final Integer score) {

    }

    //@ResponseBody
    @GetMapping("/{level}/score")
    public ResponseEntity<User> getScore(@PathVariable final Integer level, @RequestParam(required = false) final String filter) {

        User user = new User("user1", "pass1");

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

}
