package com.accenture.studentManagment.ServiceTest;

import com.accenture.studentManagment.Entity.Student;
import com.accenture.studentManagment.Repository.StudentRepository;
import com.accenture.studentManagment.Service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @InjectMocks
    StudentServiceImpl studentService;


    @Mock
    private StudentRepository studentRepository;



    private Student student;
    private Student studentToUpdate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup common test data
        student = new Student();
        student.setId(1);
        student.setName("John Doe");
        student.setAge(20);
        student.setRollNumber(Integer.valueOf("12345"));
        student.setGender("Male");

        studentToUpdate = new Student();
        studentToUpdate.setId(1);
        studentToUpdate.setName("Jane Doe");
        studentToUpdate.setAge(21);
        studentToUpdate.setRollNumber(Integer.valueOf("67890"));
        studentToUpdate.setGender("Female");
    }

    @Test
    public void updateStudentField() throws Exception{
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(studentToUpdate);

        Student students = studentService.updateStudentField(student);

        assertEquals(1, students.getId());
        assertEquals(student.getName(), students.getName());
    }



    @Test
    void testFindAllStudents() {
        // Given
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        // When
        var students = studentService.findAllStudents();

        // Then
        assertNotNull(students);
        assertEquals(1, students.size());
        assertEquals(student.getName(), students.get(0).getName());
    }

    @Test
    void testCreateStudent() {
        // Given
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // When
        Student createdStudent = studentService.createStudent(student);

        // Then
        assertNotNull(createdStudent);
        assertEquals(student.getName(), createdStudent.getName());
    }

    @Test
    void testUpdateStudent() throws Exception {
        // Given
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(studentToUpdate);

        // When
        Student updatedStudent = studentService.updateStudent(studentToUpdate);

        // Then
        assertNotNull(updatedStudent);
        assertEquals(studentToUpdate.getName(), updatedStudent.getName());
        verify(studentRepository, times(1)).save(studentToUpdate);
    }


    @Test
    void testUpdateStudentName() {
        // Given
        String newName = "Updated Name";
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // When
        Student updatedStudent = studentService.updateStudentName(student.getId(), newName);

        // Then
        assertNotNull(updatedStudent);
        assertEquals(newName.toUpperCase(), updatedStudent.getName());
        verify(studentRepository, times(1)).save(updatedStudent);
    }
}
