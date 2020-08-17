package org.yxs.jdbc.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.jdbc.multi.entity.Student;
import org.yxs.jdbc.multi.service.StudentService;

import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/")
    public List<Student> queryStudentBySno(Integer id, Integer id1) {
        List<Student> students = Arrays.asList(studentService.queryStudentById(id), studentService.queryStudentById1(id1));
        return students;
    }

}
