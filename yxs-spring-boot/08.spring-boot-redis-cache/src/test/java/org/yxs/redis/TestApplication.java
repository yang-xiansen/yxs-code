package org.yxs.redis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yxs.redis.entity.Student;
import org.yxs.redis.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisApplication.class) //指定springboot启动的类
public class TestApplication {

    @Autowired
    private StudentService studentService;

    @Test
    public void test() throws Exception {
        Student student1 = studentService.queryStudentById(1);
        System.out.println("查询的学生姓名为：" + student1.getName());

        student1.setName("yxs");
        this.studentService.update(student1);

        Student student2 = this.studentService.queryStudentById(1);
        System.out.println("查询的学生姓名为：" + student2.getName());
    }
}
