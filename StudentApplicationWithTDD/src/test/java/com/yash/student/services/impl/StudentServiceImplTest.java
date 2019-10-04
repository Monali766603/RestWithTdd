package com.yash.student.services.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.yash.student.beans.Student;
import com.yash.student.dao.StudentRepository;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	@Mock
	private StudentRepository studentRepository;

	@Test
	public void shouldReturnListOfAllStudents() {
		when(studentRepository.findAll()).thenReturn(getListOfStudents());

		List<Student> actualResult = studentServiceImpl.getStudentList();

		verify(studentRepository).findAll();

		assertEquals(actualResult.get(0).getName(), "Monali");

	}

	@Test
	public void shouldReturnStudentById() {
		when(studentRepository.findById(01L)).thenReturn(Optional.of(getStudentById()));

		Optional<Student> actualResult = studentServiceImpl.getStudentById(1L);

		verify(studentRepository).findById(1L);

		assertEquals(actualResult.get().getName(), "Monali");

	}

	@Test
	public void shouldSaveStudents() {
		Student student = getStudentById();

		when(studentRepository.save(student)).thenReturn(student);

		Student actualResult = studentServiceImpl.saveStudent(student);

		verify(studentRepository).save(student);

		assertEquals(actualResult.getName(), "Monali");

	}

	@Test
	public void shouldDeleteStudentById() {

		studentServiceImpl.deleteStudentById(1L);
	}
	
	@Test
	public void shouldUpdateStudentById()
	{
		Student student= getStudentById();
		
		when(studentRepository.save(student)).thenReturn(student);
		Student actualStudent=studentServiceImpl.updateStudent(student);
		
		verify(studentRepository).save(student);
		
		assertEquals(actualStudent.getName(), "Monali");
		
	}

	private List<Student> getListOfStudents() {

		Student s = new Student();
		s.setId(1l);
		s.setName("Monali");

		List<Student> listOfStudents = new ArrayList<>();
		listOfStudents.add(s);

		return listOfStudents;
	}

	private Student getStudentById() {
		Student s1 = new Student();
		s1.setId(1L);
		s1.setName("Monali");

		return s1;

	}

}
