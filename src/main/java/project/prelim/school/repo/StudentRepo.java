package project.prelim.school.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.prelim.school.model.StudentInfo;

import java.util.Optional;


@Repository
public interface StudentRepo extends JpaRepository<StudentInfo, Integer> {
	
	Optional<StudentInfo> findByFirstnameAndMiddlenameAndLastname(String firstname, String middlename, String lastname);
	boolean existsByFirstnameAndMiddlenameAndLastname(String firstname, String middlename, String lastname);
	
	
}
