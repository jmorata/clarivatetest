package com.jmorata.test2.domain;

import lombok.Data;

@Data(staticConstructor = "of")
public class Score {

    private final Integer value;
}
