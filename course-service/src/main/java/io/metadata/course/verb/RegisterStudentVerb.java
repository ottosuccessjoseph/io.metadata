package io.metadata.course.verb;

import io.metadata.course.adapters.outbound.CourseMySqlDatasourceAdapter;
import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.dto.RegisterStudentRequest;
import io.metadata.course.domain.dto.WebResponse;
import io.metadata.course.domain.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterStudentVerb extends AbstractVerb<RegisterStudentRequest, WebResponse> {
    private final CourseMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected WebResponse handler(RegisterStudentRequest request) {
        Course course = datasourceAdapter.findByReference(request.getReference()).get();
        course.getStudents().add(request.getUserReference());
        datasourceAdapter.save(course);
        return new WebResponse();
    }

    @Override
    protected List<Error> validate(RegisterStudentRequest request) {
        Optional<Course> course = datasourceAdapter.findByReference(request.getReference());
        if (course.isEmpty()) {
            return Arrays.asList(new Error("course-service", "reference", "course does not exist"));
        }
        if (course.isPresent() && course.get().getStudents().size() == 50) {
            return Arrays.asList(new Error("course-service", "reference", "course has exceeded it's maximum threshold."));
        }
        if (course.get().getStudents().size() > 0 && course.get().getStudents().stream().anyMatch(student -> request.getUserReference().equals(student))) {
            return Arrays.asList(new Error("course-service", "reference", "student has already enrolled for this course"));
        }
        Optional<Set<Course>> courses = datasourceAdapter.findAllByStudentReference(request.getUserReference());
        if (courses.isPresent() && courses.get().size() == 5) {
            return Arrays.asList(new Error("course-service", "reference", "student cannot enroll for more than 5 courses"));
        }
        return null;
    }
}
