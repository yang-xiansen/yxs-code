package org.yxs.mybatis.multi.mapper.mysql;

import org.apache.ibatis.annotations.Param;
import org.yxs.mybatis.multi.entity.Student;

public interface StudentMapper {

    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    Student queryStudentById(@Param(value = "id") Integer id);

}
