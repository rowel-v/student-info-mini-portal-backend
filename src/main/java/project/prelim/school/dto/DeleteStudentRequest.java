package project.prelim.school.dto;

public record DeleteStudentRequest(
		String firstname, 
		String middlename, 
		String lastname) {
	
	public DeleteStudentRequest(String firstname, String middlename, String lastname) {
		this.firstname = firstname.trim();
		this.lastname = lastname.trim();
		this.middlename = middlename.trim();
	}
}
