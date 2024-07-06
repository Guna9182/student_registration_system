package com.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.student.entity.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>{

}
