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

		String firstname = req.firstname(), middlename = req.middlename(), lastname = req.lastname();

		boolean studentAlreadyExists = studentInfoRepo.existsByFirstnameAndMiddlenameAndLastname(firstname, middlename, lastname);

		if (studentAlreadyExists) return Add.STUDENT_ALREADY_EXISTS;

		StudentInfo student = StudentMapper.INSTANCE.toEntity(req);
		studentInfoRepo.save(student);
		return Add.STUDENT_ADD_SUCCESS;
	}

	public StudentResult.Delete deleteStudentRequest(DeleteStudentRequest req) {
		
		String extractedData[] = req.fullname().split(" ");
		String firstname = extractedData[0], middlename = extractedData[1], lastname = extractedData[2];
		
		Optional<StudentInfo> student = studentInfoRepo.findByFirstnameAndMiddlenameAndLastname(
				firstname, middlename, lastname);

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
		
		String extractedData[] = studentFullname.split(" ");
		String firstname = extractedData[0], middlename = extractedData[1], lastname = extractedData[2];
		
		return studentInfoRepo.findByFirstnameAndMiddlenameAndLastname(firstname, middlename, lastname)
				.map(stud -> {
					StudentInfo updateReq = StudentMapper.INSTANCE.toEntity(req);
					
					if (stud.equals(updateReq)) return Update.STUDENT_DATA_STILL_SAME;
					
					stud.setFirstname(req.firstname());
					stud.setMiddlename(req.middlename());
					stud.setLastname(req.lastname());
					stud.setData(updateReq.getData());
					
					studentInfoRepo.save(stud);
					return Update.STUDENT_UPDATE_SUCCESS;
				})
				.orElse(Update.STUDENT_NOT_FOUND);
	}
	
	

}
