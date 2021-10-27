package io.metadata.student.adapters.outbound;

import io.metadata.student.domain.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentMySqlRepository extends CrudRepository<Student, Long> {
    Optional<Student> findStudentByReference(String reference);
    Optional<Student> findStudentByUsername(String username);
    Optional<Student> findStudentByEmail(String email);
}
