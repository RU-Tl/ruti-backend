package com.toj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Routine {

    @Id
    @Column(name = "routine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private RoutineCate routineCate;

    private String content;

    private LocalDate startDate;

    private LocalDate endDate;

    private String alarmTime;
    private String daysOfWeek;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<Daily> dailyList = new ArrayList<>();

    public Routine(Member member, String content, String categories, LocalDate startDate, LocalDate endDate, String alarmTime, String daysOfWeek) {
        this.member = member;
        this.content = content;
        this.routineCate = RoutineCate.valueOf(categories);
        this.startDate = startDate;
        this.endDate = endDate;
        this.alarmTime = alarmTime;
        this.daysOfWeek = daysOfWeek;
    }

    public Long update(String content) {
        this.content = content;

        return this.id;
    }
}
