package io.metadata.student.ports.inbound;

import io.metadata.student.domain.dto.CreateStudentRequest;
import io.metadata.student.domain.dto.RegisterCourseRequest;
import io.metadata.student.domain.dto.UpdateStudentRequest;
import io.metadata.student.domain.dto.WebResponse;

import java.util.Map;

public interface StudentWebApiPort {
    WebResponse create(Map<String, String> headers, CreateStudentRequest request);
    WebResponse read(Map<String, String> headers, String reference);
    void update(Map<String, String> headers, String reference, UpdateStudentRequest request);
    void delete(Map<String, String> headers, String reference);

}
