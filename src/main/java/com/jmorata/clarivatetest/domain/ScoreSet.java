package com.jmorata.clarivatetest.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ScoreSet {

    private Set<Score> scores = new HashSet<>();

    public void addScore(Score score) {
        this.scores.add(score);
    }
}
