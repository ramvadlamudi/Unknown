package com.proj.donemcd.service;

import com.proj.donemcd.dto.MemberDto;
import com.proj.donemcd.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDto> findAllStudents();
    Optional<StudentDto> getStudentDetailsById(long id);
    Optional<MemberDto> getMemberDetailsById(long id);
    void saveStudentDetails(StudentDto studentDto);
    void saveMemberDetails(MemberDto memberDto);
    void deleteStudentDetails(long id);
    List<MemberDto> findAllMembers();
    void deleteMemberDetails(long id);
}
