package com.jmorata.clarivatetest.domain;

import java.util.TreeMap;

public class UserMap {

    private TreeMap<UserId, User> users = new TreeMap<>();

    public void addUser(UserId userId, User userNew) {
        User user = getUser(userId);
        if (user != null) {
            user.addLevels(userNew);

        } else {
            this.users.put(userId, userNew);
        }
    }

    public User getUser(UserId userId) {
        return this.users.get(userId);
    }
}
