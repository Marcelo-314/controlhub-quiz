package controlhub.elearning.adapter.dto;

import lombok.Data;

import java.util.List;

@Data
public class LessonDto {

    private Long id;
    private String name;
    private Long courseId;
    private List<QuestionDto> questions;
    private int approvalThreshold;

}
