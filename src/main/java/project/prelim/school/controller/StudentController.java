package project.prelim.school.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import project.prelim.school.dto.AddStudentRequest;
import project.prelim.school.dto.DeleteStudentRequest;
import project.prelim.school.dto.GetAllStudentResponse;
import project.prelim.school.dto.UpdateStudentRequest;
import project.prelim.school.service.StudentService;

@RestController @RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {

	private final StudentService studentService;
	
	@PostMapping
	ResponseEntity<Void> addStudent(@RequestBody AddStudentRequest request) {
		
		var result = studentService.addStudentRequest(request);
		
		return switch (result) {
		case STUDENT_ALREADY_EXISTS -> ResponseEntity.status(409).build();
		case STUDENT_ADD_SUCCESS -> ResponseEntity.status(204).build();
		};
	}
	
	@CrossOrigin(origins = "http://localhost:5173", methods = RequestMethod.DELETE)
	@DeleteMapping
	ResponseEntity<Void> deleteStudent(@RequestBody DeleteStudentRequest request) {
		
		var result = studentService.deleteStudentRequest(request);
		
		return switch (result) {
		case STUDENT_NOT_FOUND -> ResponseEntity.status(404).build();
		case STUDENT_DELETE_SUCCESS -> ResponseEntity.status(204).build();
		};
	}
	
	@CrossOrigin(origins = "http://localhost:5173", methods = RequestMethod.GET)
	@GetMapping
	ResponseEntity<List<GetAllStudentResponse>> getAllStudent() {
		return ResponseEntity.ok(studentService.getAllStudentRequest());
	}
	
	@PutMapping("{fullname}")
	ResponseEntity<?> updateStudent(@RequestBody UpdateStudentRequest request, @PathVariable String fullname) {
		var result = studentService.updateStudentRequest(request, fullname);
		
		return switch (result) {
		case STUDENT_NOT_FOUND -> ResponseEntity.status(404).build();
		case STUDENT_DATA_STILL_SAME -> ResponseEntity.status(200).body("Student Data Still Same");
		case STUDENT_UPDATE_SUCCESS -> ResponseEntity.status(204).build();
		};
	}
	
}
