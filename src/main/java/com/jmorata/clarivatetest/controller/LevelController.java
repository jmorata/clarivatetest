package com.jmorata.clarivatetest.controller;

import com.jmorata.clarivatetest.adapter.UserLevelPresenterAdapter;
import com.jmorata.clarivatetest.domain.ScoreSet;
import com.jmorata.clarivatetest.domain.UserId;
import com.jmorata.clarivatetest.presenter.UserLevelPresenterSet;
import com.jmorata.clarivatetest.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Map;

@RestController
@RequestMapping("/level")
public class LevelController {

    @Autowired
    UserService userService;

    @PutMapping("/{level}/score/{score}")
    public void setScore(@RequestAttribute("claims") final Claims claims, @PathVariable final Integer level, @PathVariable final Integer score) throws ServletException {
        String username = claims.getSubject();
        userService.addUserLevelScore(username, level, score);
    }

    @GetMapping("/{level}/score")
    public ResponseEntity<UserLevelPresenterSet> getScore(@PathVariable final Integer level, @RequestParam(required = false) final String filter) throws ServletException {
        Map<UserId, ScoreSet> usersByLevel = userService.getUsersByLevel(level);
        UserLevelPresenterSet users = UserLevelPresenterAdapter.adapt(usersByLevel, filter);

        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
    }

}
