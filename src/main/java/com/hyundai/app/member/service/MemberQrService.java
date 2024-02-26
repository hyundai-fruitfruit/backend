package com.hyundai.app.member.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.extern.log4j.Log4j;
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
@Log4j
@Component
public class MemberQrService {
    private final int QR_CODE_SIZE = 300;

    public File generateQrCode(String memberId) {
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new QRCodeWriter()
                    .encode(String.valueOf(memberId), BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
        } catch (WriterException e) {
            log.error("큐알코드 생성 실패", e);
            throw new RuntimeException(e);
        }
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        File file = new File(memberId + ".png");
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            log.error("큐알코드 파일 생성 실패", e);
            throw new RuntimeException(e);
        }
        return file;
    }
}
