package com.yash.student.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.student.beans.Student;
import com.yash.student.dao.StudentRepository;
import com.yash.student.services.IStudentService;

@Service("studentServiceImpl")
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Transactional
	public Student saveStudent(Student student) {

		return studentRepository.save(student);
		

	}

	@Transactional
	public List<Student> getStudentList() {
		List<Student> studentList = (List<Student>) studentRepository.findAll();
		return studentList;

	}

	@Transactional
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Student updateStudent(Student student) {
		return studentRepository.save(student);

	}

}
