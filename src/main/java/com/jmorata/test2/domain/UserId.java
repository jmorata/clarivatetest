package com.jmorata.test2.domain;

import lombok.Data;

@Data(staticConstructor = "of")
public class UserId implements Comparable<UserId> {

    private final String username;

    @Override
    public int compareTo(UserId o) {
        return this.username.compareTo(o.username);
    }
}
