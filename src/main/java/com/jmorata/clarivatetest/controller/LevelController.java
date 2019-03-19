package com.jmorata.clarivatetest.controller;

import com.jmorata.clarivatetest.adapter.UserLevelPresenterAdapter;
import com.jmorata.clarivatetest.domain.Level;
import com.jmorata.clarivatetest.domain.ScoreSet;
import com.jmorata.clarivatetest.domain.User;
import com.jmorata.clarivatetest.domain.UserId;
import com.jmorata.clarivatetest.exception.UserException;
import com.jmorata.clarivatetest.presenter.UserLevelPresenterSet;
import com.jmorata.clarivatetest.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/level")
public class LevelController {

    @Autowired
    UserService userService;

    @PutMapping("/{level}/score/{score}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setScore(@RequestAttribute("claims") final Claims claims, @PathVariable final Integer level, @PathVariable final Integer score) throws UserException {
        String username = claims.getSubject();
        userService.addUserLevelScore(username, level, score);
    }

    @GetMapping("/{level}/score")
    public ResponseEntity<UserLevelPresenterSet> getScore(@PathVariable final Integer level, @RequestParam(required = false) final String filter) throws ServletException {
        TreeMap<UserId, User> userIdUserTreeMap = userService.getUsersByLevel(level);
        Map<UserId, ScoreSet> userIdScoreSetMap = getUserIdScoreSetMap(level, userIdUserTreeMap);
        UserLevelPresenterSet users = UserLevelPresenterAdapter.adapt(userIdScoreSetMap, filter);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    private Map<UserId, ScoreSet> getUserIdScoreSetMap(@PathVariable Integer levelInt, TreeMap<UserId, User> userIdUserTreeMap) {
        Map<UserId, ScoreSet> userIdScoreSetMap = new HashMap<>();
        for (UserId userId : userIdUserTreeMap.keySet()) {
            User user = userIdUserTreeMap.get(userId);
            Level level= Level.of(levelInt);
            ScoreSet scoreSet = user.getLevels().get(level);
            userIdScoreSetMap.put(userId, scoreSet);
        }
        return userIdScoreSetMap;
    }

}
