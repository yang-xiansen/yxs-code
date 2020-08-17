package org.yxs.jdbc.multi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.yxs.jdbc.multi.entity.Student;
import org.yxs.jdbc.multi.mapper.StudentMapper;
import org.yxs.jdbc.multi.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("mysql1JdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Override
    public int add(Student student) {
        return jdbcTemplate.update("insert into student(id,name,sex) values(null,?,?);", student.getName(), student.getSex());
    }

    @Override
    public int update(Student student) {
        return jdbcTemplate.update("update student set name=?,age=? where id=?", student.getName(), student.getSex(), student.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("delete from student where id=? ;", id);
    }

    @Override
    public Student queryStudentById(Integer id) {
        List<Student> students = jdbcTemplate.query("select * from student where id=?", new StudentMapper(), id);
        return students.get(0);
    }

    @Override
    public Student queryStudentById1(Integer id) {
        List<Student> students = jdbcTemplate1.query("select * from student where id=?", new StudentMapper(), id);
        return students.get(0);
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = jdbcTemplate.query("select * from student", new StudentMapper());
        return students;
    }
}
