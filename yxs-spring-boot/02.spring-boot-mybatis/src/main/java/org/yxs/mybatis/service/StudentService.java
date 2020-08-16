package org.yxs.mybatis.service;

import org.yxs.mybatis.entity.Student;

public interface StudentService {

    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    Student queryStudentById(Integer id);


}
