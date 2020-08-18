package org.yxs.redis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yxs.redis.entity.Student;


@Mapper
public interface StudentMapper {

    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    Student queryStudentById(@Param(value = "id") Integer id);

}
