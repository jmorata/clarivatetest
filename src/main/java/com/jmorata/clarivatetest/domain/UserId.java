package com.jmorata.clarivatetest.domain;

@lombok.Data
public class UserId implements Comparable<UserId>{

    private final Long id;

    @Override
    public int compareTo(UserId o) {
        return this.id.compareTo(o.id);
    }
}
