package com.jmorata.test2.adapter;

import com.jmorata.test2.domain.Score;
import com.jmorata.test2.domain.ScoreSet;
import com.jmorata.test2.domain.UserId;
import com.jmorata.test2.presenter.UserLevelPresenter;
import com.jmorata.test2.presenter.UserLevelPresenterSet;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.NoSuchElementException;

public class UserLevelPresenterAdapter {

    private static final String HIGHSCORE="highestscore";

    public static UserLevelPresenterSet adapt(Map<UserId, ScoreSet> usersByLevel, String filter) {
        UserLevelPresenterSet userLevelPresenterSet = new UserLevelPresenterSet();

        for (UserId userId : usersByLevel.keySet()) {
            ScoreSet scoreSet = usersByLevel.get(userId);
            UserLevelPresenter userLevelPresenter = createUserLevelPresenter(userId, scoreSet, filter);
            userLevelPresenterSet.getUserLevelPresenters().add(userLevelPresenter);
        }

        return userLevelPresenterSet;
    }

    private static UserLevelPresenter createUserLevelPresenter(UserId userId, ScoreSet scoreSet, String filter) {
        String username = userId.getUsername();
        Integer score = getScoreByFilter(scoreSet, filter);
        UserLevelPresenter userLevelPresenter = new UserLevelPresenter(username, score);

        return userLevelPresenter;
    }

    private static Integer getScoreByFilter(ScoreSet scoreSet, String filter) {
        Integer score = scoreSet.getScores().iterator().next().getValue();

        if (StringUtils.hasText(filter)) {
            switch (filter) {
                case HIGHSCORE: {
                    score = scoreSet.getScores()
                            .stream()
                            .mapToInt(Score::getValue)
                            .max().orElseThrow(NoSuchElementException::new);
                    break;
                }
            }
        }

        return score;
    }

}
