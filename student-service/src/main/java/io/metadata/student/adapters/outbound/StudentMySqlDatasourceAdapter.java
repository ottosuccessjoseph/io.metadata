package io.metadata.student.adapters.outbound;

import io.metadata.student.domain.models.Student;
import io.metadata.student.ports.outbound.StudentDatasource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentMySqlDatasourceAdapter implements StudentDatasource {
    private final StudentMySqlRepository repository;

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public void delete(Student model) {
        repository.delete(model);
    }

    @Override
    public Optional<Student> findByReference(String reference) {
        return repository.findStudentByReference(reference);
    }

    @Override
    public Optional<Student> findByUsername(String username) {
        return repository.findStudentByUsername(username);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return repository.findStudentByEmail(email);
    }
}
