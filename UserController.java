package com.example.demo;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

@Controller
public class UserController {

    private final UserService userService;
    private final AttendanceService attendanceService;

    public UserController(UserService userService, AttendanceService attendanceService) {
        this.userService = userService;
        this.attendanceService = attendanceService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/register";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }
    @GetMapping("/login")
    public String showLogin() {
        return "login";  
    }


    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam(required = false) String password,
                           Model model) {
        try {
            User user = userService.registerUser(name, email, password);

            model.addAttribute("message", "Registration successful!");
            model.addAttribute("user", user);
            model.addAttribute("downloadUrl", "/downloadQR?rollNo=" + user.getRollNo());

            return "register-success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
    @GetMapping("/downloadQR")
    public ResponseEntity<FileSystemResource> downloadQR(@RequestParam String rollNo) {
        try {
            Path filePath = Path.of(System.getProperty("user.dir"), "qrcodes", rollNo + ".png");
            File file = filePath.toFile();

            if (!file.exists()) {
                System.out.println("❌ File not found at: " + file.getAbsolutePath());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + rollNo + ".png");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new FileSystemResource(file));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

 
    @PostMapping("/loginQR")
    public String loginWithQR(@RequestParam("qrFile") MultipartFile qrFile, Model model) {
        try {
            User user = userService.loginWithQR(qrFile);
            if (user == null) {
                model.addAttribute("error", "Invalid or unreadable QR code!");
                return "login";
            }		
            boolean marked = attendanceService.markPresent(user);
            model.addAttribute("message", "Welcome " + user.getName() +
                    "! Attendance marked: " + (marked ? " Marked" : "Already marked"));
            return "login-success";
        } catch (Exception e) {
            model.addAttribute("error", "Error processing QR: " + e.getMessage());
            return "login";
        }
    }
}
