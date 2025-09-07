package project.prelim.school.model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(address, course, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentData other = (StudentData) obj;
		return Objects.equals(address, other.address) && Objects.equals(course, other.course) && year == other.year;
	}
}
