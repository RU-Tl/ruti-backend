package com.toj.repository.daily;

import com.toj.dto.daily.GetCalendarResponse;
import com.toj.dto.daily.GetRankingResponse;
import com.toj.entity.RoutineCate;

import java.util.List;

public interface DailyRepositoryCustom {

    List<GetRankingResponse> getRankingWithTotalScore(RoutineCate routineCate);

    List<GetCalendarResponse> getCalendar(Long memberId, int month, RoutineCate routineCate);
}
