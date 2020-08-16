package org.yxs.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.yxs.mybatis.entity.Student;

@Component
@Mapper
public interface StudentMapper {
    @Insert("insert into student(id,name,sex) values(#{id},#{name},#{sex})")
    int add(Student student);

    @Update("update student set name=#{name},sex=#{sex} where id=#{id}")
    int update(Student student);

    @Delete("delete from student where id=#{id}")
    int deleteById(Integer id);

    @Select("select * from student where id=#{id}")
    @Results(id = "student", value = {
        @Result(property = "id", column = "id", javaType = Integer.class),
        @Result(property = "name", column = "name", javaType = String.class),
        @Result(property = "sex", column = "sex", javaType = String.class)
    })
    Student queryStudentById(Integer id);

}