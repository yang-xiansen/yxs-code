package org.yxs.mybatis.multi.service;

import org.yxs.mybatis.multi.entity.Student;

public interface StudentService {

    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    Student queryStudentById(Integer id);

    public Student queryStudentById1(Integer id);


}
