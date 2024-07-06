package com.student.service;

import java.util.List;

import com.student.dto.StudentDto;
import com.student.entity.Student;

public interface StudentService {

	
	public StudentDto update(int id, StudentDto sDto);
	public StudentDto getStudentById(int sId);
	public String deleteById(int sId);
	public List<StudentDto> getAll();
	public String saveStud(StudentDto studentDto);
}
