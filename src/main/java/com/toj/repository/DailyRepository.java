package com.toj.repository;

import com.toj.entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DailyRepository extends JpaRepository<Daily, Long> {
    List<Daily> findByRoutineId(Long routineId);

}
