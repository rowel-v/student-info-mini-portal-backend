package project.prelim.school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "student_data", schema = "student")
@Getter @Setter
public class StudentData {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;
	
	private String course;
	private int year;
	private String address;

	@Builder
	private StudentData(String course, int year, String address) {
		super();
		this.course = course;
		this.year = year;
		this.address = address;
	}
}
