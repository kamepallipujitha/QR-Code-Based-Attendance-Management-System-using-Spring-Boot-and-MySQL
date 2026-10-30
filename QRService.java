package com.example.demo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRService {
    public String generateQRCode(String data) throws WriterException, IOException {
        Path qrDir = Paths.get(System.getProperty("user.dir"), "qrcodes");
        File dirFile = qrDir.toFile();
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        String fileName = data + ".png";
        Path filePath = qrDir.resolve(fileName);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix matrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 250, 250);
        MatrixToImageWriter.writeToPath(matrix, "PNG", filePath);

        return filePath.toString();
    }
    public String readQRCode(MultipartFile qrFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(qrFile.getBytes()));
        if (bufferedImage == null) {
            throw new IOException("Invalid image file!");
        }

        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText(); // this will return your roll number like "CSE-503"
        } catch (NotFoundException e) {
            throw new IOException("No QR code found in the uploaded image!");
        }
    }
}
