package project.prelim.school.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import project.prelim.school.dto.AddStudentRequest;
import project.prelim.school.dto.StudentDataDto;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final StudentController studentController;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		StudentDataDto sDto1 = new StudentDataDto("BSIT", 4, "Don Enrique Heights Brgy San Roque Antipolo City");
		AddStudentRequest request1 = new AddStudentRequest("Rowel", "Batiancila", "Razonable", sDto1);
		
		StudentDataDto sDto2 = new StudentDataDto("BSIT", 4, "Address 23 Brgy San Dalig Antipolo City");
		AddStudentRequest request2 = new AddStudentRequest("Kobe", "Dimaranan", "Santiago", sDto2);
		
		StudentDataDto sDto3 = new StudentDataDto("BSIT", 4, "Icct Street Brgy San Roque Antipolo City");
		AddStudentRequest request3 = new AddStudentRequest("Brytch", "Bonca", "Boncales", sDto3);
		
		StudentDataDto sDto4 = new StudentDataDto("BSIT", 4, "Address 020320302230 Brgy Dalig Antipolo City");
		AddStudentRequest request4 = new AddStudentRequest("Randy", "Salen", "Merca", sDto4);
		
		StudentDataDto sDto5 = new StudentDataDto("BSIT", 4, "Address 234234 Brgy Dalig Antipolo City");
		AddStudentRequest request5 = new AddStudentRequest("Kim", "Castro", "Ramos", sDto5);
		
		StudentDataDto sDto6 = new StudentDataDto("BSIS", 4, "Flying V Brgy San Jose Antipolo City");
		AddStudentRequest request6 = new AddStudentRequest("Flynn", "Dalaman", "Dalaman", sDto6);
		
		studentController.addStudent(request1);
		studentController.addStudent(request2);
		studentController.addStudent(request3);
		studentController.addStudent(request4);
		studentController.addStudent(request5);
		studentController.addStudent(request6);
	}

}
