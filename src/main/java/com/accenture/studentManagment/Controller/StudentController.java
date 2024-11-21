package com.accenture.studentManagment.Controller;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.accenture.studentManagment.Entity.Student;
import com.accenture.studentManagment.Service.StudentService;

@RestController
@RequestMapping("/studentmgmt")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/test")
    public String getStudent() {
        return "My name is pranadeep.";
    }

    @GetMapping("/findallstudent")
    public ResponseEntity<List<Student>> findAllStudents() {
        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }

    @PostMapping("/createStudent")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student)  throws Exception {
        studentService.updateStudent(student);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PutMapping("/updateStudentName/{id}/{name}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id,@PathVariable String name)  throws Exception {
        Student student = studentService.updateStudentName(id,name);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PatchMapping("/updateStudentField")
    public Student updateStudentField(@RequestBody Student student) throws Exception{
        if(Objects.nonNull(student)) {
            student = studentService.updateStudentField(student);
        }
        return student;
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity deleteStudent(@RequestBody Student student) {
        if(Objects.nonNull(student)) {
            student = studentService.deleteStudent(student);
        }
        return new ResponseEntity(student.getName(),HttpStatus.OK);
    }









}
