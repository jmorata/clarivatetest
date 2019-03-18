package com.jmorata.clarivatetest.domain;

@lombok.Data
public class Level implements Comparable<Level>{

    private final Integer value;

    @Override
    public int compareTo(Level o) {
        return this.value.compareTo(o.value);
    }
}
