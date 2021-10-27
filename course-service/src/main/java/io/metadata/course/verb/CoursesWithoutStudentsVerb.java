package io.metadata.course.verb;

import io.metadata.course.adapters.outbound.CourseMySqlDatasourceAdapter;
import io.metadata.course.domain.dto.AccessControl;
import io.metadata.course.domain.dto.DataWebResponse;
import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CoursesWithoutStudentsVerb extends AbstractVerb<AccessControl, DataWebResponse<Set<Course>>> {

    private final CourseMySqlDatasourceAdapter datasourceAdapter;

    @Override
    protected DataWebResponse<Set<Course>> handler(AccessControl request) {
        Set<Course> courses = datasourceAdapter.findCoursesByStudentsEmpty().get();
        return new DataWebResponse<>(null, courses);
    }

    @Override
    protected List<Error> validate(AccessControl request) {
        Optional<Set<Course>> courses = datasourceAdapter.findCoursesByStudentsEmpty();
        if (courses.isEmpty()) {
            return Arrays.asList(new Error("course-service", "", "There're no courses with no student"));
        }
        return null;
    }
}
