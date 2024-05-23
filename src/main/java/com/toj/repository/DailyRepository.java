package com.toj.repository;

import com.toj.entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DailyRepository extends JpaRepository<Daily, Long> {

    @Query("select d from Daily d where d.routine.id = :routineId and d.regTime between :startDate and :endDate")
    List<Daily> findByRoutineIdAndRegTimeBetween(@Param("routineId") Long routineId,
                                                 @Param("startDate")LocalDateTime startDate,
                                                 @Param("endDate")LocalDateTime endDate);

}
