package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.StudentNotFoundException;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
// note that if you setup the service as a class, then you'd need to add the @Service annotation to set it up as a
// bean. if you used an interface however, you'd not need to add the @Service annotation
// that's why in the StudentRepository interface we dont' add @Repository annotation
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;

    // now if you create a constructor like below using studentRepository, you
    // won't need to use the autowired annotation
    // stringboot will automatically create the bean
    // now using this argument, we can use the
    // AllArgsConstructor from Lombok to accomplish same results and remove need for Autowired
    // public StudentServiceImpl(StudentRepository studentRepository){
    //     this.studentRepository = studentRepository;
    // }

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student, id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudents() {
        return null;
    }

    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new StudentNotFoundException(id);
    }
}