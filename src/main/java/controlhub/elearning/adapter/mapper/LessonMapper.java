package controlhub.elearning.adapter.mapper;

import controlhub.elearning.adapter.dto.LessonDto;
import controlhub.elearning.domain.Lesson;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class LessonMapper {

    public static LessonDto toDTO(Lesson lesson) {
        LessonDto dto = new LessonDto();
        dto.setId(lesson.getId());
        dto.setName(lesson.getName());
        dto.setCourseId(lesson.getCourse().getId());
        dto.setQuestions(lesson.getQuestions().stream().map(QuestionMapper::toDTO).collect(Collectors.toList()));
        dto.setApprovalThreshold(lesson.getApprovalThreshold());
        return dto;
    }

    public static Lesson toEntity(LessonDto dto) {
        Lesson lesson = new Lesson();
        lesson.setId(dto.getId());
        lesson.setName(dto.getName());
        // course should be set by service layer to avoid loading issues
        lesson.setApprovalThreshold(dto.getApprovalThreshold());
        lesson.setQuestions(dto.getQuestions().stream().map(QuestionMapper::toEntity).collect(Collectors.toList()));
        return lesson;
    }

}
