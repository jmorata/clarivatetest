package com.jmorata.clarivatetest.service;

import com.jmorata.clarivatetest.domain.*;
import com.jmorata.clarivatetest.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service
public class UserService {

    @Autowired
    private Users users;

    User getUser(String username) {
        UserId userId = UserId.of(username);
        return users.load(userId);
    }

    public void addUserLevelScore(String username, Integer levelInt, Integer scoreInt) throws UserException {
        Level level = Level.of(levelInt);
        Score score = Score.of(scoreInt);

        UserId userId = UserId.of(username);
        User user = users.load(userId);

        if (null != user) {
            user.addScoreLevel(level, score);

        } else {
            throw new UserException("User " + username + "doesn't exists");
        }
    }

    public TreeMap<UserId, User> getUsersByLevel(Integer levelInt) {
        Level level = Level.of(levelInt);
        TreeMap<UserId, User> userIdScoreSetMap = users.findByLevel(level);

        return userIdScoreSetMap;
    }
}
