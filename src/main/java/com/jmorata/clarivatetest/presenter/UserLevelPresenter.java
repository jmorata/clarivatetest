package com.jmorata.clarivatetest.presenter;

import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Builder
@lombok.Data
@lombok.EqualsAndHashCode
public class UserLevelPresenter {

    private String username;
    private Set<Integer> scoreSet = new HashSet<>();
}
