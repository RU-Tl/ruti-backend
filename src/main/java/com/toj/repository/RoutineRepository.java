package com.toj.repository;

import com.toj.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    List<Routine> findAllByMemberId(Long memberId);
}
