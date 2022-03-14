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
        System.out.println("Enter the Student details");
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

    @GetMapping("/memberId/{id}")
    public ResponseEntity<?> getMemberDetailsById(@PathVariable long id) {
        System.out.print("print id=================> "+id);
        Optional<MemberDto> optionalMemberDto=studentService.getMemberDetailsById(id);
        if(!optionalMemberDto.isPresent()) {
            return new ResponseEntity<>("MemberDetails are empty",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalMemberDto.get(), HttpStatus.OK);
    }


    @GetMapping("/studentId/{id}")
    public ResponseEntity<?> getStudentDetailsById(@PathVariable long id) {

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
    @PostMapping("/insertMemberDetails")
    public ResponseEntity<?> saveMemberDetails(@RequestBody MemberDto memberDto) {
        /*System.out.println("enter saveMemberDetails "+memberDto);
        ResponseEntity<?> validate = (ResponseEntity<?>) validateMemberDetails(memberDto);
        if(validate!= null){
            return ResponseEntity.badRequest().body(validate.getBody());
        }*/
        studentService.saveMemberDetails(memberDto);
        return ResponseEntity.ok(new Response(0,"created"));

    }

    @DeleteMapping("/deleteStudentDetails")
    public ResponseEntity<?> deleteStudentDetailsById(@RequestParam long id) {
        System.out.println("Student id "+id);
        studentService.deleteStudentDetails(id);
        return ResponseEntity.ok(new Response(0,"created"));
    }

    @DeleteMapping("/deleteMemberDetails/{id}")
    public ResponseEntity<?> deleteMemberDetailsById(@PathVariable long id) {
        studentService.deleteMemberDetails(id);
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

    private Map<String,String> validateMemberDetails(MemberDto memberDto) {
        Map<String,String> getAllErrors = new HashMap<>();
        if(memberDto.getFirstName() == null || "".equals(memberDto.getFirstName())) {
            getAllErrors.put("firstName", "Member firstName cannot be null");
        }
        if(memberDto.getLastName() == null || "".equals(memberDto.getLastName())) {
            getAllErrors.put("lastName ", "Member lastName cannot be null");
        }
        if(memberDto.getJobTitle() == null || "".equals(memberDto.getJobTitle())) {
            getAllErrors.put("JobTitle ", "Member JobTitle cannot be null");
        }
        if(memberDto.getTeam() == null || "".equals(memberDto.getTeam())) {
            getAllErrors.put("Team ", "Member Team cannot be null");
        }
        if(memberDto.getStatus() == null || "".equals(memberDto.getStatus())) {
            getAllErrors.put("Status ", "Member Status cannot be null");
        }
        return getAllErrors;
    }
}
