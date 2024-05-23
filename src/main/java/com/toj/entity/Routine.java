package com.toj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String categories;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Routine (Member member, String categories, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.member = member;
        this.categories = categories;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
