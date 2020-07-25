package com.proj.donemcd.service;

import com.proj.donemcd.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDto> findAllStudents();
    Optional<StudentDto> getStudentDetailsById(long id);
}
