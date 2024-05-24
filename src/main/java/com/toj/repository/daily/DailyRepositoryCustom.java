package com.toj.repository.daily;

import com.toj.dto.daily.GetRankingResponse;
import com.toj.entity.RoutineCate;

import java.util.List;

public interface DailyRepositoryCustom {

    List<GetRankingResponse> getRankingWithTotalScore(RoutineCate routineCate);

}
