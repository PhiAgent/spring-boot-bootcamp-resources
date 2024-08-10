package com.ltp.gradesubmission;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.repository.StudentRepository;

@SpringBootApplication
// By implementing the CommandLineRunner interface, you can override the run application
// causing the app to not run as it normally would
public class GradeSubmissionApplication implements CommandLineRunner {

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(GradeSubmissionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student[] students = new Student[] {
            new Student("Harry Potter", LocalDate.parse(("1980-07-31"))),
            new Student("Ron Weasley", LocalDate.parse(("1980-03-01"))),
            new Student("Hermione Granger", LocalDate.parse(("1979-09-19"))),
            new Student("Neville Longbottom", LocalDate.parse(("1980-07-30")))
        };

		// Rather than populate the in-memory database using postman to send requests
		// we're bypassing postman and invoking the
		// save function directly on the repository
		// to save the students at the entry point
		// of the application here
		for(Student student: students) studentRepository.save(student);
	}

}
