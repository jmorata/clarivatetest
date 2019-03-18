package com.jmorata.clarivatetest.domain;

import java.util.Set;
import java.util.TreeMap;

@lombok.Data
public class User {

    private final String username;
    private final String password;
    private TreeMap<Level, ScoreSet> levels = new TreeMap<>();

    public void addLevels(User user) {
        Set<Level> levelSet = user.getLevels().keySet();
        for (Level level : levelSet) {

            ScoreSet userScoreSet = user.getLevels().get(level);
            ScoreSet scoreSet = getScoreByLevel(level);
            if (scoreSet != null) {
                scoreSet.addAllScoreSet(userScoreSet);

            } else {
                putLevel(level, userScoreSet);
            }
        }
    }

    public void putLevel(Level level, ScoreSet userScoreSet) {
        this.levels.put(level, userScoreSet);
    }


    public ScoreSet getScoreByLevel(Level level) {
        return this.levels.get(level);
    }
}
