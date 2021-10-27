package io.metadata.student.domain.mappers;

import io.metadata.student.domain.dto.CreateStudentRequest;
import io.metadata.student.domain.dto.ReadStudentResponse;
import io.metadata.student.domain.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student dtoToStudent(CreateStudentRequest dto);
    ReadStudentResponse studentToDto(Student student);
}
