package org.yxs.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.yxs.mybatis.entity.Student;


public interface StudentMapper1 {

    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    Student queryStudentById(@Param(value = "id") Integer id);

}