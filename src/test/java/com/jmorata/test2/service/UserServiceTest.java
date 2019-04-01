package com.jmorata.test2.service;

import com.jmorata.test2.domain.User;
import com.jmorata.test2.domain.UserId;
import com.jmorata.test2.exception.UserException;
import com.jmorata.test2.repository.UsersMapRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static Integer levelInt = 3;
    private static final String USERNAME = "demo0";
    private static final UserId userId = UserId.of(USERNAME);
    private static final User user = User.of(USERNAME);

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UsersMapRepository usersMapRepository;

    @Before
    public void setUp() {
        Mockito.when(usersMapRepository.load(userId)).thenReturn(user);
    }

    @Test
    public void addUserLevelScore() throws UserException {
        userService.addUserLevelScore(USERNAME, levelInt, 1500);
        User userTest = userService.getUser(USERNAME);

        assertThat(user).isEqualTo(userTest);
    }

    @Test
    public void getUsersByLevel() throws UserException {
        userService.addUserLevelScore(USERNAME, levelInt, 1500);
        TreeMap<UserId, User> userMap1 = usersMapRepository.loadAll();
        userService.addUserLevelScore(USERNAME, levelInt + 1, 100);

        TreeMap<UserId, User> userMap2 = userService.getUsersByLevel(levelInt);

        assertThat(userMap1).isEqualTo(userMap2);
    }

}