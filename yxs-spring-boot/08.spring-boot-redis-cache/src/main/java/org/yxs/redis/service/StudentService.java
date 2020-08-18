package org.yxs.redis.service;


import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.yxs.redis.entity.Student;

@CacheConfig(cacheNames = "student")
public interface StudentService {

    @CachePut(key = "#p0.id")
    Student add(Student student);

    @CachePut(key = "#p0.id")
    Student update(Student student);

    @CacheEvict(key = "#p0", allEntries = true)
    int deleteById(Integer id);

    @Cacheable(key = "#p0")
    Student queryStudentById(Integer id);


}
