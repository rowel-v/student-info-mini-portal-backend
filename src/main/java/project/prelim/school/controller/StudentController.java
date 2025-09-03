package project.prelim.school.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import project.prelim.school.dto.AddStudentRequest;
import project.prelim.school.dto.DeleteStudentRequest;
import project.prelim.school.service.StudentService;

@RestController @RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {

	private final StudentService studentService;
	
	@PostMapping
	ResponseEntity<Void> addStudent(AddStudentRequest request) {
		
		var result = studentService.addStudentRequest(request);
		
		return switch (result) {
		case STUDENT_ALREADY_EXISTS -> ResponseEntity.status(409).build();
		case STUDENT_ADD_SUCCESS -> ResponseEntity.status(204).build();
		};
	}
	
	@DeleteMapping
	ResponseEntity<Void> deleteStudent(@RequestBody DeleteStudentRequest request) {
		
		var result = studentService.deleteStudentRequest(request);
		
		return switch (result) {
		case STUDENT_NOT_FOUND -> ResponseEntity.status(404).build();
		case STUDENT_DELETE_SUCCESS -> ResponseEntity.status(204).build();
		};
	}
	
}
