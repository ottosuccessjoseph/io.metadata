package io.metadata.student.adapters.inbound;

import io.metadata.student.domain.dto.*;
import io.metadata.student.ports.inbound.StudentWebApiPort;
import io.metadata.student.verb.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentWebApiAdapter implements StudentWebApiPort {

    private final CreateStudentVerb createStudentVerb;
    private final ReadStudentVerb readStudentVerb;
    private final UpdateStudentVerb updateStudentVerb;
    private final DeleteStudentVerb deleteStudentVerb;

    @Override
    @PostMapping
    public WebResponse create(@RequestHeader Map<String, String> headers,
                              @RequestBody @Valid CreateStudentRequest request) {
        return createStudentVerb.execute(request);
    }

    @Override
    @GetMapping("/{reference}")
    public WebResponse read(Map<String, String> headers, @PathVariable String reference) {
        return readStudentVerb.execute(new ReadStudentRequest(reference));
    }

    @Override
    @PutMapping("/{reference}")
    public void update(@RequestHeader Map<String, String> headers,
                       @PathVariable String reference,
                       @RequestBody UpdateStudentRequest request) {
        request.setReference(reference);
        updateStudentVerb.execute(request);
    }

    @Override
    @DeleteMapping("/{reference}")
    public void delete(@RequestHeader Map<String, String> headers,
                       @PathVariable String reference) {
        deleteStudentVerb.execute(new DeleteStudentRequest(reference));
    }
}
