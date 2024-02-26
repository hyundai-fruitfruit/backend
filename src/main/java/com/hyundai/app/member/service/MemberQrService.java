package com.hyundai.app.member.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author 엄상은
 * @since 2024/02/26
 * 각 유저의 큐알코드를 만드는 서비스
 */
@Component
public class MemberQrService {
    private final int QR_CODE_SIZE = 300;

    public File generateQrCode(Integer memberId) throws WriterException, IOException {
        BitMatrix bitMatrix = new QRCodeWriter()
                .encode(String.valueOf(memberId), BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        File file = new File(memberId + ".png");
        ImageIO.write(image, "png", file);
        return file;
    }
}
