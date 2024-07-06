package com.student.repo;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	Optional<Student> findBystudentRollNo(int getsRollNo);

}
