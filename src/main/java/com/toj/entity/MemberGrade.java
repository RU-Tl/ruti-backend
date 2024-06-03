package com.toj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberGrade {

    RUCOMI("루꼬미"),
    RUTINI("루티니"),
    ROUTINEMASTER("루틴마스터"),
    ROUTINELEVELMAX("루틴만렙");

    private final String value;
}
