package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {


    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
    @PostMapping
    public Student registerNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public String deleteStudent(
            @PathVariable("studentId")Long studentId){
        studentService.deleteStudent(studentId);
        String response = "Delete successful of studentId "+studentId;
        return response;
    }
    @PutMapping(path = "{studentId}")
    public Student updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return studentService.updateStudent(studentId, name, email);

    }
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable(value = "id") Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/{studentId}/assign")
    public Student assignSubjectsToStudent(@PathVariable(value = "studentId") Long studentId,
                                           @RequestParam(value = "subjectId") Long subjectId) {
        return studentService.assignSubjectToStudent(studentId, subjectId);
    }
}