package org.yxs.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxs.mybatis.entity.Student;
import org.yxs.mybatis.mapper.StudentMapper;
import org.yxs.mybatis.mapper.StudentMapper1;
import org.yxs.mybatis.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
//    private StudentMapper studentMapper;
    private StudentMapper1 studentMapper;

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
}
