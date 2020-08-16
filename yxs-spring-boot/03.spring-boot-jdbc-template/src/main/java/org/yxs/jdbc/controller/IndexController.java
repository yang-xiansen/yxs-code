package org.yxs.jdbc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.jdbc.entity.Student;
import org.yxs.jdbc.service.StudentService;

@RestController
public class IndexController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public Student get(@PathVariable(value = "id") Integer id) {
        return studentService.queryStudentById(id);
    }
}
