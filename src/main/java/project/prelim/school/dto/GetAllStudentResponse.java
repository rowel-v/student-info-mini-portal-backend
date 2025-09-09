package project.prelim.school.dto;

public record GetAllStudentResponse(String firstname, 
		String middlename, 
		String lastname, 
		String fullname, 
		StudentDataDto data) {
}
