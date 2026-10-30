package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	boolean existsByUserAndDate(User user, LocalDate date);
	 List<Attendance> findAllByDate(LocalDate date);
	    List<Attendance> findAllByUser(User user);
}
