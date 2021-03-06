package org.yxs.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yxs.redis.entity.Student;
import org.yxs.redis.mapper.StudentMapper;
import org.yxs.redis.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student add(Student student) {
        this.studentMapper.add(student);
        return student;
    }

    @Override
    public Student update(Student student) {
        this.studentMapper.update(student);
        return student;
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
