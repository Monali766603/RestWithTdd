package com.yash.student.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.student.beans.Student;

@Repository("studentRepository")
public interface StudentRepository extends CrudRepository<Student, Long> {

	

}
