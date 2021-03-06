package com.jmorata.test2.repository;

import com.jmorata.test2.domain.Level;
import com.jmorata.test2.domain.User;
import com.jmorata.test2.domain.UserId;
import com.jmorata.test2.domain.Users;
import com.jmorata.test2.repository.database.UsersDummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TreeMap;

@Component
public class UsersMapRepository implements Users {

    @Autowired
    private UsersDummyData usersDummyData;

    // simple map in memory, without entities
    private static TreeMap<UserId, User> userIdUserTreeMap = new TreeMap<>();

    @Override
    public TreeMap<UserId, User> loadAll() {
        usersDummyData.loadDummyData(userIdUserTreeMap);
        return userIdUserTreeMap;
    }

    @Override
    public User load(UserId userId) {
        return userIdUserTreeMap.get(userId);
    }

    @Override
    public void save(UserId userId, User user) {
        userIdUserTreeMap.put(userId, user);
    }

    @Override
    public TreeMap<UserId, User> findByLevel(Level level) {
        TreeMap<UserId, User> userIdUserTreeMapFiltered = new TreeMap<>();
        for (UserId userId : userIdUserTreeMap.keySet()) {

            User user = userIdUserTreeMap.get(userId);
            if (user.getLevels().containsKey(level)) {
                userIdUserTreeMapFiltered.put(userId, user);
            }
        }

        return userIdUserTreeMapFiltered;
    }

}
