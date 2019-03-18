package com.jmorata.clarivatetest.domain;

import java.util.HashSet;
import java.util.Set;

@lombok.Data
public class ScoreSet {

    private Set<Score> scores = new HashSet<>();

    public void addAllScoreSet(ScoreSet userScoreSet) {
        this.scores.addAll(userScoreSet.getScores());
    }

    public void addScore(Score score) {
        this.scores.add(score);
    }
}
