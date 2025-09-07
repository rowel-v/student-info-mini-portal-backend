package project.prelim.school.result;

public class StudentResult {

	public enum Add {
		STUDENT_ADD_SUCCESS,
		STUDENT_ALREADY_EXISTS;
	}
	
	public enum Delete {
		STUDENT_DELETE_SUCCESS,
		STUDENT_NOT_FOUND;
	}
	
	public enum Update {
		STUDENT_DATA_STILL_SAME,
		STUDENT_NOT_FOUND,
		STUDENT_UPDATE_SUCCESS;
	}
}
