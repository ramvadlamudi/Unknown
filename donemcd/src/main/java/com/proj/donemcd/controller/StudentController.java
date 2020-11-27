package com.proj.donemcd.controller;

import com.proj.donemcd.dto.MemberDto;
import com.proj.donemcd.dto.Response;
import com.proj.donemcd.dto.StudentDto;
import com.proj.donemcd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @GetMapping("/members")
    public ResponseEntity<?> getAllMemberDetails(){
        List<MemberDto> memberDetailsList = studentService.findAllMembers();
        if(memberDetailsList.isEmpty()) {
            return new ResponseEntity<>("MemberDetails are empty",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List>(memberDetailsList, HttpStatus.OK);
    }
    @GetMapping("/studentId")
    public ResponseEntity<?> getStudentDetailsById(@RequestParam long id) {
        Optional<StudentDto> optionalStudentDto=studentService.getStudentDetailsById(id);
        if(!optionalStudentDto.isPresent()) {
            return new ResponseEntity<>("StudentDetails are empty",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalStudentDto.get(), HttpStatus.OK);
    }

    @PostMapping("/insertStudentDetails")
    public ResponseEntity<?> saveStudentDetails(@RequestBody StudentDto studentDto) {
        System.out.println("enter saveStudentDetails "+studentDto);

        ResponseEntity<?> validate = validateStudentDetails(studentDto);
        if(validate!= null){
            return ResponseEntity.badRequest().body(validate.getBody());
        }
        studentService.saveStudentDetails(studentDto);
        return ResponseEntity.ok(new Response(0,"created"));

    }
    @DeleteMapping("/deleteStudentDetails")
    public ResponseEntity<?> deleteStudentDetailsById(@RequestParam long id) {
        System.out.println("Student id "+id);
        studentService.deleteStudentDetails(id);
        return ResponseEntity.ok(new Response(0,"created"));
    }

    private static ResponseEntity<Map<String,Object>> validateStudentDetails(StudentDto studentDto) {
        Map<String,String> getAllErrors =validateInputParameters(studentDto);
        if(!getAllErrors.isEmpty()){
            Map<String,Object> body = new LinkedHashMap<>();
            body.put("Errors",getAllErrors);
            return ResponseEntity.badRequest().body(body);
        }
        return null;
    }

    private static Map<String,String> validateInputParameters(StudentDto studentDto) {
        Map<String,String> getAllErrors = new HashMap<>();
        if(studentDto.getName() == null || "".equals(studentDto.getName())) {
            getAllErrors.put("name", "Student name cannot be null");
        }
        if(studentDto.getEmail() == null || "".equals(studentDto.getEmail())) {
            getAllErrors.put("Email ", "Student email cannot be null");
        }
        if(studentDto.getBook() == null || "".equals(studentDto.getBook())) {
            getAllErrors.put("Book ", "Student book cannot be null");
        }
        return getAllErrors;
    }
}
