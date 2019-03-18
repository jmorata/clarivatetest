package com.jmorata.clarivatetest.mother;

import com.jmorata.clarivatetest.domain.*;

public class UserMother {
    /*
    public static UserMap createUserMapDummy() {

        UserId userId = new UserId(1L);
        User user = getUser("user1", "pass1", 1, 1500);

        UserMap userMap = new UserMap();
        userMap.addUserMap(userId, user);

        userId = new UserId(2L);
        user = getUser("user2", "pass2", 1, 800);
        userMap.addUserMap(userId, user);

        // add levels, scorings at user

        return userMap;
    }

    private static User getUser(String username, String password, Integer levelValue, Integer scoreValue) {
        Score score = new Score(scoreValue);
        ScoreSet scoreSet = new ScoreSet();
        scoreSet.addScore(score);
        Level level = new Level(levelValue);
        User user = new User(username, password);
        user.putLevel(level, scoreSet);
        return user;
    }
    */
}
