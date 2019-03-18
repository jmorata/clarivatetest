package com.jmorata.clarivatetest.domain;

import lombok.Data;

@Data(staticConstructor = "of")
public class Score {

    private final Integer value;
}
