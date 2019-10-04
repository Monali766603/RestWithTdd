package com.yash.student.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.student.beans.Student;
import com.yash.student.exception.RecordNotFoundException;
import com.yash.student.services.IStudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	Logger logger = Logger.getLogger(StudentController.class.getName());

	@Autowired
	private IStudentService studentService;

	@PostMapping("/add")
	public void addStudent(@RequestBody Student student) {
		studentService.saveStudent(student);

	}

	@GetMapping("/read")
	public List<Student> getAllStudents() {
		return studentService.getStudentList();
	}

	@GetMapping(path = "/{id}")
	public Student findStudentById(@PathVariable Long id) {
		Optional<Student> stud = studentService.getStudentById(id);
		if (!stud.isPresent()) {
			throw new RecordNotFoundException("Invalid student id : " + id);
		}
		return stud.get();
	}

	@DeleteMapping(path = "/{id}")
	public void deleteStudentById(@PathVariable Long id) {

		if (!studentService.getStudentById(id).isPresent()) {

			throw new RecordNotFoundException("Student is not exist ");
		}
		studentService.deleteStudentById(id);
		
	}

	@PutMapping(path = "/{id}")
	public Student updateStudentById(@PathVariable Long id, @RequestBody Student updateStud) {
		Optional<Student> student = studentService.getStudentById(id);

		if (!student.isPresent()) {
			throw new RecordNotFoundException("Id not exist");
		}

		Student studentToUpdate=student.get();
		studentToUpdate.setName(updateStud.getName());

		return studentService.updateStudent(studentToUpdate);
		

	}

}
