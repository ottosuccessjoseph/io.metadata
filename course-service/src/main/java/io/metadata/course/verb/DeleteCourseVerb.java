package io.metadata.course.verb;

import io.metadata.course.adapters.outbound.CourseMySqlDatasourceAdapter;
import io.metadata.course.domain.dto.DeleteCourseRequest;
import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.dto.WebResponse;
import io.metadata.course.domain.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteCourseVerb extends AbstractVerb<DeleteCourseRequest, WebResponse> {

    private final CourseMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected WebResponse handler(DeleteCourseRequest request) {
        datasourceAdapter.delete(datasourceAdapter.findByReference(request.getReference()).get());
        return new WebResponse();
    }

    @Override
    protected List<Error> validate(DeleteCourseRequest request) {
        Optional<Course> course = datasourceAdapter.findByReference(request.getReference());
        if (!course.isPresent()) {
            return Arrays.asList(new Error("student-service", "reference", "course reference does not exist"));
        }
        return null;
    }
}
