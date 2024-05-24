package com.toj.repository.daily;

import com.toj.dto.daily.GetRankingResponse;
import com.toj.entity.Daily;
import com.toj.entity.RoutineCate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface DailyRepository extends JpaRepository<Daily, Long>, DailyRepositoryCustom {

    @Query("select d from Daily d where d.routine.id = :routineId and d.regTime between :startDate and :endDate")
    List<Daily> findByRoutineIdAndRegTimeBetween(@Param("routineId") Long routineId,
                                                 @Param("startDate")LocalDateTime startDate,
                                                 @Param("endDate")LocalDateTime endDate);

    List<Daily> findByRoutineId(Long routineId);


/*    @Query(value = "select rank() over (order by sum(d.score) desc) as ranking," +
            "sum(d.score) as total_score " +
            "from daily d " +
            "join routine r on d.routine_id = r.routine_id " +
            "join member m on m.member_id = r.member_id " +
            "where r.routine_cate = :#{#routineCate.name()} " +
            "group by r.routine_cate, r.member_id",
            nativeQuery = true)
    List<GetRankingResponse> getRankingWithTotalScore(
            @Param("routineCate") RoutineCate routineCate);*/

/*
    @Query("SELECT new com.toj.dto.daily.GetRankingResponse(" +
            "SUM(d.score)) " +
            "FROM Daily d " +
            "JOIN d.routine r " +
            "JOIN r.member m " +
            "WHERE r.routineCate = :routineCate " +
            "AND r.member.id = :memberId " +
            "GROUP BY r.routineCate, r.member.id " +
            "ORDER BY SUM(d.score) DESC")
    GetRankingResponse getRankingWithTotalScore(
            @Param("memberId") Long memberId,
            @Param("routineCate") RoutineCate routineCate);
*/


}
