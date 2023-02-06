package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
}
