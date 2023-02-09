package com.example.demo.student;

import com.example.demo.subject.Subject;
import com.example.demo.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public Student getStudent(Long id){
        return studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not found"));
    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id" + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public Student updateStudent(Long studentId,
                                 String name,
                                 String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->new IllegalStateException(
                        "student with id" + studentId + "does not exists"
                ));

        student.setName(name);
        student.setEmail(email);
        return studentRepository.save(student);
    }

    public Student assignSubjectToStudent(Long studentId, Long subjectId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) throw new IllegalStateException("Student doesn't exist");

        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) throw new IllegalStateException("Subject doesn't exist");
//
//        HashSet<Subject> subjectHashSet = new HashSet<>();
//        subjectHashSet.add(optionalSubject.get());

        Student student = optionalStudent.get();

        Set<Subject> subjects = student.getSubjects();
        subjects.add(optionalSubject.get());

        student.setSubjects(subjects);

        student = studentRepository.save(student);

        return student;
    }
}
