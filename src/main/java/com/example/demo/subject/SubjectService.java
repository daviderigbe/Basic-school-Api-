package com.example.demo.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubject(Long id) {
        return (Subject) subjectRepository.findById(id).orElseThrow(() -> new IllegalStateException("This Is Invalid"));
    }

    public Subject addNewSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional
    public Subject updateSubject(Long subjectId,
                                 String name,
                                 String code) {
        Subject subject = (Subject) subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalStateException(
                        "subject with id" + subjectId + "does not exist"
                ));

        subject.setName(name);
        subject.setCode(code);
        return subjectRepository.save(subject);
    }
    public void deleteSubject(Long subjectId) {
        boolean exists = subjectRepository.existsById(subjectId);
        if (!exists) {
            throw new IllegalStateException(
                    "subject with id" + subjectId + " does not exist");
        }
        subjectRepository.deleteById(subjectId);
    }

}
