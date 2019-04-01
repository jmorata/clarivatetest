package com.jmorata.test2.domain;

import lombok.Data;

@Data(staticConstructor = "of")
public class Level implements Comparable<Level> {

    private final Integer value;

    @Override
    public int compareTo(Level o) {
        return this.value.compareTo(o.value);
    }
}
