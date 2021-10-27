package io.metadata.student.verb;

import io.metadata.student.adapters.outbound.StudentMySqlDatasourceAdapter;
import io.metadata.student.domain.dto.DeleteStudentRequest;
import io.metadata.student.domain.dto.Error;
import io.metadata.student.domain.dto.WebResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteStudentVerb extends AbstractVerb<DeleteStudentRequest, WebResponse> {

    private final StudentMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected WebResponse handler(DeleteStudentRequest request) {
        datasourceAdapter.delete(datasourceAdapter.findByReference(request.getReference()).get());
        return new WebResponse(request.getReference());
    }

    @Override
    protected List<Error> validate(DeleteStudentRequest request) {
        if (!datasourceAdapter.findByReference(request.getReference()).isPresent()) {
            return Arrays.asList(new Error("student-service", "reference", "user reference does not exist"));
        }
        return null;
    }
}
