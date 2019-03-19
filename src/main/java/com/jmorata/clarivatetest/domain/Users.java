package com.jmorata.clarivatetest.domain;

import java.util.TreeMap;

public interface Users {

    User load(UserId userId);

    void save(UserId userId, User user);

    TreeMap<UserId, User> findByLevel(Level level);

    TreeMap<UserId, User> loadAll();
}
