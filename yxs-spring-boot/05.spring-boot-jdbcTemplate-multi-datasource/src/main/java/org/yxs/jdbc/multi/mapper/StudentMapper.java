package org.yxs.jdbc.multi.mapper;


import org.springframework.jdbc.core.RowMapper;
import org.yxs.jdbc.multi.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setSex(resultSet.getString("sex"));
        return student;
    }
}
