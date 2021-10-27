package io.metadata.course.verb;

import io.metadata.course.adapters.outbound.CourseMySqlDatasourceAdapter;
import io.metadata.course.domain.dto.Error;
import io.metadata.course.domain.dto.UpdateCourseRequest;
import io.metadata.course.domain.dto.WebResponse;
import io.metadata.course.domain.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateCourseVerb extends AbstractVerb<UpdateCourseRequest, WebResponse> {

    private final CourseMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected WebResponse handler(UpdateCourseRequest request) {
        Course course = datasourceAdapter.findByReference(request.getReference()).get();
        if (StringUtils.hasText(request.getName())) course.setName(request.getName());
        datasourceAdapter.save(course);

        return new WebResponse(request.getReference());
    }

    @Override
    protected List<Error> validate(UpdateCourseRequest request) {
        Optional<Course> course = datasourceAdapter.findByReference(request.getReference());
        if (!course.isPresent()) {
            return Arrays.asList(new Error("student-service", "reference", "course reference does not exist"));
        }
        return null;
    }
}
