package io.metadata.course.verb;

import io.metadata.course.adapters.outbound.CourseMySqlDatasourceAdapter;
import io.metadata.course.domain.dto.CoursesWithSpecificStudentRequest;
import io.metadata.course.domain.dto.CoursesWithSpecificStudentResponse;
import io.metadata.course.domain.dto.DataWebResponse;
import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CoursesWithSpecificStudentVerb extends AbstractVerb <CoursesWithSpecificStudentRequest , DataWebResponse<Set<Course>>>{

    private final CourseMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected DataWebResponse<Set<Course>> handler(CoursesWithSpecificStudentRequest request) {
        Set<Course> course = datasourceAdapter.findAllByStudentReference(request.getReference()).get();

        return new DataWebResponse<>(request.getReference(), course);
    }

    @Override
    protected List<Error> validate(CoursesWithSpecificStudentRequest request) {
        if (datasourceAdapter.findAllByStudentReference(request.getReference()).isEmpty()) {
            return Arrays.asList(new Error("course-service", "reference", "student has not enrolled in any course"));
        }
        return null;
    }
}
