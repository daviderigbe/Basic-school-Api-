package com.example.demo.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SubjectRepository
        extends JpaRepository<Subject, Long> {

    Set<Subject> getSubjectsByIdIn(List<Long> ids);
}
