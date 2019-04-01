package com.jmorata.test2.domain;

import lombok.Data;

import java.util.TreeMap;

@Data(staticConstructor = "of")
public class User {

    private final String password;
    private TreeMap<Level, ScoreSet> levels = new TreeMap<>();

    public void addScoreLevel(Level level, Score score) {
        ScoreSet scoreSet = this.levels.get(level);
        if (null==scoreSet) {
            scoreSet=new ScoreSet();
            levels.put(level, scoreSet);
        }
        scoreSet.addScore(score);
    }

}
