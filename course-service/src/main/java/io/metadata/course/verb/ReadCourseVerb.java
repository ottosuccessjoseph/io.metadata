package io.metadata.course.verb;

import io.metadata.course.adapters.outbound.CourseMySqlDatasourceAdapter;
import io.metadata.course.domain.dto.DataWebResponse;
import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.dto.ReadCourseRequest;
import io.metadata.course.domain.dto.ReadCourseResponse;
import io.metadata.course.domain.mappers.CourseMapper;
import io.metadata.course.domain.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadCourseVerb extends AbstractVerb<ReadCourseRequest, DataWebResponse<ReadCourseResponse>> {

    private final CourseMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected DataWebResponse<ReadCourseResponse> handler(ReadCourseRequest request) {
        return new DataWebResponse<>(request.getReference(), CourseMapper.INSTANCE.courseToDto(datasourceAdapter.findByReference(request.getReference()).get()));
    }

    @Override
    protected List<Error> validate(ReadCourseRequest request) {
        Optional<Course> course = datasourceAdapter.findByReference(request.getReference());
        if (!course.isPresent()) {
            return Arrays.asList(new Error("student-service", "reference", "course reference does not exist"));
        }
        return null;
    }
}
