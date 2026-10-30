package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final QRService qrService;

    public UserService(UserRepository userRepository, QRService qrService) {
        this.userRepository = userRepository;
        this.qrService = qrService;
    }

    public User registerUser(String name, String email, String password) throws Exception {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("User already exists!");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        Optional<User> lastUser = userRepository.findTopByOrderByIdDesc();
        int nextNumber = 501;
        if (lastUser.isPresent()) {
            String lastRoll = lastUser.get().getRollNo(); // e.g., "CSE-514"
            nextNumber = Integer.parseInt(lastRoll.split("-")[1]) + 1;
        }
        String roll = "CSE-" + nextNumber;
        user.setRollNo(roll);
        String qrFilePath = qrService.generateQRCode(user.getRollNo());
        user.setQrPath(qrFilePath);

        userRepository.save(user);
        return user;
    }

    public User loginWithQR(org.springframework.web.multipart.MultipartFile qrFile) throws Exception {
        String decodedText = qrService.readQRCode(qrFile);
        Optional<User> userOpt = userRepository.findByRollNo(decodedText);
        return userOpt.orElse(null);
    }
}
