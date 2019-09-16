package com.open.cloud.redis.dao;

import com.open.cloud.redis.pojo.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-16-11:46 下午
 */
public interface StudentRepository extends CrudRepository<Student, String> {
}
