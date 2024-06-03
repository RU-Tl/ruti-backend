package com.toj.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String name;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;
    private int totalScore;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Routine> routineList = new ArrayList<>();

    public Member (String email, String name, String nickname) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.grade = MemberGrade.RUCOMI;
        this.totalScore = 0;
    }

    public void updateTotalScore(int score) {
        this.totalScore += score;
    }

    public void updateGrade(MemberGrade memberGrade) {
        this.grade = memberGrade;
    }

}
