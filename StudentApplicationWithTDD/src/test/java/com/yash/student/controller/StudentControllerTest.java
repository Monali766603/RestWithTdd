package com.yash.student.controller;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yash.student.beans.Student;
import com.yash.student.services.impl.StudentServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private StudentServiceImpl studentServiceImpl;

	@Test
	public void shouldAddStudentWhenAllDataIsGiven() throws Exception {

		mvc.perform(post("/students/add").accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(getStudent())).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldReturnListOfStudents() throws Exception {
		List<Student> listOfStudent = new ArrayList<>();
		listOfStudent.add(getStudent());

		when(studentServiceImpl.getStudentList()).thenReturn(listOfStudent);
		mvc.perform(get("/students/read").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", comparesEqualTo("Monali")));
		verify(studentServiceImpl).getStudentList();

	}

	@Test
	public void shouldReturnStudentByIdIfStudentIsPresent() throws Exception {

		when(studentServiceImpl.getStudentById(1L)).thenReturn(Optional.of(getStudent()));

		mvc.perform(get("/students/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", comparesEqualTo("Monali")));

		verify(studentServiceImpl).getStudentById(1L);

	}

	@Test
	public void shouldThrowExceptionWhenGettingStudentIfStudentIsNotPresent() throws Exception {

		when(studentServiceImpl.getStudentById(1L)).thenReturn(Optional.empty());

		mvc.perform(get("/students/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

	@Test
	public void shouldDeleteStudentIfStudentIsPresent() throws Exception {
		when(studentServiceImpl.getStudentById(1L)).thenReturn(Optional.of(getStudent()));

		mvc.perform(delete("/students/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(studentServiceImpl).deleteStudentById(1L);

	}

	@Test
	public void shouldThrowExceptionWhenDeletingStudentIfStudentIsNotPresent() throws Exception {

		when(studentServiceImpl.getStudentById(1L)).thenReturn(Optional.empty());

		mvc.perform(delete("/students/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

	@Test
	public void shouldUpdateStudentWhenDataIsValid() throws Exception {
		Student student = getStudent();

		Student studentOutPut = new Student();
		studentOutPut.setId(1l);
		studentOutPut.setName("Pranali");
		when(studentServiceImpl.getStudentById(1L)).thenReturn(Optional.of(student));
		when(studentServiceImpl.updateStudent(student)).thenReturn(studentOutPut);
		mvc.perform(put("/students/1").accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(studentOutPut)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", comparesEqualTo("Pranali")));
		verify(studentServiceImpl).getStudentById(1L);
		

	}
	

	@Test
	public void shouldThrowExceptionWhenUpdatingStudentWhenStudentIsNotPresent() throws Exception {

		Student studentOutPut = new Student();
		studentOutPut.setId(1l);
		studentOutPut.setName("Pranali");

		when(studentServiceImpl.getStudentById(1L)).thenReturn(Optional.empty());

		mvc.perform(put("/students/1").accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(studentOutPut)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

	private Student getStudent() {
		Student s1 = new Student();
		s1.setId(1L);
		s1.setName("Monali");

		return s1;

	}

}
