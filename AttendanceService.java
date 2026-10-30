package com.example.demo;
//import com.example.demo.entity.Attendance;
//import com.example.demo.entity.User;
//import com.example.demo.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public boolean markPresent(User user) {
        LocalDate today = LocalDate.now();
        if (attendanceRepository.existsByUserAndDate(user, today)) {
            return false;
        }
        Attendance a = new Attendance();
        a.setUser(user);
        a.setDate(today);
        a.setStatus("Present");
        attendanceRepository.save(a);
        return true;
    }

    public List<Attendance> getAttendanceForDate(LocalDate date) {
        return attendanceRepository.findAllByDate(date);
    }
}