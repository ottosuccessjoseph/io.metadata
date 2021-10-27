package io.metadata.course.ports.outbound;

import io.metadata.course.domain.models.Course;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CourseDatasource extends Datasource<Course> {
    Course save(Course course);
    void delete(Course course);
    Optional<Course> findByReference(String reference);
    Optional<Course> findByName(String name);
    Optional<Set<Course>> findAllByStudentReference(String reference);
    Optional<Course> findCourseByReferenceAndStudentsContains(String reference, String studentReference);
    Optional<Set<Course>> findCoursesByStudentsEmpty();
}
