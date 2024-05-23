package com.toj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordCate {

    EXERCISE("운동"),
    READING("독서"),
    DEVELOPMENT("자기개발");

    private final String value;

}
