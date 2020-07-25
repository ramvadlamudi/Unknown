package com.proj.donemcd.service;

import com.proj.donemcd.dto.StudentDto;
import com.proj.donemcd.model.Student;
import com.proj.donemcd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired(required=true)
    private StudentRepository studentRepository;


    @Override
    public List<StudentDto> findAllStudents() {
        List<Student> students = studentRepository.findAll();
       if(students != null && !students.isEmpty()) {
           return convertToStudentDto(students);
       }
        return new ArrayList<>();
    }

    @Override
    public Optional<StudentDto> getStudentDetailsById(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()) {
            StudentDto studentDto = convertStudent(optionalStudent.get());
            return Optional.of(studentDto);
        }
        return Optional.empty();
    }

    private List<StudentDto> convertToStudentDto(List<Student> students) {
        List<StudentDto> studentDtos = students.stream().map(student->{
            return convertStudent(student);
        }).collect(Collectors.toList());
        return studentDtos;
    }

    private StudentDto convertStudent(Student student) {
        return StudentDto.builder().id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .book(student.getBook()).build();
    }
}
