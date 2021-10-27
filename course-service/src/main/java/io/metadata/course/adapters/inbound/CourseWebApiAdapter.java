package io.metadata.course.adapters.inbound;

import io.metadata.course.domain.dto.*;
import io.metadata.course.ports.inbound.CourseWebApiPort;
import io.metadata.course.verb.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseWebApiAdapter implements CourseWebApiPort {

    private final CreateCourseVerb createCourseVerb;
    private final ReadCourseVerb readCourseVerb;
    private final UpdateCourseVerb updateCourseVerb;
    private final DeleteCourseVerb deleteCourseVerb;
    private final RegisterStudentVerb registerStudentVerb;
    private final StudentsWithSpecificCourseVerb studentsWithSpecificCourseVerb;
    private final CoursesWithSpecificStudentVerb coursesWithSpecificStudentVerb;
    private final CoursesWithoutStudentsVerb coursesWithoutStudentsVerb;

    @Override
    @PostMapping
    public WebResponse create(@RequestHeader Map<String, String> headers,
                              @Valid @RequestBody CreateCourseRequest request) {
        return createCourseVerb.execute(request);
    }

    @Override
    @GetMapping("/{reference}")
    @Validated
    public WebResponse read(@RequestHeader Map<String, String> headers,
                            @PathVariable String reference) {
        return readCourseVerb.execute(new ReadCourseRequest(reference));
    }

    @Override
    @PutMapping("/{reference}")
    public void update(@RequestHeader Map<String, String> headers,
                       @PathVariable String reference,
                       @RequestBody @Valid UpdateCourseRequest request) {
        updateCourseVerb.execute(request);
    }

    @Override
    @DeleteMapping("/{reference}")
    public void delete(@RequestHeader Map<String, String> headers,
                       @PathVariable String reference) {
        deleteCourseVerb.execute(new DeleteCourseRequest(reference));
    }

    @Override
    @PutMapping("/{reference}/students/{student_reference}")
    public void registerStudent(@RequestHeader Map<String, String> headers,
                                @PathVariable("reference") String reference,
                                @PathVariable("student_reference") String userReference) {
        registerStudentVerb.execute(new RegisterStudentRequest(reference, userReference));
    }

    @GetMapping("/{reference}/students")
    public WebResponse studentsWithSpecificCourse(@RequestHeader Map<String, String> headers,
                                                  @PathVariable String reference) {
        return studentsWithSpecificCourseVerb.execute(new StudentsWithSpecificCourseRequest(reference));
    }

    @GetMapping("/students/{reference}")
    public WebResponse coursesWithSpecificStudent(@RequestHeader Map<String, String> headers,
                                                 @PathVariable String reference) {
        return coursesWithSpecificStudentVerb.execute(new CoursesWithSpecificStudentRequest(reference));
    }

    @GetMapping
    public WebResponse allCoursesWithoutStudents(@RequestHeader Map<String, String> headers) {
        return coursesWithoutStudentsVerb.execute(new AccessControl());
    }
}
