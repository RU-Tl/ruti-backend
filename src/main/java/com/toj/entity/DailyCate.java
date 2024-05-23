package com.toj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DailyCate {

    SUCCESS("시작할게요", 10),
    DELAY("잠시 미룰게요", 10),
    FAIL("오늘은 어려워요", 3);

    private final String value;
    private final int score;
}
