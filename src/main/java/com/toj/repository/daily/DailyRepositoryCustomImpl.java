package com.toj.repository.daily;

import com.querydsl.core.support.ExtendedSubQuery;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toj.dto.daily.GetCalendarResponse;
import com.toj.dto.daily.GetRankingResponse;
import com.toj.dto.daily.QGetCalendarResponse;
import com.toj.dto.daily.QGetRankingResponse;
import com.toj.entity.RoutineCate;

import java.util.List;

import static com.toj.entity.QDaily.daily;
import static com.toj.entity.QMember.member;
import static com.toj.entity.QRoutine.routine;

public class DailyRepositoryCustomImpl implements DailyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DailyRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<GetRankingResponse> getRankingWithTotalScore(RoutineCate routineCate) {

        List<GetRankingResponse> rank = queryFactory.
                select(new QGetRankingResponse(
                        routine.member.id,
                        member.nickname,
                        daily.score.sum().as("totalScore")
                ))
                .from(daily)
                .join(daily.routine, routine)
                .join(routine.member, member)
                .where(routine.routineCate.eq(routineCate))
                .groupBy(routine.routineCate, routine.member.id)
                .fetch();

        return rank;
    }

    @Override
    public List<GetCalendarResponse> getCalendar(Long memberId, int month, RoutineCate routineCate) {

        List<GetCalendarResponse> calendars = queryFactory
                .select(new QGetCalendarResponse(
                        daily.id,
                        routine.content,
                        routine.routineCate,
                        routine.alarmTime,
                        daily.dailyCate,
                        daily.failReason,
                        daily.regTime
                ))
                .from(daily)
                .join(daily.routine, routine)
                .join(routine.member, member)
                .where(member.id.eq(memberId),
                        routine.routineCate.in(routineCate))
                .fetch();

        return calendars;
    }
}
