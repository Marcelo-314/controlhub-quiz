package controlhub.elearning.adapter.mapper;

import controlhub.elearning.adapter.dto.CourseDto;
import controlhub.elearning.domain.Course;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class CourseMapper {

    public static CourseDto toDTO(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setLessons(course.getLessons().stream().map(LessonMapper::toDTO).collect(Collectors.toList()));
        return dto;
    }

    public static Course toEntity(CourseDto dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setLessons(dto.getLessons().stream().map(LessonMapper::toEntity).collect(Collectors.toList()));
        return course;
    }

}
