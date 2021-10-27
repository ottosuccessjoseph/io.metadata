package io.metadata.student.verb;

import io.metadata.student.adapters.outbound.StudentMySqlDatasourceAdapter;
import io.metadata.student.domain.dto.Error;
import io.metadata.student.domain.dto.UpdateStudentRequest;
import io.metadata.student.domain.dto.WebResponse;
import io.metadata.student.domain.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateStudentVerb extends AbstractVerb<UpdateStudentRequest, WebResponse> {
    private final StudentMySqlDatasourceAdapter datasourceAdapter;

    @Override
    protected WebResponse handler(UpdateStudentRequest request) {
        Student student = datasourceAdapter.findByReference(request.getReference()).get();
        if (StringUtils.hasText(request.getFirstName())) student.setFirstName(request.getFirstName());
        if (StringUtils.hasText(request.getLastName())) student.setLastName(request.getLastName());
        datasourceAdapter.save(student);
        return new WebResponse(request.getReference());
    }

    @Override
    protected List<Error> validate(UpdateStudentRequest request) {
        if (!datasourceAdapter.findByReference(request.getReference()).isPresent()) {
            return Arrays.asList(new Error("student-service", "reference", "user reference does not exist"));
        }
        return null;
    }
}
