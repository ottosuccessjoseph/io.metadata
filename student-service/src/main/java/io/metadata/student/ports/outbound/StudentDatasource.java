package io.metadata.student.ports.outbound;

import io.metadata.student.domain.models.Student;

import java.util.Optional;

public interface StudentDatasource extends Datasource<Student> {
    Student save(Student student);
    Optional<Student> findByReference(String reference);
    Optional<Student> findByUsername(String username);
    Optional<Student> findByEmail(String email);
}
