package io.metadata.course.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Table
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    private String reference;
    private String name;
    @ElementCollection
    private Set<String> students = new HashSet<>();

}
