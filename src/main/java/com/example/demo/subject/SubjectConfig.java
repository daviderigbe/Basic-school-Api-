package com.example.demo.subject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class SubjectConfig {
        @Bean
    CommandLineRunner subjectCL(
            SubjectRepository repository) {
        return args -> {
            Subject Math = new Subject(
                    "Math",
                    "Math412"
            );
            Subject English = new Subject(
                    "English",
                    "Eng433"
            );
            repository.saveAll(
                    List.of(Math, English)
            );
        };
    }
}
