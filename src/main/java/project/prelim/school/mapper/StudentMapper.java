package project.prelim.school.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import project.prelim.school.dto.AddStudentRequest;
import project.prelim.school.dto.GetAllStudentResponse;
import project.prelim.school.dto.StudentDataDto;
import project.prelim.school.model.StudentData;
import project.prelim.school.model.StudentInfo;

@Mapper
public interface StudentMapper {
	
	public StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	
	StudentData toEntity(StudentDataDto studentDataDto);
	
	StudentInfo toEntity(AddStudentRequest request);
	
	GetAllStudentResponse toDTO(StudentInfo student);

}
