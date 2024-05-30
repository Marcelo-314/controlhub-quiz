package controlhub.elearning.adapter.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDto {

    private Long id;
    private String name;
    private List<LessonDto> lessons;

}
