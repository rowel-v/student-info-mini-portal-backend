package project.prelim.school.dto;

public record UpdateStudentRequest(
		String firstname,
		String middlename,
		String lastname,
		StudentDataDto data
		) {
}
