package io.metadata.student.verb;

import io.metadata.student.adapters.outbound.StudentMySqlDatasourceAdapter;
import io.metadata.student.domain.dto.CreateStudentRequest;
import io.metadata.student.domain.dto.Error;
import io.metadata.student.domain.dto.WebResponse;
import io.metadata.student.domain.mappers.StudentMapper;
import io.metadata.student.domain.models.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateStudentVerb extends AbstractVerb<CreateStudentRequest, WebResponse> {

    private final StudentMySqlDatasourceAdapter datasourceAdapter;
    @Override
    protected WebResponse handler(CreateStudentRequest request) {
        Student student = StudentMapper.INSTANCE.dtoToStudent(request);
        if (!StringUtils.hasText(request.getReference())) {
            student.setReference(UUID.randomUUID().toString());
        }
        datasourceAdapter.save(student);

        return new WebResponse(student.getReference());
    }

    @Override
    protected List<Error> validate(CreateStudentRequest request) {
        if (datasourceAdapter.findByUsername(request.getUsername()).isPresent()) {
            return Arrays.asList(new Error("student-service", "username", "username is already in use"));
        }
        if (datasourceAdapter.findByEmail(request.getEmail()).isPresent()) {
            return Arrays.asList(new Error("student-service", "email", "email is already in use"));
        }
        return null;
    }
}
