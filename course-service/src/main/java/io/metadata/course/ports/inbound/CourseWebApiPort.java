package io.metadata.course.ports.inbound;

import io.metadata.course.domain.dto.CreateCourseRequest;
import io.metadata.course.domain.dto.UpdateCourseRequest;
import io.metadata.course.domain.dto.WebResponse;

import java.util.Map;

public interface CourseWebApiPort {
    WebResponse create(Map<String, String> headers, CreateCourseRequest request);
    WebResponse read(Map<String, String> headers, String reference);
    void update(Map<String, String> headers, String reference, UpdateCourseRequest request);
    void delete(Map<String, String> headers, String reference);
    void registerStudent(Map<String, String> headers, String reference, String userReference);
    WebResponse studentsWithSpecificCourse(Map<String, String> headers, String reference);
    WebResponse coursesWithSpecificStudent(Map<String, String> headers, String reference);
    WebResponse allCoursesWithoutStudents(Map<String, String> headers);
}
