package io.metadata.course.adapters.outbound;

import io.metadata.course.domain.models.Course;
import io.metadata.course.ports.outbound.CourseDatasource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseMySqlDatasourceAdapter implements CourseDatasource {

    private final CourseMySqlRepository repository;
    @Override
    public Course save(Course model) {
        return repository.save(model);
    }

    @Override
    public void delete(Course course) {
        repository.delete(course);
    }

    @Override
    public Optional<Course> findByReference(String reference) {
        return repository.findCourseByReference(reference);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return repository.findCourseByName(name);
    }

    @Override
    public Optional<Set<Course>> findAllByStudentReference(String reference) {
        return repository.findCoursesByStudentsIn(Arrays.asList(reference));
    }

    @Override
    public Optional<Course> findCourseByReferenceAndStudentsContains(String reference, String studentReference) {
        return repository.findCourseByReferenceAndStudentsContains(reference, studentReference);
    }

    @Override
    public Optional<Set<Course>> findCoursesByStudentsEmpty() {
        return repository.findCoursesByStudentsEmpty();
    }
}
