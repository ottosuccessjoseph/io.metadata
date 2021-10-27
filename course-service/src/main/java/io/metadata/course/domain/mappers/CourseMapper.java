package io.metadata.course.domain.mappers;

import io.metadata.course.domain.dto.CreateCourseRequest;
import io.metadata.course.domain.dto.ReadCourseResponse;
import io.metadata.course.domain.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course dtoToCourse(CreateCourseRequest dto);
    ReadCourseResponse courseToDto(Course  course);
}
