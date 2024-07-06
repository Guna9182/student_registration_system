package com.student.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.bridge.MessageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dto.StudentDto;
import com.student.entity.Address;
import com.student.entity.Student;
import com.student.exceptions.StudentIdNotFoundException;
import com.student.exceptions.StudentRollNoAlreadyExistsException;
import com.student.mapper.StudentMapper;
import com.student.repo.AddressRepo;
import com.student.repo.StudentRepo;

import jakarta.el.MethodReference;

//import static com.student.StudentRegistrationSystemApplication.myLogger;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String saveStud(StudentDto sDto) {
		Optional<Student> findBysRollNo = studentRepo.findBystudentRollNo(sDto.getStudentRollNo());

		if (findBysRollNo.isPresent()) {
			throw new StudentRollNoAlreadyExistsException("Roll No with " + sDto.getStudentRollNo() + " Already Exists.");
		}

//		Student s = StudentMapper.dtoToStudent(sDto);
		Student s = modelMapper.map(sDto, Student.class);
		addressRepo.save(s.getAddress());
		studentRepo.save(s);

//		myLogger.info("Student Registered Successfully, Name : {}", sDto.getStudentName());
		return "Saved Student Data Successfully...";
	}

	@Override
	public StudentDto update(int id, StudentDto sDto) {
		
		Student studentExistsById = studentRepo.findById(id)
				.orElseThrow(() -> new StudentIdNotFoundException("Student Id not found with this Id to update " + id));

		studentExistsById.setStudentRollNo(sDto.getStudentRollNo());
		studentExistsById.setStudentName(sDto.getStudentName());
		studentExistsById.setPhoneNumber(sDto.getPhoneNumber());

		int adId = studentExistsById.getAddress().getaId();

		Address address = addressRepo.findById(adId).get();

		Address adto = sDto.getAddress();

		address.setHouseNo(adto.getHouseNo());
		address.setCity(adto.getCity());
		address.setState(adto.getState());

		addressRepo.save(address);
		studentRepo.save(studentExistsById);

//		StudentDto dto = StudentMapper.studentToDto(findStud);
		StudentDto dto = modelMapper.map(studentExistsById, StudentDto.class);
//		myLogger.info("Updated student with this Id {}", id);
		return dto;
	}

	@Override
	public StudentDto getStudentById(int sId) {

		Student getStud = studentRepo.findById(sId)
				.orElseThrow(() -> new StudentIdNotFoundException("Student Id not found with Id : " + sId));

//		StudentDto s = StudentMapper.studentToDto(getStud);
		StudentDto s = modelMapper.map(getStud, StudentDto.class);
//		myLogger.info("Student with this Id found.", sId);
		return s;
	}

	@Override
	public List<StudentDto> getAll() {
		List<Student> students = studentRepo.findAll();
//      this stream, map,method reference, collect are java 8 features
//		List<StudentDto> dto = findedAll.stream().map(StudentMapper::studentToDto).collect(Collectors.toList());

		List<StudentDto> dto = students.stream().map((student) -> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());
//		myLogger.info("Got all students data.");
		return dto;
	}

	@Override
	public String deleteById(int sId) {

	  Student foundStudent = studentRepo.findById(sId).orElseThrow(()-> new StudentIdNotFoundException("Student Id not found with this Id to delete :" + sId));

	    studentRepo.deleteById(sId);
	    addressRepo.deleteById(foundStudent.getAddress().getaId());

//		myLogger.info("Deleted student data successfully with Id {}",sId);
		return "Deleted student data successfully....";
	}

}
