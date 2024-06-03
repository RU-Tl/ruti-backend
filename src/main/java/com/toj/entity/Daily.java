package com.toj.entity;

import com.toj.entity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

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

    private String failReason;


    public Daily(Routine routine, DailyCate status, String failReason) {
        this.routine = routine;
        this.dailyCate = status;
        if (status.equals(DailyCate.SUCCESS)) {
            this.score = 10;
        } else if (status.equals(DailyCate.FAIL)) {
            this.score = 3;
        }
        if (!ObjectUtils.isEmpty(failReason))
            this.failReason = failReason;
    }
}
