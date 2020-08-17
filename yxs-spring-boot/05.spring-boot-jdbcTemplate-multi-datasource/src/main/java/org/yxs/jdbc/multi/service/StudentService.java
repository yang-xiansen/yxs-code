package org.yxs.jdbc.multi.service;



import org.yxs.jdbc.multi.entity.Student;

import java.util.List;

public interface StudentService {

    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    Student queryStudentById(Integer id);
    Student queryStudentById1(Integer id);

    public List<Student> queryStudents();


}
