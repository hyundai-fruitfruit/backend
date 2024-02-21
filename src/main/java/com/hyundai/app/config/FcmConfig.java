package com.hyundai.app.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @author 황수영
 * @since 2024/02/20
 * Firebase 설정용
 */
@Log4j
@Configuration
public class FcmConfig {
    private FirebaseApp firebaseApp;
    @PostConstruct
    public void firebaseInit() throws IOException {
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
        FirebaseApp firebaseApp;
        if (firebaseApps != null && !firebaseApps.isEmpty()) {
            log.info("Firebase가 이미 초기화되어있습니다.");
            for (FirebaseApp app : firebaseApps) {
                if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                    this.firebaseApp = app;
                }
            }
        } else {
            log.info("Firebase를 초기화합니다.");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("fcm/fcm-key.json").getInputStream()))
                    .build();
            this.firebaseApp = FirebaseApp.initializeApp(options);
        }
    }

    @Bean
    public FirebaseApp firebaseApp() {
        return firebaseApp;
    }
}