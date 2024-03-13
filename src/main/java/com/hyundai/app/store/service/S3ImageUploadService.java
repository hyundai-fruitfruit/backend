package com.hyundai.app.store.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hyundai.app.exception.AdventureOfHeendyException;
import com.hyundai.app.exception.ErrorCode;
import com.hyundai.app.config.AwsS3Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 황수영
 * @since 2024/03/04
 * 리뷰 작성시, S3 이미지 다중 업로드 기능 구현용
 */
@Log4j
@Component
@RequiredArgsConstructor
public class S3ImageUploadService {

    @Value("${aws.s3.bucket}")
    private String bucket;

    private final AwsS3Config awsS3Config;
    
    /**
     * @author 황수영
     * @since 2024/03/03
     * 리뷰 작성 시 이미지 다중 업로드
     */
    public List<String> uploadReviewImages(List<MultipartFile> multipartFilelist) {
        List<String> urlList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFilelist){
            String url = uploadImage(multipartFile);
            urlList.add(url);
        }
        return urlList;
    }

    /**
     * @author 황수영
     * @since 2024/03/03
     * 파일 개별 업로드 후, url 반환
     */
    public String uploadImage(MultipartFile file)  {
        try (InputStream inputStream = file.getInputStream()) {
            String fileName = UUID.randomUUID().toString()
                    .concat(getFileExtension(Objects.requireNonNull(file.getOriginalFilename())));

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());
            uploadFile(inputStream, objectMetadata, fileName);
            return getFileUrl(fileName);
        } catch(IllegalAccessException | IOException e) {
            log.error("파일 변환 중 에러가 발생하였습니다. => 파일명 : " + file.getOriginalFilename());
            throw new AdventureOfHeendyException(ErrorCode.SERVER_UNAVAILABLE);
        }
    }

    /**
     * @author 황수영
     * @since 2024/03/03
     * 파일의 확장자명 가져오기
     */
    private String getFileExtension(String fileName) throws IllegalAccessException {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            log.error("파일 형식이 유효하지 않습니다. => 파일명 : " + fileName);
            throw new AdventureOfHeendyException(ErrorCode.SERVER_UNAVAILABLE);
        }
    }

    public void uploadFile(InputStream inputStream, ObjectMetadata objectMeTadata, String fileName){
        awsS3Config.AmazonS3Client().putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMeTadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public  String getFileUrl(String fileName){
        return awsS3Config.AmazonS3Client().getUrl(bucket, fileName).toString();
    }
}