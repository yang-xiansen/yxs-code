package org.yxs.jdbc.service;


import org.yxs.jdbc.entity.Student;

import java.util.List;

public interface StudentService {

    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    Student queryStudentById(Integer id);

    public List<Student> queryStudents();


}
