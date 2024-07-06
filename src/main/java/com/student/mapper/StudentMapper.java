package com.student.mapper;

import com.student.dto.StudentDto;
import com.student.entity.Student;

public class StudentMapper {

	public static Student dtoToStudent(StudentDto sDto) {
		Student s = new Student();
		s.setStudentRollNo(sDto.getStudentRollNo());
		s.setStudentName(sDto.getStudentName());
		s.setPhoneNumber(sDto.getPhoneNumber());
		s.setAddress(sDto.getAddress());

		return s;
	}

	public static StudentDto studentToDto(Student s) {
		StudentDto dto = new StudentDto();
		dto.setStudentRollNo(s.getStudentRollNo());
		dto.setStudentName(s.getStudentName());
		dto.setPhoneNumber(s.getPhoneNumber());
		dto.setAddress(s.getAddress());

		return dto;
	}
}
