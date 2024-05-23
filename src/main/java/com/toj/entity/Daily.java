package com.toj.entity;

import com.toj.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Daily extends BaseTimeEntity {

    @Id
    @Column(name = "daily_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @Enumerated(EnumType.STRING)
    private DailyCate dailyCate;

    private int score;

    private String status;

    private String failReason;

}
