package com.proj.donemcd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.donemcd.dto.StudentDto;
import com.proj.donemcd.service.StudentService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;

    @Test
    public void getAllStudentDetails() throws Exception {
        List<StudentDto> studentDtos=  createStudentDetails();
        when(studentService.findAllStudents()).thenReturn(studentDtos);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Students").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andReturn();
        String inputJson = mapToJson(studentDtos);
        assertEquals(inputJson,mvcResult.getResponse().getContentAsString());
        ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).body(studentDtos);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getAllStudentDetails_Empty() throws Exception {
        List<StudentDto> studentDtos= new ArrayList<>();
        when(studentService.findAllStudents()).thenReturn(studentDtos);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Students").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andReturn();
        assertEquals("StudentDetails are empty",mvcResult.getResponse().getContentAsString());
        ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(studentDtos);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
   public void getAllStudentDetails_Exception() throws Exception {
        List<StudentDto> studentDtos=  createStudentDetails();
        when(studentService.findAllStudents()).thenThrow(new RuntimeException("Error"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Students").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andReturn();
        assertEquals("{\"errorCode\":500,\"message\":\"Error\"}",mvcResult.getResponse().getContentAsString());
        ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).body(studentDtos);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getStudentDetailsById() throws Exception {
        StudentDto studentDto= createStudentDto();
        when(studentService.getStudentDetailsById(1)).thenReturn(Optional.of(studentDto));
        String inputJson = mapToJson(studentDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/studentId/1").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertEquals(inputJson,mvcResult.getResponse().getContentAsString());
        ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).body(studentDto);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getStudentDetailsById_Empty() throws Exception {
        Optional<StudentDto> studentDto= Optional.empty();
        when(studentService.getStudentDetailsById(1l)).thenReturn(studentDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/studentId/1").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("StudentDetails are empty",mvcResult.getResponse().getContentAsString());
        ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(studentDto);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
    public void getStudentDetailsById_Exception() throws Exception {
        StudentDto studentDto= createStudentDto();
        when(studentService.getStudentDetailsById(1)).thenThrow(new RuntimeException("Error"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/studentId/1").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("{\"errorCode\":500,\"message\":\"Error\"}",mvcResult.getResponse().getContentAsString());
        ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.OK).body(studentDto);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
    @Test
    public void saveStudentDetails() throws Exception {
        StudentDto studentDto = StudentDto.builder().name("Srinu").email("Srinu@gmail.com").book("LapTop").build();
        String inputJson = mapToJson(studentDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/insertStudentDetails")
                .content(inputJson).contentType(MediaType.APPLICATION_STREAM_JSON_VALUE);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        verify(studentService).saveStudentDetails(studentDto);
        assertEquals("{\"errorCode\":0,\"message\":\"created\"}",mvcResult.getResponse().getContentAsString());
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);
    }
    @Test
    public void saveStudentDetails_InvalidParameters() throws Exception {
        StudentDto studentDto = new StudentDto();
        String inputJson = mapToJson(studentDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/insertStudentDetails")
                .content(inputJson).contentType(MediaType.APPLICATION_STREAM_JSON_VALUE);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("{\"Errors\":{\"Book \":\"Student book cannot be null\",\"name\":\"Student name cannot be null\",\"Email \":\"Student email cannot be null\"}}",mvcResult.getResponse().getContentAsString());
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    private List<StudentDto> createStudentDetails(){
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

    private StudentDto createStudentDto(){
        return StudentDto.builder().id(1l).name("Rama").email("Rama@gmail.com").book("RamaBook").build();

    }
}