package com.accenture.studentManagment.Service;

import java.util.List;

import com.accenture.studentManagment.Entity.Student;

public interface StudentService {
    public List<Student> findAllStudents();

    public Student createStudent(Student student);

    public Student updateStudent(Student student)  throws Exception;

    public Student updateStudentField(Student student) throws Exception;

    Student deleteStudent(Student student);

    Student updateStudentName(Integer id, String name);
}
