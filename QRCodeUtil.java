package com.example.demo;

import com.google.zxing.*;
import com.google.zxing.client.j2se.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.*;
import javax.imageio.ImageIO;

public class QRCodeUtil {

    public static Path generateQRCodeImage(String text, String fileName) throws WriterException, IOException {
        int width = 300;
        int height = 300;

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        Path qrDir = projectDir.resolve("qrcodes");
        if (!Files.exists(qrDir)) {
            Files.createDirectories(qrDir);
        }

        Path filePath = qrDir.resolve(fileName + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", filePath);

        System.out.println(" QR Code created at: " + filePath.toAbsolutePath());
        return filePath;
    }
    public static String decodeQRCode(Path qrImagePath) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrImagePath.toFile());
        if (bufferedImage == null) throw new IOException("Unreadable QR image");

        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            throw new IOException("No QR code found in image");
        }
    }
}
