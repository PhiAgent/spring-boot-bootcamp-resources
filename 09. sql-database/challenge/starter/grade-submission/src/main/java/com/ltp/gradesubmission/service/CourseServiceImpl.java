package com.ltp.gradesubmission.service;

import java.util.List;
import java.util.Optional;

import com.ltp.gradesubmission.entity.Course;
import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.exception.CourseNotFoundException;
import com.ltp.gradesubmission.repository.CourseRepository;
import com.ltp.gradesubmission.repository.StudentRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;
    StudentRepository studentRepository;

    @Override
    public Course getCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course, id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course course = unwrapCourse(courseRepository.findById(courseId), courseId);
        Student student = StudentServiceImpl.unwrapStudent(studentRepository.findById(studentId), studentId);
        course.getStudents().add(student);
        // Remember to save the course again
        // since you're modifying it, it must
        // be saved again so the changes persist
        // also, since the course you're saving
        // already exists in the db, the
        // crudRepository provided by JPA will
        // update instead of create a new course
        return courseRepository.save(course);
    }

    @Override
    public List<Student> getEnrolledStudents(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new CourseNotFoundException(id);
    }
}
