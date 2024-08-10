package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Grade;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.exception.GradeNotFoundException;
import com.ltp.gradesubmission.exception.StudentNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.GradeRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeServiceImpl implements GradeService {

    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    CourseRepository courseRepository;

    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        Optional<Grade> possibleGrade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);

        if(possibleGrade.isPresent()) return possibleGrade.get();
        else throw new GradeNotFoundException(studentId, courseId);
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        Course course; Student student;
        // we find the student that matches that id
        Optional<Student> possibleStudent = studentRepository.findById(studentId);
        if(possibleStudent.isPresent()) student = possibleStudent.get();
        else throw new StudentNotFoundException(studentId);

        Optional<Course> possibleCourse = courseRepository.findById(courseId);
        if(possibleCourse.isPresent()) course = possibleCourse.get();
        else throw new CourseNotFoundException(courseId);

        // the we associate that student to the grade before saving the grade
        grade.setStudent(student);
        grade.setCourse(course);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {

        Optional<Grade> possibleGrade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);

        if (possibleGrade.isPresent()){
            Grade grade = possibleGrade.get();
            grade.setScore(score);
            // spring jpa will update the grade rather than create a new one since
            // the grade being saved already exists in the database
            return gradeRepository.save(grade);
        }
        else
            throw new GradeNotFoundException(studentId, courseId);
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }
}
