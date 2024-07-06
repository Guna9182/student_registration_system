package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.dto.StudentDto;
import com.student.entity.Student;
import com.student.exceptions.StudentIdNotFoundException;
import com.student.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

//	@Autowired
//	private StudentService studentSer;

	// when we have one dependency object then we can done this by constructor also
	// from spring 4.0
	private final StudentService studentSer;

	public StudentController(StudentService studentSer) {
		this.studentSer = studentSer;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerStudent(@RequestBody @Valid StudentDto studentDto) {
		String saveStud = studentSer.saveStud(studentDto);
		return new ResponseEntity<>(saveStud, HttpStatus.CREATED);
	}

	@PutMapping("/update/{sId}")
	public ResponseEntity<StudentDto> updateStudent(@PathVariable int sId, @RequestBody @Valid StudentDto studentDto) {
		StudentDto updated = studentSer.update(sId, studentDto);

		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{sId}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable int sId) {
		StudentDto data = studentSer.getStudentById(sId);
		return ResponseEntity.status(HttpStatus.FOUND).body(data);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<StudentDto>> getAllStudent() {
		List<StudentDto> all = studentSer.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(all);
	}

	@DeleteMapping("/delete/{sId}")
	public ResponseEntity<String> deleteStud(@PathVariable int sId) {
		String deletedData = studentSer.deleteById(sId);
		return new ResponseEntity<String>(deletedData, HttpStatus.OK);
	}
}
