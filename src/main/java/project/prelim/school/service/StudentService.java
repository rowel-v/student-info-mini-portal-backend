package project.prelim.school.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.prelim.school.dto.AddStudentRequest;
import project.prelim.school.dto.DeleteStudentRequest;
import project.prelim.school.dto.GetAllStudentResponse;
import project.prelim.school.dto.UpdateStudentRequest;
import project.prelim.school.mapper.StudentMapper;
import project.prelim.school.model.StudentInfo;
import project.prelim.school.repo.StudentRepo;
import project.prelim.school.result.StudentResult;
import project.prelim.school.result.StudentResult.Add;
import project.prelim.school.result.StudentResult.Delete;
import project.prelim.school.result.StudentResult.Update;

@RequiredArgsConstructor
@Service
public class StudentService {

	private final StudentRepo studentInfoRepo;

	public StudentResult.Add addStudentRequest(AddStudentRequest req) {
		
		boolean studentAlreadyExists = studentInfoRepo.existsByFirstnameAndMiddlenameAndLastname(
				req.firstname(), req.middlename(), req.lastname());

		if (studentAlreadyExists) return Add.STUDENT_ALREADY_EXISTS;

		StudentInfo student = StudentMapper.INSTANCE.toEntity(req);
		studentInfoRepo.save(student);
		return Add.STUDENT_ADD_SUCCESS;
	}

	public StudentResult.Delete deleteStudentRequest(DeleteStudentRequest req) {
		
		Optional<StudentInfo> student = studentInfoRepo.findByFirstnameAndMiddlenameAndLastname(
				req.firstname(), req.middlename(), req.lastname());

		System.out.println(req.firstname());
		System.out.println(req.middlename());
		System.out.println(req.lastname());
		
		return student
				.map(stud -> {
					studentInfoRepo.delete(stud);
					return Delete.STUDENT_DELETE_SUCCESS;
				})
				.orElse(Delete.STUDENT_NOT_FOUND);
	}
	
	public List<GetAllStudentResponse> getAllStudentRequest() {
		
		List<StudentInfo> studs = studentInfoRepo.findAll();
		
		return studs.stream()
		.map(stud -> StudentMapper.INSTANCE.toDTO(stud))
		.collect(Collectors.toList());
	}
	
	public StudentResult.Update updateStudentRequest(UpdateStudentRequest req, String studentFullname) {		
		return studentInfoRepo.findByFullname(studentFullname)
				.map(stud -> {
					StudentInfo updateReq = StudentMapper.INSTANCE.toEntity(req);
					
					if (stud.equals(updateReq)) return Update.STUDENT_DATA_STILL_SAME;
					
					stud.setFirstname(updateReq.getFirstname());
					stud.setMiddlename(updateReq.getMiddlename());
					stud.setLastname(updateReq.getLastname());
					stud.setData(updateReq.getData());
					
					studentInfoRepo.save(stud);
					return Update.STUDENT_UPDATE_SUCCESS;
				})
				.orElse(Update.STUDENT_NOT_FOUND);
	}
	
	

}
