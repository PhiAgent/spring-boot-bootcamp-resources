package com.ltp.gradesubmission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "grades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NonNull
    @Column(name = "score", nullable = false)
    private String score;

    // A single student can have many grades and we define that using the annotation
    // of ManyToOne
    @ManyToOne(optional = false)
    // Each grade will have a foreign key that points to the student that owns that
    // grade; to define this relationship, we'll use the joinColumn annotation
    // we'll specify the name of the column in the foreign table(referencedColumnName)
    // and the name that we'd like to name this foreign key column in our table(name)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    //each grade must be associated to a
    // student. We define this association by defining a student field for grade
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
