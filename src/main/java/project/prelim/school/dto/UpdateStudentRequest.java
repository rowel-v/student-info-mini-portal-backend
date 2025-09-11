package project.prelim.school.dto;

public record UpdateStudentRequest(
		String firstname,
		String middlename,
		String lastname,
		StudentDataDto data
		) {
	
	public UpdateStudentRequest(String firstname, String middlename, String lastname, StudentDataDto data) {
		this.firstname = firstname.trim();
		this.lastname = lastname.trim();
		this.middlename = middlename.trim();
		this.data = new StudentDataDto(data.course().trim(), data.year(), data.address().trim());
	}	
}
