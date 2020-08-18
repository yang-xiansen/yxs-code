package org.yxs.ehcache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.yxs.ehcache.entity.Student;


@Mapper
public interface StudentMapper {

    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    Student queryStudentById(@Param(value = "id") Integer id);

}
