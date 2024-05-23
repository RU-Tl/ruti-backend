package com.toj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Routine {

    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private RecordCate recordCate;

    private String content;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String alarmTime;

    public Routine(Member member, String content, String categories, LocalDateTime startDate, LocalDateTime endDate, String alarmTime) {
        this.member = member;
        this.content = content;
        this.recordCate = RecordCate.valueOf(categories);
        this.startDate = startDate;
        this.endDate = endDate;
        this.alarmTime = alarmTime;
    }
}
