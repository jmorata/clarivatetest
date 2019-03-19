package com.jmorata.clarivatetest.service;

import com.jmorata.clarivatetest.domain.User;
import com.jmorata.clarivatetest.domain.UserId;
import com.jmorata.clarivatetest.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Service
public class LoginService {

    @Autowired
    private Users users;

    public boolean checkUser(String username, String password) {
        UserId userId = UserId.of(username);
        User user = users.load(userId);

        return null != user && user.getPassword().equalsIgnoreCase(password);
    }

    public List<String> loadAll() {
        List<String> usernames=new ArrayList<>();

        TreeMap<UserId, User> userIdUserTreeMap = users.loadAll();
        for (UserId userId: userIdUserTreeMap.keySet()) {
            usernames.add(userId.getUsername());
        }

        return usernames;
    }
}
