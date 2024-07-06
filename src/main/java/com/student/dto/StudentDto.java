package com.student.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import com.student.entity.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class StudentDto {

	@NotNull(message = "Roll No should not be Null")
	private int studentRollNo;

	@NotBlank(message = "Student Name should not be Empty")
	// if NotNull used we can send empty data like ""
	private String studentName;

	@NotEmpty(message = "Phone Number should not be Empty & must be 10 digits.")
	@Length(min = 10, max = 10, message = "Phone Number should be 10 digits, Please check..")
	private String phoneNumber;

	private Address address;

}
