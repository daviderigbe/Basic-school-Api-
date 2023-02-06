package com.example.demo.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/student/subject")
public class SubjectController {

    private final SubjectService subjectService;
    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

//    @GetMapping
//    public List<Subject> getSubject() {
//        return subjectService.getSubjects();
//    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable(value = "id") Long id) {
        return subjectService.getSubject(id);
    }

    @PostMapping
    public Subject registerNewSubject(@RequestBody Subject subject) {
        return subjectService.addNewSubject(subject);
    }

    @PutMapping(path = "{subjectId}")
    public Subject updateSubject(
            @PathVariable("subjectId") Long subjectId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code) {
        return subjectService.updateSubject(subjectId, name, code);
    }

    @DeleteMapping(path = "{subjectId}")
    public String deleteSubject(
            @PathVariable("studentId")Long subjectId){
        subjectService.deleteSubject(subjectId);
        String response = "subjectId has been deleted successfully "+subjectId;
        return response;
    }
}
