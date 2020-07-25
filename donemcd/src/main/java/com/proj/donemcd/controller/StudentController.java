package com.proj.donemcd.controller;

import com.proj.donemcd.dto.StudentDto;
import com.proj.donemcd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/Students")
    public ResponseEntity<?> getAllStudentDetails(){
        List<StudentDto> studentDtoList = studentService.findAllStudents();
        if(studentDtoList.isEmpty()) {
            return new ResponseEntity<>("StudentDetails are empty",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List>(studentDtoList, HttpStatus.OK);
    }
    @GetMapping("/studentId")
    public ResponseEntity<?> getStudentDetailsById(@RequestParam long id) {
        Optional<StudentDto> optionalStudentDto=studentService.getStudentDetailsById(id);
        if(!optionalStudentDto.isPresent()) {
            return new ResponseEntity<>("StudentDetails are empty",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalStudentDto.get(), HttpStatus.OK);
    }
}
