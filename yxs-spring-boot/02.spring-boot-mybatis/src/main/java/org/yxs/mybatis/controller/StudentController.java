package org.yxs.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.mybatis.entity.Student;
import org.yxs.mybatis.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/")
    public Student queryStudentBySno(Integer id) {
        return this.studentService.queryStudentById(id);
    }

}
