package io.metadata.course.adapters.outbound;

import io.metadata.course.domain.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CourseMySqlRepository extends CrudRepository<Course, Long> {
    Optional<Course> findCourseByReference(String reference);
    Optional<Course> findCourseByName(String name);
    Optional<Set<Course>> findCoursesByStudentsIn(List<String> references);
    Optional<Course> findCourseByReferenceAndStudentsContains(String reference, String studentReference);
    Optional<Set<Course>> findCoursesByStudentsEmpty();

}
