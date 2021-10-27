package io.metadata.course.verb;

import io.metadata.course.adapters.outbound.CourseMySqlDatasourceAdapter;
import io.metadata.course.domain.dto.CreateCourseRequest;
import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.dto.WebResponse;
import io.metadata.course.domain.mappers.CourseMapper;
import io.metadata.course.domain.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCourseVerb extends AbstractVerb<CreateCourseRequest, WebResponse> {

    private final CourseMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected WebResponse handler(CreateCourseRequest request) {
        Course course = CourseMapper.INSTANCE.dtoToCourse(request);
        course.setReference(UUID.randomUUID().toString());
        datasourceAdapter.save(course);
        return new WebResponse(course.getReference());
    }

    @Override
    protected List<Error> validate(CreateCourseRequest request) {
        Optional<Course> course = datasourceAdapter.findByName(request.getName());
        if (course.isPresent()) {
            return Arrays.asList(new Error("student-service", "name", "course name already exist"));
        }
        return null;
    }
}
