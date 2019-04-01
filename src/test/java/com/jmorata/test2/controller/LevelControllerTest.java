package com.jmorata.test2.controller;

import com.jmorata.test2.domain.*;
import com.jmorata.test2.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.TreeMap;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LevelControllerTest {

    private MockMvc mvc;

    private static TreeMap<UserId, User> userIdUserTreeMap = new TreeMap<>();

    private static Integer levelInt = 3;
    private static final String USERNAME = "demo0";
    private static final UserId userId = UserId.of(USERNAME);
    private static final User user = User.of(USERNAME);
    private static final Level level=Level.of(levelInt);


    @InjectMocks
    private LevelController levelController;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(levelController)
                .build();

        Mockito.when(userService.getUsersByLevel(levelInt)).thenReturn(userIdUserTreeMap);
        user.getLevels().put(level,getScoreSet());
        userIdUserTreeMap.put(userId, user);
    }

    private ScoreSet getScoreSet() {
        ScoreSet scoreSet=new ScoreSet();
        Score score= Score.of(1500);
        scoreSet.getScores().add(score);

        return scoreSet;
    }

    @Test
    public void getScore() throws Exception {
        final String json="{\"userLevelPresenters\":[{\"username\":\"demo0\",\"score\":1500}]}";

        mvc.perform(get("/level/{level}/score", levelInt))
                .andExpect(status().isOk())
                .andExpect(content().json(json))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(userService, times(1)).getUsersByLevel(levelInt);
    }

}