package com.toj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberGrade {

    LUCOMI("루꼬미"),
    LUTINI("루티니"),
    LOUTINEMASTER("루틴마스터"),
    ROUTINELEVELMAX("루틴만렙");

    private final String value;
}
