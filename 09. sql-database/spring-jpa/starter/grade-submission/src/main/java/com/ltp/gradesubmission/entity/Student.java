package com.ltp.gradesubmission.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="students")
@Getter
@Setter
// Creates an all args constructor for class using all the fields of the class
// @AllArgsConstructor
// Creates a constructor using fields that are required, in other works, fields that are
// non-null
@RequiredArgsConstructor
// Creates a constructor for the class that takes no args
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    @NonNull
    private String name;
    @Column(name = "birth_date", nullable = false)
    @NonNull
    private LocalDate birthDate;

    // A single student can have many grades. the mappedby key here tells springboot
    // that another table owns this relationship so there's no need to
    // create a join table
    @OneToMany(mappedBy = "student")
    // we use this annotation to ignore the grades property during serialization of
    // a grade object to json in REST response
    // the reason we ignore the grades field in student is because each grade has a
    // student field and each student has a grades field. a grade of G1 belongs to
    // student S1 so if springboot tries to serialize this student, it steps into
    // it'll encounter the grade G1 again and this leads to an infinite loop
    // thats why we tell springboot to ignore this field in serialization
    @JsonIgnore
    private List<Grade> grades;
}
