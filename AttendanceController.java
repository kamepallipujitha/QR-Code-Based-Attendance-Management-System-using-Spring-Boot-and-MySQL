package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	AttendanceRepository attendanceRepo;
	

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public String viewAttendance(@RequestParam(required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                 Model model) {
        if (date == null) date = LocalDate.now();
        List<Attendance> list = attendanceService.getAttendanceForDate(date);
        model.addAttribute("selectedDate", date);
        model.addAttribute("attendanceList", list);
        return "attendance-list";
    }
    @GetMapping("/attendance")
    public String showAttendance(Model model) {
        List<Attendance> list = attendanceRepo.findAll();
        model.addAttribute("attendanceList", list);
        return "attendance-record";

    }

}
