package com.proj.donemcd.service;

import com.proj.donemcd.dto.MemberDto;
import com.proj.donemcd.dto.StudentDto;
import com.proj.donemcd.model.Member;
import com.proj.donemcd.model.Student;
import com.proj.donemcd.repository.MemberRepository;
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
    @Autowired(required=true)
    private MemberRepository memberRepository;


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

    @Override
    public void saveStudentDetails(StudentDto studentDto) {
        Student student = buildStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentDetails(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<MemberDto> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        if(members != null && !members.isEmpty()) {
            return convertToMemberDto(members);
        }
        return new ArrayList<>();
    }

    private List<StudentDto> convertToStudentDto(List<Student> students) {
        List<StudentDto> studentDtos = students.stream().map(student->{
            return convertStudent(student);
        }).collect(Collectors.toList());
        return studentDtos;
    }

    private List<MemberDto> convertToMemberDto(List<Member> members) {
        List<MemberDto> memberDtos = members.stream().map(member->{
            return convertMember(member);
        }).collect(Collectors.toList());
        return memberDtos;
    }
    private MemberDto convertMember(Member member) {
        return MemberDto.builder().id(member.getId())
                .firstName(member.getFirstName()).lastName(member.getLastName())
                .jobTitle(member.getJobTitle()).status(member.getStatus())
                .team(member.getTeam()).build();

    }

    private StudentDto convertStudent(Student student) {
        return StudentDto.builder().id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .book(student.getBook()).build();
    }

    private Student buildStudent(StudentDto studentDto) {
        return Student.builder().id(studentDto.getId()).name(studentDto.getName())
                .email(studentDto.getEmail()).book(studentDto.getBook()).build();
    }
}
