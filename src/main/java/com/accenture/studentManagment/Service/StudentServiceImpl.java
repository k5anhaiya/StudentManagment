package com.accenture.studentManagment.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ch.qos.logback.core.util.StringUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.studentManagment.Entity.Student;
import com.accenture.studentManagment.Repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        try {
            if(Objects.nonNull(student)) {
                student = studentRepository.save(student);
            }
        }catch(Exception ee){
            ee.printStackTrace();
        }
        return student;
    }

    @Override
    public Student updateStudent(Student student) throws Exception{
        Integer tempId = student.getId();
        studentRepository.findById(tempId);
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student updateStudentField(Student studentDto) throws Exception {
        Student student1 = studentRepository.findById(studentDto.getId()).orElseThrow(()->new RuntimeException("user not found"));
        if(studentDto.getName()!=null){
            student1.setName(studentDto.getName().toUpperCase());
        } else if (studentDto.getAge()!=null) {
            student1.setAge(studentDto.getAge());
        } else if (studentDto.getRollNumber()!=null) {
            student1.setRollNumber(studentDto.getRollNumber());
        } else if (studentDto.getGender()!=null) {
            student1.setGender(studentDto.getGender());
        }
        studentRepository.save(student1);
        return student1;
    }


    @Override
    public Student deleteStudent(Student student) {
        studentRepository.deleteById(student.getId());
        return student;
    }

    @Override
    public Student updateStudentName(Integer id, String name) {
        Student student1=null;
        try {
            if(!Strings.isBlank(String.valueOf(id))){
                student1 = studentRepository.findById(Integer.valueOf(id)).get();
                student1.setName(name.toUpperCase());
                studentRepository.save(student1);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("user not found");
        }
        return student1;
    }
}
