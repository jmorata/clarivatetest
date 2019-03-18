package com.jmorata.clarivatetest.presenter;

import com.jmorata.clarivatetest.domain.User;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Builder
@lombok.Data
@lombok.EqualsAndHashCode
public class UserLevelPresenterSet {

    private Set<User> userSet=new HashSet<>();
}
