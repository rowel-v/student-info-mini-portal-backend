package project.prelim.school.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity 
@Table(name = "student_info", schema = "student")
@Getter @Setter
public class StudentInfo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private Integer id;
	
	private String firstname;
	private String middlename;
	private String lastname;
	
	private String fullname;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "data_id")
	private StudentData data;

	@Builder
	private StudentInfo(String firstname, String middlename, String lastname, StudentData data) {
		super();
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.data = data;
	}
	
	@PrePersist
	private void formFullname() {
		this.fullname = String.format("%s %s %s", firstname, middlename, lastname);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(data, firstname, lastname, middlename);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentInfo other = (StudentInfo) obj;
		return Objects.equals(data, other.data) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(middlename, other.middlename);
	}
}
