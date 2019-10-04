package com.yash.student.services;

import java.util.List;
import java.util.Optional;

import com.yash.student.beans.Student;

public interface IStudentService {

	public void deleteStudentById(Long id);

	public List<Student> getStudentList();

	public Optional<Student> getStudentById(Long id);

	public Student saveStudent(Student student);

	public Student updateStudent(Student student);

}
