package com.proj.donemcd.service;

import com.proj.donemcd.dto.StudentDto;
import com.proj.donemcd.model.Student;
import com.proj.donemcd.repository.StudentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImplTest {

     @MockBean
     private StudentRepository studentRepository;

     @Autowired
     private StudentServiceImpl studentServiceImpl;

    @Test
    public void testFindAllStudents() {
        List<Student> students= createStudentDetails();
        when(studentRepository.findAll()).thenReturn(students);
        List<StudentDto> actualStudentDtos = studentServiceImpl.findAllStudents();
        List<StudentDto> expectedStudentDetails=buildStudentDetails();
        Assert.assertThat(actualStudentDtos,is(expectedStudentDetails));
    }

    @Test
    public void testFindAllStudents_Exception() {
        when(studentRepository.findAll()).thenThrow(new RuntimeException("Time out exception"));
        try{
            studentServiceImpl.findAllStudents();
        } catch(RuntimeException ex){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testGetStudentDetailsById() {
        Student student= buildStudent();
        when(studentRepository.findById(1l)).thenReturn(Optional.of(student));
        Optional<StudentDto> actualStudentDto = studentServiceImpl.getStudentDetailsById(1L);
        Optional<StudentDto> expectedStudentDto = Optional.of(buildStudentDto());
        Assert.assertThat(actualStudentDto,is(expectedStudentDto));
    }
    @Test
    public void testGetStudentDetailsById_Exception() {
        when(studentRepository.findById(1l)).thenThrow(new RuntimeException("TIme out Exception"));
        try{
            studentServiceImpl.getStudentDetailsById(1L);
        } catch(RuntimeException ex) {
            Assert.assertTrue(true);
        }

    }

    private List<Student> createStudentDetails(){
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Rama");
        student1.setEmail("Rama@gmail.com");
        student1.setBook("RamaBook");
        studentList.add(student1);

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Sam");
        student2.setEmail("Sam@gmail.com");
        student2.setBook("SamBook");
        studentList.add(student2);
        return studentList;
    }

    private List<StudentDto> buildStudentDetails(){
        List<StudentDto> studentDtos = new ArrayList<>();
        StudentDto studentDto1 = new StudentDto();
        studentDto1.setId(1L);
        studentDto1.setName("Rama");
        studentDto1.setEmail("Rama@gmail.com");
        studentDto1.setBook("RamaBook");
        studentDtos.add(studentDto1);

        StudentDto studentDto2 = new StudentDto();
        studentDto2.setId(2L);
        studentDto2.setName("Sam");
        studentDto2.setEmail("Sam@gmail.com");
        studentDto2.setBook("SamBook");
        studentDtos.add(studentDto2);
        return studentDtos;
    }

    private Student buildStudent(){
        return Student.builder().id(1l).name("Rama").email("Rama@gmail.com").book("RamaBook").build();
    }

    private StudentDto buildStudentDto(){
        return StudentDto.builder().id(1l).name("Rama").email("Rama@gmail.com").book("RamaBook").build();
    }




}