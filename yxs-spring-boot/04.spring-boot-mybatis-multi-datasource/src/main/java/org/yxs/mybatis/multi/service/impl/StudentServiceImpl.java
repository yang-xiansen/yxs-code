package org.yxs.mybatis.multi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxs.mybatis.multi.entity.Student;
import org.yxs.mybatis.multi.mapper.mysql.StudentMapper;
import org.yxs.mybatis.multi.mapper.mysql1.StudentMapper1;
import org.yxs.mybatis.multi.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentMapper1 studentMapper1;

    @Override
    public int add(Student student) {
        return this.studentMapper.add(student);
    }

    @Override
    public int update(Student student) {
        return this.studentMapper.update(student);
    }

    @Override
    public int deleteById(Integer id) {
        return this.studentMapper.deleteById(id);
    }

    @Override
    public Student queryStudentById(Integer id) {
        return this.studentMapper.queryStudentById(id);
    }

    @Override
    public Student queryStudentById1(Integer id) {
        return this.studentMapper1.queryStudentById(id);
    }
}
