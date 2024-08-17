package com.toj.repository;

import com.toj.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    List<Routine> findAllByMemberId(Long memberId);

    @Query("select * from routine where memberId := memberId and := selectedDate between start_date and end_date")
    List<Routine> findAllByMemberIdAndDate(Long memberId, LocalDate selectedDate);
}
