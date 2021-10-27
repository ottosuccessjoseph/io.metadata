package io.metadata.course.verb;

import io.metadata.course.adapters.outbound.CourseMySqlDatasourceAdapter;
import io.metadata.course.domain.dto.DataWebResponse;
import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.dto.StudentsWithSpecificCourseRequest;
import io.metadata.course.domain.dto.StudentsWithSpecificCourseResponse;
import io.metadata.course.domain.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsWithSpecificCourseVerb extends AbstractVerb<StudentsWithSpecificCourseRequest, DataWebResponse<StudentsWithSpecificCourseResponse>> {

    private final CourseMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected DataWebResponse<StudentsWithSpecificCourseResponse> handler(StudentsWithSpecificCourseRequest request) {
        Course course = datasourceAdapter.findByReference(request.getReference()).get();
        return new DataWebResponse<>(request.getReference(), new StudentsWithSpecificCourseResponse(course.getName(), course.getStudents()));
    }

    @Override
    protected List<Error> validate(StudentsWithSpecificCourseRequest request) {
        Optional<Course> course = datasourceAdapter.findByReference(request.getReference());
        if (course.isEmpty()) {
            return Arrays.asList(new Error("course-service", "reference", "course does not exist"));
        }
        return null;
    }
}
