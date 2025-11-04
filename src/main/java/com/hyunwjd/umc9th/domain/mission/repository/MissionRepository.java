package com.hyunwjd.umc9th.domain.mission.repository;

import com.hyunwjd.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class MissionRepository extends JpaRepository<Mission, Long> {


}
