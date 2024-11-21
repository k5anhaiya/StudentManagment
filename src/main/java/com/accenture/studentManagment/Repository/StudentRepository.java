package com.accenture.studentManagment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.studentManagment.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
