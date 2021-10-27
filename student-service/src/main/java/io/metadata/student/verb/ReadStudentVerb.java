package io.metadata.student.verb;

import io.metadata.student.adapters.outbound.StudentMySqlDatasourceAdapter;
import io.metadata.student.domain.dto.DataWebResponse;
import io.metadata.student.domain.dto.Error;
import io.metadata.student.domain.dto.ReadStudentRequest;
import io.metadata.student.domain.dto.ReadStudentResponse;
import io.metadata.student.domain.exceptions.BusinessException;
import io.metadata.student.domain.mappers.StudentMapper;
import io.metadata.student.domain.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadStudentVerb extends AbstractVerb<ReadStudentRequest, DataWebResponse<ReadStudentResponse>> {

    private final StudentMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected DataWebResponse<ReadStudentResponse> handler(ReadStudentRequest request) {
        Optional<Student> student = datasourceAdapter.findByReference(request.getReference());
        if (student.isPresent()) {
            return new DataWebResponse<>(request.getReference(), StudentMapper.INSTANCE.studentToDto(student.get()));
        }
        throw new BusinessException(request.getReference(), Arrays.asList(new Error("STUDENT_SERVICE", "reference", "user with reference not found")));
    }

    @Override
    protected List<Error> validate(ReadStudentRequest request) {
        return null;
    }
}
